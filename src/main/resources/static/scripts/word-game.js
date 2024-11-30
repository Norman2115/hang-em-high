// Difficulty levels
let currentDifficulty = getDifficulty();
console.log(currentDifficulty);

// To track questions
const totalQuestions = 10;
let totalCorrectAnswers = 0;

// Score variables
const correctAnswerPoints = 20;
// const incorrectWordPoints = 2;

// Score variables
let score = 0;

// Lives
let lives = 6;
let hangman = 0;
const hangmanImage = document.querySelector(".hang-man-image")

// Timer variables
let minutes = 0;
let seconds = 0;
let timerInterval;

// Countdown variables
let countdownValue = 3;
let countdownInterval;
const countdownTextElement = document.querySelector(".countdown-text");

async function checkRemainingWords() {
  try {
    const response = await fetch(`/api/words/remaining`);

    const words = await response.json();

    if (!response.ok) {
      throw new Error("Network response error, status: " + response.status);
    }

    console.log("Remaining words:", words);
    return words;
  } catch (error) {
    console.error("Error checking remaining words:", error);
  }
}

async function fetchWord(difficulty) {
  try {
    const response = await fetch(`/api/words/${difficulty}`);

    if (!response.ok) {
      throw new Error("Network response error, status: " + response.status);
    }

    const word = await response.json();
    console.log(word);
    displayWord(word);
  } catch (error) {
    console.error("Error fetching word:", error);
  }
}

async function checkAndUpdateLeaderboardEntry() {
  try {
    const currentState = loadPlayerState();
    const sessionId =
      currentState.sessionId + "-" + currentDifficulty + "-word";

    console.log("Session ID:", sessionId);
    const response = await fetch(`/api/leaderboard/word/get/${sessionId}`);

    if (response.ok) {
      const leaderboardEntry = await response.json();
      console.log("Leaderboard entry:", leaderboardEntry);

      console.log("Current score:", score);
      console.log("Current time:", minutes * 60 + seconds);

      if (
        calculateWeightedScore(score, minutes * 60 + seconds) >
        calculateWeightedScore(
          leaderboardEntry.score,
          leaderboardEntry.time === 0 ? 1 : leaderboardEntry.time
        )
      ) {
        await updateLeaderboardEntry(
          sessionId,
          currentState.name,
          score,
          minutes * 60 + seconds
        );
      } else {
        console.log("Existing score is higher, skipping update.");
      }
    } else if (response.status === 404) {
      throw new Error("Leaderboard entry not found for session ID:", sessionId);
    } else {
      throw new Error("Error fetching leaderboard entry: " + response.status);
    }
  } catch (error) {
    console.error("Error fetching leaderboard entry:", error);
  }
}

function calculateWeightedScore(score, time) {
  // Define weights for score and time (adjust as needed)
  const scoreWeight = 0.7; // 70% emphasis on score
  const timeWeight = 0.3; // 30% emphasis on time

  // Calculate weighted score
  // Since time is unbounded, inverse (1/time) to give lower times higher weight
  const weightedScore = scoreWeight * score + timeWeight * (1 / time);

  // Scale to get a higher value if needed (e.g., multiply by 1000)
  return weightedScore * 1000;
}

async function updateLeaderboardEntry(sessionId, name, score, time) {
  const url = `/api/leaderboard/word/update/${encodeURIComponent(
    sessionId
  )}?name=${encodeURIComponent(name)}&score=${score}&time=${time}`;

  try {
    const response = await fetch(url, {
      method: "PUT",
    });

    if (!response.ok) {
      throw new Error("Network response error, status: " + response.status);
    }

    console.log("Leaderboard entry updated");
  } catch (error) {
    console.error("Error updating leaderboard entry:", error);
  }
}

// Save player score
function savePlayerScore() {
  const currentState = loadPlayerState();
  currentState.score = score;
  savePlayerState(currentState);
}

function savePlayerTime() {
  const currentState = loadPlayerState();
  currentState.time = minutes * 60 + seconds;
  savePlayerState(currentState);
}

// Countdown function
function startCountdown() {
  countdownValue = 3;
  countdownTextElement.textContent = countdownValue;
  playCountdownAudio();

  countdownInterval = setInterval(() => {
    countdownValue--;

    if (countdownValue !== 0) {
      countdownTextElement.textContent = countdownValue;
    } else {
      countdownTextElement.textContent = "Go!";
    }

    if (countdownValue === -1) {
      clearInterval(countdownInterval);
      restartGame();
      createKeyboard();
      startTimer();
      const container = document.querySelector(".countdown-container");
      container.style.display = "none";
    }
  }, 1000);
}

// Timer functions
function startTimer() {
  timerInterval = setInterval(() => {
    seconds++;

    if (seconds == 60) {
      minutes++;
      seconds = 0;
    }

    displayTimer();
  }, 1000);
}

function displayTimer() {
  const timerElement = document.querySelector(".timer-number");
  timerElement.textContent = `${minutes}:${
    seconds < 10 ? "0" + seconds : seconds
  }`;
}

function stopTimer() {
  clearInterval(timerInterval);
}

// Display the words and hints on the page
function displayWord(word) {
  const wordTextElement = document.querySelector(".word-text");
  const hintElement = document.querySelector(".hint-text");

  // Set the word text
  wordTextElement.textContent = word.currentGuess.split("").join(" ");
  // Set the hint text
  hintElement.textContent = "Hint: " + word.hint;

  // get the event listener for the keyboard keys
  const keyboardKeys = document.querySelectorAll(".keyboard-key");

  keyboardKeys.forEach((key) => {
    if (!key.dataset.listenerAdded) {
      key.addEventListener("click", async () => {
        console.log(key.textContent);
        disableKeyboardKey();

        const response = await updateGuess(key.textContent);
        console.log(response);

        if (response.isCorrect) {
          wordTextElement.textContent = response.word.currentGuess
            .split("")
            .join(" ");

          key.classList.add("correct");
          setTimeout(() => {
            key.classList.remove("correct");
          }, 300);

          playCorrectAnswerAudio();
        } else {
          key.classList.add("incorrect");
          setTimeout(() => {
            key.classList.remove("incorrect");
          }, 300);

          lives--;
          hangman++;
          hangmanImage.style.backgroundImage = `url(../images/hangman-${hangman}.png)`;

          playIncorrectAnswerAudio();
        }

        const isWordGuessed = response.word.wordGuessed;

        trackGameState(isWordGuessed);

        if (isWordGuessed) {
          totalCorrectAnswers++;
          score += correctAnswerPoints;
          displayPoints();
          setTimeout(() => {
            fetchWord(currentDifficulty);
          }, 1000);
        }

        enableKeyboardKey();
      });

      key.dataset.listenerAdded = true;
    }
  });
}

async function trackGameState(isWordGuessed) {
  const remainingWords = await checkRemainingWords();
  console.log("Is word guessed:", isWordGuessed);

  const modal = document.getElementById("game-modal");
  const modalTitle = document.getElementById("modal-title");
  const modalScore = document.getElementById("modal-score");

  console.log(lives);

  if (lives <= 0) {
    modalTitle.textContent = "Game Over!";
    modalScore.textContent = score;
    modal.style.display = "block";
    savePlayerScore();
    savePlayerTime();

    playGameOverAudio();
    modal.style.display = "block";

    console.log("Game Over!");
    stopTimer();

    await checkAndUpdateLeaderboardEntry();
    return;
  }

  if (remainingWords <= 0 && isWordGuessed) {
    modalTitle.textContent = "You Passed!";
    modalScore.textContent = score;
    modal.style.display = "block";
    savePlayerScore();
    savePlayerTime();

    playGameWinAudio();
    modal.style.display = "block";

    console.log("You Passed!");
    stopTimer();

    await checkAndUpdateLeaderboardEntry();
    return;
  }
}

function disableKeyboardKey() {
  const keyboardKeys = document.querySelectorAll(".keyboard-key");
  keyboardKeys.forEach((key) => {
    key.style.pointerEvents = "none";
  });
}

function enableKeyboardKey() {
  const keyboardKeys = document.querySelectorAll(".keyboard-key");
  keyboardKeys.forEach((key) => {
    key.style.pointerEvents = "auto";
  });
}

function displayPoints() {
  const scoreElement = document.querySelector(".score-number");
  scoreElement.textContent = score;
}

function disableKeyboardKey() {
  const keyboardKeys = document.querySelectorAll(".keyboard-key");
  keyboardKeys.forEach((key) => {
    key.style.pointerEvents = "none";
  });
}

async function updateGuess(letter) {
  try {
    const response = await fetch(`/api/words/guess?letter=${letter}`, {
      method: "PUT",
    });

    if (!response.ok) {
      throw new Error("Network response error, status: " + response.status);
    }

    const result = await response.json();
    return result;
  } catch (error) {
    console.error("Error checking letter:", error);
  }
}

async function restartGame() {
  try {
    const response = await fetch(`/api/words/restart`, {
      method: "POST",
    });

    if (!response.ok) {
      throw new Error("Network response error, status: " + response.status);
    }

    console.log("Game restarted");

    fetchWord(currentDifficulty);
  } catch (error) {
    console.error("Error restarting game:", error);
  }
}

function createKeyboard() {
  const keyboardContainer = document.querySelector(".keyboard-container");
  const letter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

  letter.split("").forEach((letter) => {
    const keyElement = document.createElement("div");
    keyElement.className = "keyboard-key";
    keyElement.textContent = letter;

    keyboardContainer.appendChild(keyElement);
  });
}

function playAgain() {
  playButtonAudio();
  const modal = document.getElementById("game-modal");
  hangmanImage.style.backgroundImage = `url(../images/hangman-default.png)`;
  hangman = 0;
  modal.style.display = "none";
  totalCorrectAnswers = 0;
  score = 0;
  lives = 6;
  minutes = 0;
  seconds = 0;
  document.querySelector(".timer-number").textContent = "0:00";
  startTimer();
  restartGame();
}

function backToMenu() {
  playButtonAudio();
  setTimeout(() => {
    window.location.href = "index.html";
  }, 200);
}

window.onload = () => {
  startCountdown();
  const currentState = loadPlayerState();
  console.log(currentState);
};

function backToGameDifficulty() {
  playButtonAudio();
  setTimeout(() => {
    window.location.href = "word-difficulty.html";
  }, 200);
}
