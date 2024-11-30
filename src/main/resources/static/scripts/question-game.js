// Difficulty levels
let currentDifficulty = getDifficulty();
console.log(currentDifficulty);

// To track questions
const totalQuestions = 20;
let totalCorrectAnswers = 0;

// Score variables
const correctAnswerPoints = 10;
const incorrectAnswerPoints = 5;
const streakBonus = 5;
let streak = 0;
let score = 0;

// Lives
let lives = 6;
let hangman = 0;
const hangmanImage = document.querySelector(".hang-man-image");

// Sound variables for streaks
let playSoundSinceLastStreak = 0;
const playSoundLimitForStreak = 2;

// Timer variables
let timerValue = 20;
let timerInterval;
const timerTextElement = document.querySelector(".timer-number");

// Countdown variables
let countdownValue = 3;
let countdownInterval;
const countdownTextElement = document.querySelector(".countdown-text");

// Fetch number of remaining questions from server
async function checkRemainingQuestions() {
  try {
    const response = await fetch(`/api/questions/remaining`);

    const questions = await response.json();

    if (!response.ok) {
      throw new Error("Network response error, status: " + response.status);
    }

    console.log("Remaining questions:", questions);
    return questions;
  } catch (error) {
    console.error("Error checking remaining questions:", error);
  }
}

// Fetch question from server
async function fetchQuestion(difficulty) {
  try {
    const remainingQuestions = await checkRemainingQuestions();

    const modal = document.getElementById("game-modal");
    const modalTitle = document.getElementById("modal-title");
    const modalScore = document.getElementById("modal-score");

    if (lives === 0) {
      modalTitle.textContent = "Game Over!";
      modalScore.textContent = score;
      savePlayerScore();

      playGameOverAudio();
      modal.style.display = "block";

      console.log("Game over!");
      clearInterval(timerInterval);

      await checkAndUpdateLeaderboardEntry();
      return;
    }

    if (remainingQuestions === 0) {
      modalTitle.textContent = "You Passed!";
      modalScore.textContent = score;
      savePlayerScore();

      playGameWinAudio();
      modal.style.display = "block";

      console.log("You passed!");
      clearInterval(timerInterval);

      await checkAndUpdateLeaderboardEntry();
      return;
    }

    const response = await fetch(`/api/questions/${difficulty}`);

    if (!response.ok) {
      throw new Error("Network response error, status: " + response.status);
    }

    const question = await response.json();
    console.log(question);
    displayQuestion(question);
    startTimer();
  } catch (error) {
    console.error("Error fetching question:", error);
  }
}

function savePlayerScore() {
  const currentState = loadPlayerState();
  currentState.score = score;
  savePlayerState(currentState);
}

async function checkAndUpdateLeaderboardEntry() {
  try {
    const currentState = loadPlayerState();
    const sessionId =
      currentState.sessionId +
      "-" +
      currentState.difficulty +
      "-" +
      currentState.gameType;

    console.log("Session ID:", sessionId);
    const response = await fetch(`/api/leaderboard/get/${sessionId}`);

    if (response.ok) {
      const leaderboardEntry = await response.json();
      console.log("Leaderboard entry:", leaderboardEntry);

      if (score > leaderboardEntry.score) {
        await updateLeaderboardEntry(sessionId, currentState.name, score);
      } else {
        console.log("New score is not higher. No update needed.");
      }
    } else if (response.status === 404) {
      throw new Error("Leaderboard entry not found for session ID:", sessionId);
    } else {
      throw new Error("Error fetching leaderboard entry: " + response.status);
    }
  } catch (error) {
    console.error("Error checking and updating leaderboard entry:", error);
  }
}

async function updateLeaderboardEntry(sessionId, name, score) {
  const url = `/api/leaderboard/update/${encodeURIComponent(
    sessionId
  )}?name=${encodeURIComponent(name)}&score=${score}`;

  try {
    const response = await fetch(url, {
      method: "PUT",
    });

    if (!response.ok) {
      console.error("Error updating leaderboard entry:", response.status);
    }

    console.log("Leaderboard entry updated");
  } catch (error) {
    console.error("Error updating leaderboard entry:", error);
  }
}

// Countdown function before starting the game
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
      const container = document.querySelector(".countdown-container");
      container.style.display = "none";
    }
  }, 1000);
}

// Timer function to countdown from 30 seconds for each question
function startTimer() {
  timerValue = 20;
  timerTextElement.textContent = timerValue;

  clearInterval(timerInterval);
  timerInterval = setInterval(() => {
    timerValue--;
    timerTextElement.textContent = timerValue;

    if (timerValue <= 0) {
      streak = 0;
      lives--;
      hangman++;
      
      hangmanImage.style.backgroundImage = `url(../images/hangman-${hangman}.png)`;

      if (score >= incorrectAnswerPoints) {
        score -= incorrectAnswerPoints;
      } else {
        score = 0;
      }

      displayPoints();
      clearInterval(timerInterval);
      disableAnswerCards(document.querySelector(".answer-container"));
      console.log("Time's up!");
      fetchQuestion(currentDifficulty);
    }
  }, 1000);
}

function displayPoints() {
  const scoreElement = document.querySelector(".score-number");
  scoreElement.textContent = score;
}

// Display the question and answer options on the page
function displayQuestion(question) {
  const questionTextElement = document.querySelector(".question-text");
  const answerContainer = document.querySelector(".answer-container");

  questionTextElement.textContent = question.questionText;
  answerContainer.innerHTML = "";

  question.options.forEach((option) => {
    const answerCard = document.createElement("div");
    answerCard.className = "answer-card answer-text";
    answerCard.textContent = option;

    answerCard.addEventListener("click", () => {
      disableAnswerCards(answerContainer);

      if (option === question.correctAnswer) {
        playSoundSinceLastStreak++;

        if (streak > 0) {
          score += correctAnswerPoints + streak * streakBonus;
          streak++;
        } else {
          score += correctAnswerPoints;
          streak++;
        }

        if (playSoundSinceLastStreak >= playSoundLimitForStreak) {
          playStreakAudio();
          playSoundSinceLastStreak = 0;
        }

        displayPoints();
        totalCorrectAnswers++;
        answerCard.classList.add("correct");
        playCorrectAnswerAudio();
      } else {
        streak = 0;
        lives--;
        hangman++;
        hangmanImage.style.backgroundImage = `url(../images/hangman-${hangman}.png)`;

        if (score >= incorrectAnswerPoints) {
          score -= incorrectAnswerPoints;
        } else {
          score = 0;
        }

        displayPoints();

        answerCard.classList.add("incorrect");
        playIncorrectAnswerAudio();
        // Todo: Change HangMan image
      }

      setTimeout(() => {
        fetchQuestion(currentDifficulty);
      }, 500);
      clearInterval(timerInterval);
    });

    answerContainer.appendChild(answerCard);
  });
}

// Disable clicking on answer cards after selecting an answer
function disableAnswerCards(container) {
  const answerCards = container.querySelectorAll(".answer-card");
  answerCards.forEach((card) => {
    card.style.pointerEvents = "none";
  });
}

// Restart the game every time the page is loaded
async function restartGame() {
  try {
    const response = await fetch("/api/questions/restart", {
      method: "POST",
    });

    if (!response.ok) {
      console.error("Error restarting game");
    }

    console.log("Game restarted");

    fetchQuestion(currentDifficulty);
  } catch (error) {
    console.error("Error restarting game", error);
  }
}

// Fetch question when the page is loaded
window.onload = () => {
  startCountdown();
  const currentState = loadPlayerState();
  console.log(currentState);
};

// Restart the game and reset state variables
function playAgain() {
  playButtonAudio();
  hangmanImage.style.backgroundImage = `url(../images/hangman-default.png)`;
  hangman = 0;
  const modal = document.getElementById("game-modal");
  modal.style.display = "none";
  totalCorrectAnswers = 0;
  score = 0;
  streak = 0;
  playSoundSinceLastStreak = 0;
  lives = 6;
  restartGame();
}

function backToMenu() {
  playButtonAudio();
  setTimeout(() => {
    window.location.href = "index.html";
  }, 200);
}

function backToGameDifficulty() {
  playButtonAudio();
  setTimeout(() => {
    window.location.href = "question-difficulty.html";
  }, 200);
}
