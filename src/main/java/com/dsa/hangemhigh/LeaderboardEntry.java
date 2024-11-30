package com.dsa.hangemhigh;

public class LeaderboardEntry {
  private String id;
  private String name;
  private int score;
  private String gameType;
  private String difficulty;

  public LeaderboardEntry() {
    // Required for Firestore deserialization
  }

  public LeaderboardEntry(String id, String name, int score, String gameType, String difficulty) {
    this.id = id;
    this.name = name;
    this.score = score;
    this.gameType = gameType;
    this.difficulty = difficulty;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public String getGameType() {
    return gameType;
  }

  public void setGameType(String gameType) {
    this.gameType = gameType;
  }

  public String getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(String difficulty) {
    this.difficulty = difficulty;
  }
}
