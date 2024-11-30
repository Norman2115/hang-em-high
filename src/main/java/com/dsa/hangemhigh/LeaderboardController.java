package com.dsa.hangemhigh;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardController {
  private final FirestoreService firestoreService;

  public LeaderboardController(FirestoreService firestoreService) {
    this.firestoreService = firestoreService;
  }

  @GetMapping("/get/{sessionId}")
  public ResponseEntity<LeaderboardEntry> getLeaderboardEntry(@PathVariable String sessionId) {
    LeaderboardEntry leaderboardEntry = firestoreService.getLeaderboardEntry(sessionId);

    if (leaderboardEntry == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(leaderboardEntry);
  }

  @GetMapping("/get/easy")
  public ResponseEntity<List<LeaderboardEntry>> getLeaderboardEasyLevel() {
    List<LeaderboardEntry> leaderboardEntries = firestoreService.getLeaderboardEasyLevel();

    if (leaderboardEntries.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    
    return ResponseEntity.ok(leaderboardEntries);
  }

  @GetMapping("/get/medium")
  public ResponseEntity<List<LeaderboardEntry>> getLeaderboardMediumLevel() {
    List<LeaderboardEntry> leaderboardEntries = firestoreService.getLeaderboardMediumLevel();

    if (leaderboardEntries.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(leaderboardEntries);
  }

  @GetMapping("/get/hard")
  public ResponseEntity<List<LeaderboardEntry>> getLeaderboardHardLevel() {
    List<LeaderboardEntry> leaderboardEntries = firestoreService.getLeaderboardHardLevel();

    if (leaderboardEntries.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(leaderboardEntries);
  }

  @GetMapping("/get/profile/{sessionId}")
  public ResponseEntity<Map<String, Integer>> getProfileLeaderboard(@PathVariable String sessionId) {
    Map<String, Integer> profileLeaderboard = firestoreService.getProfileLeaderboard(sessionId);

    if (profileLeaderboard.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(profileLeaderboard);
  }

  @GetMapping("/get/easy/{name}")
  public ResponseEntity<LeaderboardEntry> getLeaderboardEasyLevelByName(@PathVariable String name) {
    LeaderboardEntry leaderboardEntry = firestoreService.getLeaderboardEasyLevelEntryByName(name);

    if (leaderboardEntry == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(leaderboardEntry);
  }

  @GetMapping("/get/medium/{name}")
  public ResponseEntity<LeaderboardEntry> getLeaderboardMediumLevelByName(@PathVariable String name) {
    LeaderboardEntry leaderboardEntry = firestoreService.getLeaderboardMediumLevelEntryByName(name);

    if (leaderboardEntry == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(leaderboardEntry);
  }

  @GetMapping("/get/hard/{name}")
  public ResponseEntity<LeaderboardEntry> getLeaderboardHardLevelByName(@PathVariable String name) {
    LeaderboardEntry leaderboardEntry = firestoreService.getLeaderboardHardLevelEntryByName(name);

    if (leaderboardEntry == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(leaderboardEntry);
  }

  @PostMapping("/add")
  public void addLeaderboardEntry(
      @RequestParam String sessionId,
      @RequestParam String name,
      @RequestParam int score,
      @RequestParam String gameType,
      @RequestParam String difficulty) {
    firestoreService.addLeaderboardEntry(sessionId, name, score, gameType, difficulty);
  }

  @PutMapping("/update/{sessionId}")
  public void updateLeaderboardEntry(@PathVariable String sessionId, @RequestParam String name,
      @RequestParam int score) {
    firestoreService.updateLeaderboardEntry(sessionId, name, score);
  }

  @PutMapping("/update/{sessionId}/name")
  public void updateLeaderboardEntryName(@PathVariable String sessionId, @RequestParam String name) {
    firestoreService.updateLeaderboardEntryName(sessionId, name);
  }

  @GetMapping("/get/{name}/exists")
  public ResponseEntity<Boolean> isNameExists(@PathVariable String name) {
    return ResponseEntity.ok(firestoreService.isNameExists(name));
  }

  //// For Word-based Leaderboard

  @GetMapping("/word/get/{sessionId}")
  public ResponseEntity<WordLeaderboardEntry> getWordLeaderboardEntry(@PathVariable String sessionId) {
    WordLeaderboardEntry wordLeaderboardEntry = firestoreService.getWordLeaderboardEntry(sessionId);

    if (wordLeaderboardEntry == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(wordLeaderboardEntry);
  }

  @GetMapping("/word/get/easy")
  public ResponseEntity<List<WordLeaderboardEntry>> getWordLeaderboardEasyLevel() {
    List<WordLeaderboardEntry> wordLeaderboardEntries = firestoreService.getWordLeaderboardEasyLevel();

    if (wordLeaderboardEntries.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(wordLeaderboardEntries);
  }

  @GetMapping("/word/get/medium")
  public ResponseEntity<List<WordLeaderboardEntry>> getWordLeaderboardMediumLevel() {
    List<WordLeaderboardEntry> wordLeaderboardEntries = firestoreService.getWordLeaderboardMediumLevel();

    if (wordLeaderboardEntries.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(wordLeaderboardEntries);
  }

  @GetMapping("/word/get/hard")
  public ResponseEntity<List<WordLeaderboardEntry>> getWordLeaderboardHardLevel() {
    List<WordLeaderboardEntry> wordLeaderboardEntries = firestoreService.getWordLeaderboardHardLevel();

    if (wordLeaderboardEntries.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(wordLeaderboardEntries);
  }

  @GetMapping("/word/get/profile/{sessionId}")
  public ResponseEntity<Map<String, Integer>> getWordProfileLeaderboard(@PathVariable String sessionId) {
    Map<String, Integer> profileLeaderboard = firestoreService.getWordProfileLeaderboard(sessionId);

    if (profileLeaderboard.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(profileLeaderboard);
  }

  @GetMapping("/word/get/easy/{name}")
  public ResponseEntity<WordLeaderboardEntry> getWordLeaderboardEasyLevelByName(@PathVariable String name) {
    WordLeaderboardEntry wordLeaderboardEntry = firestoreService.getWordLeaderboardEasyLevelEntryByName(name);

    if (wordLeaderboardEntry == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(wordLeaderboardEntry);
  }

  @GetMapping("/word/get/medium/{name}")
  public ResponseEntity<WordLeaderboardEntry> getWordLeaderboardMediumLevelByName(@PathVariable String name) {
    WordLeaderboardEntry wordLeaderboardEntry = firestoreService.getWordLeaderboardMediumLevelEntryByName(name);

    if (wordLeaderboardEntry == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(wordLeaderboardEntry);
  }

  @GetMapping("/word/get/hard/{name}")
  public ResponseEntity<WordLeaderboardEntry> getWordLeaderboardHardLevelByName(@PathVariable String name) {
    WordLeaderboardEntry wordLeaderboardEntry = firestoreService.getWordLeaderboardHardLevelEntryByName(name);

    if (wordLeaderboardEntry == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(wordLeaderboardEntry);
  }

  @PostMapping("/word/add")
  public void addWordLeaderboardEntry(
      @RequestParam String sessionId,
      @RequestParam String name,
      @RequestParam int score,
      @RequestParam String gameType,
      @RequestParam String difficulty,
      @RequestParam int time) {
    firestoreService.addWordLeaderboardEntry(sessionId, name, score, gameType, difficulty, time);
  }

  @PutMapping("/word/update/{sessionId}")
  public void updateWordLeaderboardEntry(@PathVariable String sessionId, @RequestParam String name,
      @RequestParam int score,
      @RequestParam int time) {
    firestoreService.updateWordLeaderboardEntry(sessionId, name, score, time);
  }

  @PutMapping("/word/update/{sessionId}/name")
  public void updateWordLeaderboardEntryName(@PathVariable String sessionId, @RequestParam String name) {
    firestoreService.updateWordLeaderboardEntryName(sessionId, name);
  }

  @GetMapping("/word/get/{name}/exists")
  public ResponseEntity<Boolean> isWordNameExists(@PathVariable String name) {
    return ResponseEntity.ok(firestoreService.isWordNameExists(name));
  }
}
