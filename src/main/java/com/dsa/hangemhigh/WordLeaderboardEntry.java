package com.dsa.hangemhigh;

public class WordLeaderboardEntry extends LeaderboardEntry {
  private int time;

  public WordLeaderboardEntry() {
    // Required for Firestore deserialization
  }
  
  public WordLeaderboardEntry(String id, String name, int score, String gameType, String difficulty, int time) {
    super(id, name, score, gameType, difficulty);
    this.time = time;
  }

  public int getTime() {
    return time;
  }

  public void setTime(int time) {
    this.time = time;
  }
}
