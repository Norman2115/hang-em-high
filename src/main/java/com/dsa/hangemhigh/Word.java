package com.dsa.hangemhigh;

import java.util.Map;
import java.util.HashMap;
import java.util.Random;

public class Word {
  // private String originalWord;
  private String word;
  private String hint;
  private Map<Character, Integer> charCount;
  private Map<Character, Integer> guessCount;
  private StringBuilder currentGuess;
  private String difficulty;

  public Word(String word, String hint, String difficulty) {
    // this.originalWord = word;
    this.word = word.toLowerCase().replace(" ", "");
    this.hint = hint;
    this.difficulty = difficulty;
    this.charCount = new HashMap<>();
    this.guessCount = new HashMap<>();
    this.currentGuess = new StringBuilder("_".repeat(this.word.length()));
    calculateCharCount();
    setCharacterHint();
  }

  private void calculateCharCount() {
    for (char c : word.toCharArray()) {
      charCount.put(c, charCount.getOrDefault(c, 0) + 1);
    }
  }

  private void setCharacterHint() {
    if (word.length() < 2) {
      return;
    }

    Random random = new Random();
    int firstHintIndex = random.nextInt(word.length());
    int secondHintIndex;

    do {
      secondHintIndex = random.nextInt(word.length());
    } while (secondHintIndex == firstHintIndex);

    // Store this hint in the current guess
    currentGuess.setCharAt(firstHintIndex, word.charAt(firstHintIndex));
    currentGuess.setCharAt(secondHintIndex, word.charAt(secondHintIndex));

    // Increment the count in the guess count
    guessCount.put(word.charAt(firstHintIndex), 1);
    guessCount.put(word.charAt(secondHintIndex), 1);
  }

  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  public String getHint() {
    return hint;
  }

  public void setHint(String hint) {
    this.hint = hint;
  }

  public Map<Character, Integer> getCharCount() {
    return charCount;
  }

  public String getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(String difficulty) {
    this.difficulty = difficulty;
  }

  public String getCurrentGuess() {
    // StringBuilder display = new StringBuilder();

    // for (int i = 0; i < originalWord.length(); i++) {
    //   if (originalWord.charAt(i) == ' ') {
    //     display.append(" ");
    //   } else {
    //     display.append(currentGuess.charAt(i));
    //   }
    // }

    // return display.toString();
    return currentGuess.toString();
  }

  public boolean updateCurrentGuess(char guessLetter) {
    // Convert to Lower Case
    guessLetter = Character.toLowerCase(guessLetter);

    if (!charCount.containsKey(guessLetter)) {
      return false;
    }

    // Max Occurences of the Letter in Original Word
    int maxOccurences = charCount.get(guessLetter);
    // Current Occurrences that have been guessed
    int currentOccurences = guessCount.getOrDefault(guessLetter, 0);

    // All Occurrences of the Letter have been guessed
    if (currentOccurences >= maxOccurences) {
      return false;
    }

    // If not guessed before, increment the count
    guessCount.put(guessLetter, currentOccurences + 1);

    // Update the current guess
    for (int i = 0; i < word.length(); i++) {
      if (word.charAt(i) == guessLetter && currentGuess.charAt(i) == '_') {
        currentGuess.setCharAt(i, guessLetter);
        break;
      }
    }

    return true;
  }

  public boolean isWordGuessed() {
    return !currentGuess.toString().contains("_");
  }
}
