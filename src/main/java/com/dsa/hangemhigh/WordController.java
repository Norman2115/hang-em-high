package com.dsa.hangemhigh;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/words")
public class WordController {
  private WordService wordService;
  private Word currentWord;

  public WordController(WordService wordService) {
    this.wordService = wordService;
  }

  @GetMapping("/{difficulty}")
  public ResponseEntity<Word> getWord(@PathVariable String difficulty) {
    currentWord = wordService.getShuffledWords(difficulty).poll();

    if (currentWord == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(currentWord);
  }

  @GetMapping("/remaining")
  public ResponseEntity<Integer> getRemainingWords() {
    if (currentWord == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(wordService.getShuffledWords(currentWord.getDifficulty()).size());
  }

  @PutMapping("/guess")
  public ResponseEntity<Map<String, Object>> updateGuess(@RequestParam char letter) {
    if (currentWord == null) {
      return ResponseEntity.notFound().build();
    }

    boolean result = currentWord.updateCurrentGuess(letter);

    Map<String, Object> response = new HashMap<>();
    response.put("isCorrect", result);
    response.put("word", currentWord);

    return ResponseEntity.ok(response);
  }

  @PostMapping("/restart")
  public void restart() {
    wordService.resetWords();
  }
}
