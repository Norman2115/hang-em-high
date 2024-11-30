package com.dsa.hangemhigh;

import java.util.List;
import java.lang.String;

public class Question {
  private String questionText;
  private List<String> options;
  private String correctAnswer;
  private String difficulty;

  public Question(String questionText, List<String> options, String correctAnswer, String difficulty) {
    this.questionText = questionText;
    this.options = options;
    this.correctAnswer = correctAnswer;
    this.difficulty = difficulty;
  }

  public String getQuestionText() {
    return questionText;
  }

  public void setQuestionText(String questionText) {
    this.questionText = questionText;
  }

  public List<String> getOptions() {
    return options;
  }

  public void setOptions(List<String> options) {
    this.options = options;
  }

  public String getCorrectAnswer() {
    return correctAnswer;
  }

  public void setCorrectAnswer(String correctAnswer) {
    this.correctAnswer = correctAnswer;
  }

  public String getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(String difficulty) {
    this.difficulty = difficulty;
  }
}
