let currentQuestionDifficulty = "easy";
let currentWordDifficulty = "easy";

function setActiveQuestionButton(button) {
  const buttons = document.querySelectorAll(".question-button");

  buttons.forEach((btn) => {
    btn.classList.remove("difficulty-button-active");
  });

  button.classList.add("difficulty-button-active");
}

function setActiveWordButton(button) {
  const buttons = document.querySelectorAll(".word-button");

  buttons.forEach((btn) => {
    btn.classList.remove("difficulty-button-active");
  });

  button.classList.add("difficulty-button-active");
}

function setGridHeader(type) {
  let leaderboardContainer;

  if (type === "question") {
    leaderboardContainer = document.querySelector(
      ".leaderboard-question-container"
    );
  } else if (type === "word") {
    leaderboardContainer = document.querySelector(
      ".leaderboard-word-container"
    );
  }

  leaderboardContainer.innerHTML = "";
  let header;

  if (type === "question") {
    header = document.createElement("div");
    header.className = "leaderboard-row-header-question";
    const rank = document.createElement("div");
    rank.className = "leaderboard-rank";
    rank.textContent = "Rank";
    const name = document.createElement("div");
    name.className = "leaderboard-name";
    name.textContent = "Name";
    const score = document.createElement("div");
    score.className = "leaderboard-score";
    score.textContent = "Score";

    header.appendChild(rank);
    header.appendChild(name);
    header.appendChild(score);
  } else if (type === "word") {
    header = document.createElement("div");
    header.className = "leaderboard-row-header-word";
    const rank = document.createElement("div");
    rank.className = "leaderboard-rank";
    rank.textContent = "Rank";
    const name = document.createElement("div");
    name.className = "leaderboard-name";
    name.textContent = "Name";
    const score = document.createElement("div");
    score.className = "leaderboard-score";
    score.textContent = "Score";
    const time = document.createElement("div");
    time.className = "leaderboard-time";
    time.textContent = "Time";

    header.appendChild(rank);
    header.appendChild(name);
    header.appendChild(score);
    header.appendChild(time);
  }

  leaderboardContainer.appendChild(header);
}

async function setLeaderboard(type, difficulty) {
  if (type === "question") {
    currentQuestionDifficulty = difficulty;
    console.log("Current question difficulty:", currentQuestionDifficulty);
    await setQuestionLeaderboard(difficulty);
  } else if (type === "word") {
    currentWordDifficulty = difficulty;
    console.log("Current word difficulty:", currentWordDifficulty);
    await setWordLeaderboard(difficulty);
  }
}

async function setQuestionLeaderboard(difficulty) {
  const leaderboardContainer = document.querySelector(
    ".leaderboard-question-container"
  );

  leaderboardContainer.innerHTML = "";

  setGridHeader("question");

  const leaderboardEntries = await getQuestionLeaderboard(difficulty);

  if (leaderboardEntries.length === 0 || !leaderboardEntries) {
    return;
  }

  leaderboardEntries.forEach((entry, index) => {
    const row = document.createElement("div");
    row.className = "leaderboard-row-question";
    const rank = document.createElement("div");
    rank.className = "leaderboard-rank";
    rank.textContent = index + 1;
    const name = document.createElement("div");
    name.className = "leaderboard-name";
    name.textContent = entry.name;
    const score = document.createElement("div");
    score.className = "leaderboard-score";
    score.textContent = entry.score;

    row.appendChild(rank);
    row.appendChild(name);
    row.appendChild(score);

    leaderboardContainer.appendChild(row);
  });
}

async function setWordLeaderboard(difficulty) {
  const leaderboardContainer = document.querySelector(
    ".leaderboard-word-container"
  );

  leaderboardContainer.innerHTML = "";

  setGridHeader("word");

  const leaderboardEntries = await getWordLeaderboard(difficulty);

  if (leaderboardEntries.length === 0 || !leaderboardEntries) {
    return;
  }

  leaderboardEntries.forEach((entry, index) => {
    const row = document.createElement("div");
    row.className = "leaderboard-row-word";
    const rank = document.createElement("div");
    rank.className = "leaderboard-rank";
    rank.textContent = index + 1;
    const name = document.createElement("div");
    name.className = "leaderboard-name";
    name.textContent = entry.name;
    const score = document.createElement("div");
    score.className = "leaderboard-score";
    score.textContent = entry.score;
    const time = document.createElement("div");
    time.className = "leaderboard-time";

    if (entry.time === 0) {
      time.textContent = "N/A";
    } else {
      let minutes = Math.floor(entry.time / 60);
      let seconds = entry.time - minutes * 60;

      time.textContent = `${minutes}:${seconds < 10 ? "0" + seconds : seconds}`;
    }

    row.appendChild(rank);
    row.appendChild(name);
    row.appendChild(score);
    row.appendChild(time);

    leaderboardContainer.appendChild(row);
  });
}

async function getQuestionLeaderboard(difficulty) {
  try {
    let response;

    if (difficulty === "easy") {
      response = await fetch("/api/leaderboard/get/easy");
    } else if (difficulty === "medium") {
      response = await fetch("/api/leaderboard/get/medium");
    } else if (difficulty === "hard") {
      response = await fetch("/api/leaderboard/get/hard");
    }

    if (response.status === 404) {
      console.log("No data available for the selected difficulty.");
      return [];
    } else if (!response.ok) {
      throw new Error("Error getting leaderboard entries");
    }

    const leaderboardEntries = await response.json();

    console.log("Leaderboard entries:", leaderboardEntries);
    return leaderboardEntries;
  } catch (error) {
    console.error("Error getting leaderboard entries", error);
  }
}

async function getWordLeaderboard(difficulty) {
  try {
    let response;

    if (difficulty === "easy") {
      response = await fetch("/api/leaderboard/word/get/easy");
    } else if (difficulty === "medium") {
      response = await fetch("/api/leaderboard/word/get/medium");
    } else if (difficulty === "hard") {
      response = await fetch("/api/leaderboard/word/get/hard");
    }

    if (response.status === 404) {
      console.log("No data available for the selected difficulty.");
      return [];
    } else if (!response.ok) {
      throw new Error("Error getting leaderboard entries");
    }

    const leaderboardEntries = await response.json();

    console.log("Leaderboard entries:", leaderboardEntries);
    return leaderboardEntries;
  } catch (error) {
    console.error("Error getting leaderboard entries", error);
  }
}

async function handleQuestionSearchOnEnter(event) {
  if (event.key === "Enter") {
    const name = document.querySelector(".search-bar").value;
    await searchLeaderboard("question", name);
  }
}

async function handleWordSearchOnEnter(event) {
  if (event.key === "Enter") {
    const name = document.getElementById("searchWordInput").value;
    await searchLeaderboard("word", name);
  }
}

const searchQuestionInput = document.getElementById("searchQuestionInput");
searchQuestionInput.addEventListener("keypress", handleQuestionSearchOnEnter);

const searchWordInput = document.getElementById("searchWordInput");
searchWordInput.addEventListener("keypress", handleWordSearchOnEnter);

async function searchLeaderboard(type, name) {
  if (type === "question") {
    await searchQuestionLeaderboard(currentQuestionDifficulty, name);
  } else if (type === "word") {
    await searchWordLeaderboard(currentWordDifficulty, name);
  }
}

async function searchQuestionLeaderboard(difficulty, searchInput) {
  const leaderboardContainer = document.querySelector(
    ".leaderboard-row-grid-container"
  );

  if (!searchInput || searchInput === "") {
    setLeaderboard("question", difficulty);
    return;
  }

  leaderboardContainer.innerHTML = "";

  setGridHeader("question");

  console.log("Searching for:", searchInput);

  const leaderboardEntry = await getQuestionLeaderboardEntryByName(
    difficulty,
    searchInput
  );

  if (leaderboardEntry.length === 0 || !leaderboardEntry) {
    return;
  }

  const row = document.createElement("div");
  row.className = "leaderboard-row-question";
  const rank = document.createElement("div");
  rank.className = "leaderboard-rank";
  rank.textContent = cachedQuestionRank ? cachedQuestionRank : "-";
  const name = document.createElement("div");
  name.className = "leaderboard-name";
  name.textContent = leaderboardEntry.name;
  const score = document.createElement("div");
  score.className = "leaderboard-score";
  score.textContent = leaderboardEntry.score;

  row.appendChild(rank);
  row.appendChild(name);
  row.appendChild(score);

  leaderboardContainer.appendChild(row);
}

async function searchWordLeaderboard(difficulty, searchInput) {
  const leaderboardContainer = document.querySelector(
    ".leaderboard-word-container"
  );

  if (!searchInput || searchInput === "") {
    setLeaderboard("word", difficulty);
    return;
  }

  leaderboardContainer.innerHTML = "";

  setGridHeader("word");

  const leaderboardEntry = await getWordLeaderboardEntryByName(
    difficulty,
    searchInput
  );

  if (leaderboardEntry.length === 0 || !leaderboardEntry) {
    return;
  }

  const row = document.createElement("div");
  row.className = "leaderboard-row-word";
  const rank = document.createElement("div");
  rank.className = "leaderboard-rank";
  rank.textContent = cachedWordRank ? cachedWordRank : "-";
  const name = document.createElement("div");
  name.className = "leaderboard-name";
  name.textContent = leaderboardEntry.name;
  const score = document.createElement("div");
  score.className = "leaderboard-score";
  score.textContent = leaderboardEntry.score;
  const time = document.createElement("div");
  time.className = "leaderboard-time";

  if (leaderboardEntry.time === 0) {
    time.textContent = "N/A";
  } else {
    let minutes = Math.floor(leaderboardEntry.time / 60);
    let seconds = leaderboardEntry.time - minutes * 60;

    time.textContent = `${minutes}:${seconds < 10 ? "0" + seconds : seconds}`;
  }

  row.appendChild(rank);
  row.appendChild(name);
  row.appendChild(score);
  row.appendChild(time);

  leaderboardContainer.appendChild(row);
}

let cachedQuestionRank;
let cachedWordRank;

async function fetchAndCacheQuestionRank(difficulty, name) {
  try {
    let response;

    if (difficulty === "easy") {
      response = await fetch("/api/leaderboard/get/easy");
    } else if (difficulty === "medium") {
      response = await fetch("/api/leaderboard/get/medium");
    } else if (difficulty === "hard") {
      response = await fetch("/api/leaderboard/get/hard");
    }

    if (response.status === 404) {
      console.log("No data available for the selected difficulty.");
      return [];
    } else if (!response.ok) {
      throw new Error("Error getting leaderboard entries");
    }
    const leaderboardEntries = await response.json();
    console.log("Leaderboard entries fetched and cached:", leaderboardEntries);

    const index = leaderboardEntries.findIndex((entry) => entry.name === name);
    cachedQuestionRank = index === -1 ? null : index + 1;
  } catch (error) {
    console.error("Error getting leaderboard entries", error);
  }
}

async function fetchAndCacheWordRank(difficulty, name) {
  try {
    let response;

    if (difficulty === "easy") {
      response = await fetch("/api/leaderboard/word/get/easy");
    } else if (difficulty === "medium") {
      response = await fetch("/api/leaderboard/word/get/medium");
    } else if (difficulty === "hard") {
      response = await fetch("/api/leaderboard/word/get/hard");
    }

    if (response.status === 404) {
      console.log("No data available for the selected difficulty.");
      return [];
    } else if (!response.ok) {
      throw new Error("Error getting leaderboard entries");
    }

    console.log("Leaderboard entries fetched and cached");
    const leaderboardEntries = await response.json();

    const index = leaderboardEntries.findIndex((entry) => entry.name === name);
    cachedWordRank = index === -1 ? null : index + 1;
  } catch (error) {
    console.error("Error getting leaderboard entries", error);
  }
}

async function getQuestionLeaderboardEntryByName(difficulty, name) {
  try {
    await fetchAndCacheQuestionRank(difficulty, name);
    let response;

    if (difficulty === "easy") {
      response = await fetch(`/api/leaderboard/get/easy/${name}`);
    } else if (difficulty === "medium") {
      response = await fetch(`/api/leaderboard/get/medium/${name}`);
    } else if (difficulty === "hard") {
      response = await fetch(`/api/leaderboard/get/hard/${name}`);
    }

    if (response.status === 404) {
      console.log("No data available for the input name and difficulty.");
      return [];
    } else if (!response.ok) {
      throw new Error("Error getting leaderboard entries");
    }

    const leaderboardEntry = await response.json();

    console.log("Leaderboard entry:", leaderboardEntry);
    return leaderboardEntry;
  } catch (error) {
    console.error("Error getting leaderboard entries", error);
  }
}

async function getWordLeaderboardEntryByName(difficulty, name) {
  try {
    await fetchAndCacheWordRank(difficulty, name);
    let response;

    if (difficulty === "easy") {
      response = await fetch(`/api/leaderboard/word/get/easy/${name}`);
    } else if (difficulty === "medium") {
      response = await fetch(`/api/leaderboard/word/get/medium/${name}`);
    } else if (difficulty === "hard") {
      response = await fetch(`/api/leaderboard/word/get/hard/${name}`);
    }

    if (response.status === 404) {
      console.log("No data available for the input name and difficulty.");
      return [];
    } else if (!response.ok) {
      throw new Error("Error getting leaderboard entries");
    }

    const leaderboardEntry = await response.json();

    console.log("Leaderboard entry:", leaderboardEntry);
    return leaderboardEntry;
  } catch (error) {
    console.error("Error getting leaderboard entries", error);
  }
}

async function setProfile() {
  const currentState = loadPlayerState();
  console.log("Current state:", currentState);
  const sessionId = currentState.sessionId;

  const profileName = document.querySelector(".profile-name");
  profileName.textContent = currentState.name;

  const questionScores = await fetchQuestionProfileScores(sessionId);

  const questionEasyScore = document.getElementById("questionEasyScore");
  const questionMediumScore = document.getElementById("questionMediumScore");
  const questionHardScore = document.getElementById("questionHardScore");

  questionEasyScore.textContent =
    questionScores.easy === -1 ? "-" : questionScores.easy;
  questionMediumScore.textContent =
    questionScores.medium === -1 ? "-" : questionScores.medium;
  questionHardScore.textContent =
    questionScores.hard === -1 ? "-" : questionScores.hard;

  const wordScores = await fetchWordProfileScores(sessionId);

  const wordEasyScore = document.getElementById("wordEasyScore");
  const wordMediumScore = document.getElementById("wordMediumScore");
  const wordHardScore = document.getElementById("wordHardScore");

  wordEasyScore.textContent = wordScores.easy === -1 ? "-" : wordScores.easy;
  wordMediumScore.textContent =
    wordScores.medium === -1 ? "-" : wordScores.medium;
  wordHardScore.textContent = wordScores.hard === -1 ? "-" : wordScores.hard;
}

async function fetchQuestionProfileScores(sessionId) {
  try {
    const response = await fetch(
      `/api/leaderboard/get/profile/${encodeURIComponent(sessionId)}`
    );

    if (response.status === 404) {
      console.log("No data available for the input name and difficulty.");
      return [];
    } else if (!response.ok) {
      throw new Error("Error getting leaderboard entries");
    }

    const scores = await response.json();

    console.log("Profile scores:", scores);
    return scores;
  } catch (error) {
    console.error("Error fetching profile scores:", error);
  }
}

async function fetchWordProfileScores(sessionId) {
  try {
    const response = await fetch(
      `/api/leaderboard/word/get/profile/${encodeURIComponent(sessionId)}`
    );

    if (response.status === 404) {
      console.log("No data available for the input name and difficulty.");
      return [];
    } else if (!response.ok) {
      throw new Error("Error getting leaderboard entries");
    }

    const scores = await response.json();

    console.log("Profile scores:", scores);
    return scores;
  } catch (error) {
    console.error("Error fetching profile scores:", error);
  }
}

function backToMainMenu() {
  playButtonAudio();
  setTimeout(() => {
    window.location.href = "index.html";
  }, 200);
}

window.onload = () => {
  setLeaderboard("question", "easy");
  setLeaderboard("word", "easy");
  setProfile();
};
