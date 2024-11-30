function selectGameType(gameType) {
  if (gameType == 'question') {
    const currentState = loadPlayerState();
    currentState.gameType = 'question';
    savePlayerState(currentState);

    setTimeout(() => {
      window.location.href = "question-difficulty.html";
    }, 200);
  }
  else if (gameType == 'word') {
    const currentState = loadPlayerState();
    currentState.gameType = 'word';
    savePlayerState(currentState);
    
    setTimeout(() => {
      window.location.href = "word-difficulty.html";
    }, 200);
  }
}

window.onload = () => {
  const currentState = loadPlayerState();
  console.log(currentState);
};

function backToMenu() {
  playButtonAudio();
  setTimeout(() => {
    window.location.href = "index.html";
  }, 200);
}
