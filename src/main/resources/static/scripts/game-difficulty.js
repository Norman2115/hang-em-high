function startQuestionGame(difficulty) {
  const currentState = loadPlayerState();
  currentState.difficulty = difficulty;
  savePlayerState(currentState);

  setDifficulty(difficulty);
  setTimeout(() => {
    window.location.href = "question-game.html";
  }, 200);
}

function startWordGame(difficulty) {
  const currentState = loadPlayerState();
  currentState.difficulty = difficulty;
  savePlayerState(currentState);

  setDifficulty(difficulty);
  setTimeout(() => {
    window.location.href = "word-game.html";
  }, 200);
}

window.onload = () => {
  const currentState = loadPlayerState();
  console.log(currentState);
};

function backToGameType() {
  playButtonAudio();
  setTimeout(() => {
    window.location.href = "game-type.html";
  }, 200);
}