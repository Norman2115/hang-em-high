const playerState = {
  sessionId: getSessionId(),
  name: "",
  score: 0,
  gameType: "",
  difficulty: "",
  time: 0, // for word game
};

function savePlayerState(state) {
  localStorage.setItem("playerState", JSON.stringify(state));
}

function loadPlayerState() {
  const playerStateString = localStorage.getItem("playerState");

  if (playerStateString) {
    return JSON.parse(playerStateString);
  } else {
    return playerState;
  }
}

function getSessionId() {
  if (!localStorage.getItem("sessionId")) {
    localStorage.setItem("sessionId", generateSessionId());
  }

  return localStorage.getItem("sessionId");
}

function generateSessionId() {
  const dateNow = new Date();

  const timestamp = dateNow.toISOString();

  const randomComponent =
    Math.random().toString(36).substring(2, 15) +
    Math.random().toString(36).substring(2, 15);

  return `${timestamp}-${randomComponent}`;
}

function playCountdownAudio() {
  const audioElement = document.getElementById("countdown-audio");
  audioElement.currentTime = 0;
  audioElement.volume = 0.8;
  audioElement.play();
}

function playButtonAudio() {
  const audioElement = document.getElementById("button-audio");
  audioElement.currentTime = 0;
  audioElement.volume = 0.8;
  audioElement.play();
}

function playGameWinAudio() {
  const audioElement = document.getElementById("game-win-audio");
  const audioElement2 = document.getElementById("game-win-audio-2");
  audioElement.currentTime = 0;
  audioElement.volume = 0.8;
  audioElement.play();
  audioElement.onended = () => {
    audioElement2.currentTime = 0;
    audioElement2.volume = 0.8;
    audioElement2.play();
  };
}

function playGameOverAudio() {
  const audioElement = document.getElementById("game-over-audio");
  audioElement.currentTime = 0;
  audioElement.volume = 0.8;
  audioElement.play();
}

function playStreakAudio() {
  const audioElement = [
    document.getElementById("streak-audio"),
    document.getElementById("streak-audio-2"),
    document.getElementById("streak-audio-3"),
  ];

  const randomIndex = Math.floor(Math.random() * audioElement.length);
  audioElement[randomIndex].currentTime = 0;
  audioElement[randomIndex].volume = 0.8;
  audioElement[randomIndex].play();
}

function playCorrectAnswerAudio() {
  const audioElement = document.getElementById("correct-audio");
  audioElement.currentTime = 0;
  audioElement.volume = 0.8;
  audioElement.play();
}

function playIncorrectAnswerAudio() {
  const audioElement = document.getElementById("wrong-audio");
  audioElement.currentTime = 0;
  audioElement.volume = 0.8;
  audioElement.play();
}

function setDifficulty(difficulty) {
  localStorage.setItem("difficulty", difficulty);
}

function getDifficulty() {
  return localStorage.getItem("difficulty") || "easy";
}
