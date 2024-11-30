async function startGame() {
  playButtonAudio();
  console.log("Starting game");

  const currentState = loadPlayerState();

  const name = document.querySelector(".name-input").value;

  if (!name || name.length === 0) {
    const errorMessageElement = document.querySelector(".error-message");
    errorMessageElement.textContent = "Please enter your name";
    errorMessageElement.style.display = "block";
    return;
  }

  const [isUnique, isBelongsToUser] = await Promise.all([
    isNameUnique(name),
    isNameBelongsToUser(currentState.sessionId, name),
  ]);

  console.log("Is unique:", isUnique);
  console.log("Is belongs to user:", isBelongsToUser);

  if (!isUnique && !isBelongsToUser) {
    const errorMessageElement = document.querySelector(".error-message");
    errorMessageElement.textContent = "Name already taken";
    errorMessageElement.style.display = "block";
    return;
  }

  currentState.name = name;

  document.querySelector(".error-message").style.display = "none";

  console.log("Player state:", currentState);
  savePlayerState(currentState);

  await checkAndUpdateLeaderboardEntry();

  setTimeout(() => {
    window.location.href = "game-type.html";
  }, 200);
}

function showHighScore() {
  playButtonAudio();
  const currentState = loadPlayerState();
  const name = currentState.name;
  console.log("Name:", name);

  setTimeout(() => {
    window.location.href = "leaderboard.html";
  }, 200);
}

function showAbout() {
  playButtonAudio();

  setTimeout(() => {
    window.location.href = "about.html";
  }, 200);
}

async function checkAndUpdateLeaderboardEntry() {
  try {
    const currentState = loadPlayerState();
    const sessionId = currentState.sessionId;
    // Make sure the name is fetched from input field
    const name = currentState.name;

    const difficulties = ["easy", "medium", "hard"];
    const gameTypes = [
      { type: "question", endpoint: "/api/leaderboard" },
      { type: "word", endpoint: "/api/leaderboard/word" },
    ];

    for (const game of gameTypes) {
      for (const difficulty of difficulties) {
        const specificSessionId =
          sessionId + "-" + difficulty + "-" + game.type;

        console.log("Session ID:", specificSessionId);

        const response = await fetch(
          `${game.endpoint}/get/${specificSessionId}`
        );

        if (response.ok) {
          const leaderboardEntry = await response.json();
          console.log("Leaderboard entry:", leaderboardEntry);

          if (leaderboardEntry.name === name) {
            console.log("Name matches existing entry, skipping update.");
            continue;
          }

          if (game.type === "word") {
            await updateLeaderboardEntry(
              specificSessionId,
              name,
              game.type,
              game.endpoint,
              leaderboardEntry.score,
              leaderboardEntry.time
            );
          } else {
            await updateLeaderboardEntry(
              specificSessionId,
              name,
              game.type,
              game.endpoint,
              leaderboardEntry.score
            );
          }
        } else if (response.status === 404) {
          await createLeaderboardEntry(
            specificSessionId,
            name,
            game.type,
            difficulty,
            game.endpoint
          );
        } else {
          throw new Error("Network response error, status: " + response.status);
        }
      }
    }
  } catch (error) {
    console.error("Error fetching leaderboard entry:", error);
  }
}

async function createLeaderboardEntry(
  sessionId,
  name,
  gameType,
  difficulty,
  endpoint
) {
  try {
    let url;

    if (gameType === "word") {
      url = `${endpoint}/add?sessionId=${encodeURIComponent(
        sessionId
      )}&name=${encodeURIComponent(
        name
      )}&score=${0}&gameType=${encodeURIComponent(
        gameType
      )}&difficulty=${encodeURIComponent(difficulty)}&time=${0}`;
    } else {
      url = `${endpoint}/add?sessionId=${encodeURIComponent(
        sessionId
      )}&name=${encodeURIComponent(
        name
      )}&score=${0}&gameType=${encodeURIComponent(
        gameType
      )}&difficulty=${encodeURIComponent(difficulty)}`;
    }

    const response = await fetch(url, {
      method: "POST",
    });

    if (!response.ok) {
      throw new Error("Error creating leaderboard entry:", response.status);
    }

    console.log("Leaderboard entry created for ", sessionId);
  } catch (error) {
    console.error("Error creating leaderboard entry:", error);
  }
}

async function updateLeaderboardEntry(
  sessionId,
  name,
  gameType,
  endpoint,
  score,
  time
) {
  try {
    console.log("Updating leaderboard entry name for ", sessionId);
    console.log("Game type:", gameType);

    let response;

    if (gameType === "word") {
      response = await fetch(
        `${endpoint}/update/${encodeURIComponent(
          sessionId
        )}?name=${encodeURIComponent(name)}&score=${score}&time=${time}`,
        {
          method: "PUT",
        }
      );
    } else {
      response = await fetch(
        `${endpoint}/update/${encodeURIComponent(
          sessionId
        )}?name=${encodeURIComponent(name)}&score=${score}`,
        {
          method: "PUT",
        }
      );
    }

    if (!response.ok) {
      throw new Error(
        "Error updating leaderboard entry name:",
        response.status
      );
    }

    console.log("Leaderboard entry name updated for ", sessionId);
  } catch (error) {
    console.error("Error updating leaderboard entry name:", error);
  }
}

async function isNameUnique(name) {
  try {
    // Since we will have all leaderboard entries for each user
    // We can check the uniqueness in one of the board
    const response = await fetch(
      `/api/leaderboard/get/${encodeURIComponent(name)}/exists`
    );

    if (!response.ok) {
      throw new Error("Network response error, status: " + response.status);
    }

    const result = await response.json();

    console.log("Result:", result);

    return !result;
  } catch (error) {
    console.error("Error checking name:", error);
  }
}

async function isNameBelongsToUser(sessionId, name) {
  try {
    // Since we will have all leaderboard entries for registered user,
    // We can check on one of them.
    const specificSessionId = sessionId + "-easy-question";

    const response = await fetch(
      `/api/leaderboard/get/${encodeURIComponent(specificSessionId)}`
    );

    if (!response.ok) {
      throw new Error("Network response error, status: " + response.status);
    }

    const leaderboardEntry = await response.json();
    return leaderboardEntry.name === name;
  } catch (error) {
    console.error("Error checking name:", error);
  }
}

async function fetchNameStartUp() {
  try {
    const currentState = loadPlayerState();
    const sessionId = currentState.sessionId;

    // Since we will have all leaderboard entries for registered user,
    // We can fetch from one of them.
    const specificSessionId = sessionId + "-easy-question";

    const response = await fetch(
      `/api/leaderboard/get/${encodeURIComponent(specificSessionId)}`
    );

    if (response.status === 404) {
      throw new Error("Leaderboard entry not found for session ID:", sessionId);
    } else if (!response.ok) {
      throw new Error("Network response error, status: " + response.status);
    }

    const leaderboardEntry = await response.json();
    return leaderboardEntry.name;
  } catch (error) {
    console.error("Error fetching name:", error);
  }
}

window.onload = async () => {
  const currentState = loadPlayerState();
  currentState.sessionId = getSessionId();
  savePlayerState(currentState);

  const name = (await fetchNameStartUp()) || "";
  const nameInput = document.querySelector(".name-input");
  nameInput.value = name;
};
