package com.dsa.hangemhigh;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FirestoreService {
  private final Firestore firestore;

  public FirestoreService() {
    this.firestore = FirestoreClient.getFirestore();
  }

  //// For Question-based Leaderboard

  public void addLeaderboardEntry(String sessionId, String name, int score, String gameType, String difficulty) {
    DocumentReference docRef = firestore.collection("leaderboard").document(sessionId);
    ApiFuture<WriteResult> result = docRef.set(new LeaderboardEntry(sessionId, name, score, gameType, difficulty));

    try {
      WriteResult writeResult = result.get();
      System.out.println("Added leaderboard entry for session: " + writeResult);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void updateLeaderboardEntry(String sessionId, String name, int score) {
    DocumentReference docRef = firestore.collection("leaderboard").document(sessionId);

    Map<String, Object> updates = new HashMap<>();
    updates.put("score", score);
    updates.put("name", name);

    ApiFuture<WriteResult> result = docRef.update(updates);

    try {
      WriteResult writeResult = result.get();
      System.out.println("Updated leaderboard entry for session: " + writeResult);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void updateLeaderboardEntryName(String sessionId, String name) {
    DocumentReference docRef = firestore.collection("leaderboard").document(sessionId);

    Map<String, Object> updates = new HashMap<>();
    updates.put("name", name);

    ApiFuture<WriteResult> result = docRef.update(updates);

    try {
      WriteResult writeResult = result.get();
      System.out.println("Updated leaderboard entry for session: " + writeResult);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public LeaderboardEntry getLeaderboardEntry(String sessionId) {
    DocumentReference docRef = firestore.collection("leaderboard").document(sessionId);
    ApiFuture<DocumentSnapshot> query = docRef.get();

    try {
      DocumentSnapshot document = query.get();
      return document.toObject(LeaderboardEntry.class);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

  public List<LeaderboardEntry> getLeaderboardEasyLevelUnsorted() {
    List<LeaderboardEntry> leaderboard = new ArrayList<>();
    ApiFuture<QuerySnapshot> query = firestore.collection("leaderboard").whereEqualTo("difficulty", "easy").get();

    try {
      query.get().getDocuments().forEach(document -> {
        LeaderboardEntry entry = document.toObject(LeaderboardEntry.class);
        leaderboard.add(entry);
      });
    } catch (Exception e) {
      e.printStackTrace();
    }

    return leaderboard;
  }

  public List<LeaderboardEntry> getLeaderboardEasyLevel() {
    List<LeaderboardEntry> leaderboard = new ArrayList<>();
    ApiFuture<QuerySnapshot> query = firestore.collection("leaderboard").whereEqualTo("difficulty", "easy").get();

    try {
      query.get().getDocuments().forEach(document -> {
        LeaderboardEntry entry = document.toObject(LeaderboardEntry.class);
        leaderboard.add(entry);
      });
    } catch (Exception e) {
      e.printStackTrace();
    }

    System.gc();
    double startTime = System.nanoTime();
    System.out.println("\nMerge sort by score");
    System.out.println("------------------------------");
    System.out.println("Start time\t: " + (startTime / 1_000_000) + " ms");

    mergeSort(leaderboard);

    double endTime = System.nanoTime();
    System.out.println("End time\t: " + (endTime / 1_000_000) + " ms");

    double timeDiff = endTime - startTime;
    System.out.println("Time taken\t: " + (timeDiff / 1_000_000) + " ms");
    System.out.println("------------------------------\n");

    return leaderboard;
  }

  public List<LeaderboardEntry> getLeaderboardMediumLevelUnsorted() {
    List<LeaderboardEntry> leaderboard = new ArrayList<>();
    ApiFuture<QuerySnapshot> query = firestore.collection("leaderboard").whereEqualTo("difficulty", "medium").get();

    try {
      query.get().getDocuments().forEach(document -> {
        LeaderboardEntry entry = document.toObject(LeaderboardEntry.class);
        leaderboard.add(entry);
      });
    } catch (Exception e) {
      e.printStackTrace();
    }

    return leaderboard;
  }

  public List<LeaderboardEntry> getLeaderboardMediumLevel() {
    List<LeaderboardEntry> leaderboard = new ArrayList<>();
    ApiFuture<QuerySnapshot> query = firestore.collection("leaderboard").whereEqualTo("difficulty", "medium").get();

    try {
      query.get().getDocuments().forEach(document -> {
        LeaderboardEntry entry = document.toObject(LeaderboardEntry.class);
        leaderboard.add(entry);
      });
    } catch (Exception e) {
      e.printStackTrace();
    }

    double startTime = System.nanoTime();
    System.out.println("Medium level leaderboard for Question Game");
    System.out.println("\n------------------------------");
    System.out.println("Start time of merge sort by score: " + startTime);

    mergeSort(leaderboard);

    double endTime = System.nanoTime();
    System.out.println("End time of merge sort by score: " + endTime);
    System.out.println("Time taken for merge sort by score: " + (endTime - startTime) + " ns");
    System.out.println("------------------------------\n");

    return leaderboard;
  }

  public List<LeaderboardEntry> getLeaderboardHardLevelUnsorted() {
    List<LeaderboardEntry> leaderboard = new ArrayList<>();
    ApiFuture<QuerySnapshot> query = firestore.collection("leaderboard").whereEqualTo("difficulty", "hard").get();

    try {
      query.get().getDocuments().forEach(document -> {
        LeaderboardEntry entry = document.toObject(LeaderboardEntry.class);
        leaderboard.add(entry);
      });
    } catch (Exception e) {
      e.printStackTrace();
    }

    return leaderboard;
  }

  public List<LeaderboardEntry> getLeaderboardHardLevel() {
    List<LeaderboardEntry> leaderboard = new ArrayList<>();
    ApiFuture<QuerySnapshot> query = firestore.collection("leaderboard").whereEqualTo("difficulty", "hard").get();

    try {
      query.get().getDocuments().forEach(document -> {
        LeaderboardEntry entry = document.toObject(LeaderboardEntry.class);
        leaderboard.add(entry);
      });
    } catch (Exception e) {
      e.printStackTrace();
    }

    double startTime = System.nanoTime();
    System.out.println("Hard level leaderboard for Question Game");
    System.out.println("\n------------------------------");
    System.out.println("Start time of merge sort by score: " + startTime);

    mergeSort(leaderboard);

    double endTime = System.nanoTime();
    System.out.println("End time of merge sort by score: " + endTime);
    System.out.println("Time taken for merge sort by score: " + (endTime - startTime) + " ns");
    System.out.println("------------------------------\n");

    return leaderboard;
  }

  public Map<String, Integer> getProfileLeaderboard(String id) {
    Map<String, Integer> leaderboard = new HashMap<>();

    List<LeaderboardEntry> easyLeaderboard = getLeaderboardEasyLevelUnsorted();
    List<LeaderboardEntry> mediumLeaderboard = getLeaderboardMediumLevelUnsorted();
    List<LeaderboardEntry> hardLeaderboard = getLeaderboardHardLevelUnsorted();

    String easyId = id + "-easy-question";
    String mediumId = id + "-medium-question";
    String hardId = id + "-hard-question";

    mergeSortId(easyLeaderboard);
    mergeSortId(mediumLeaderboard);
    mergeSortId(hardLeaderboard);

    int easyIndex = binarySearchById(easyLeaderboard, easyId);
    int mediumIndex = binarySearchById(mediumLeaderboard, mediumId);
    int hardIndex = binarySearchById(hardLeaderboard, hardId);

    if (easyIndex != -1) {
      leaderboard.put("easy", easyLeaderboard.get(easyIndex).getScore());
    } else {
      leaderboard.put("easy", -1);
    }

    if (mediumIndex != -1) {
      leaderboard.put("medium", mediumLeaderboard.get(mediumIndex).getScore());
    } else {
      leaderboard.put("medium", -1);
    }

    if (hardIndex != -1) {
      leaderboard.put("hard", hardLeaderboard.get(hardIndex).getScore());
    } else {
      leaderboard.put("hard", -1);
    }

    return leaderboard;
  }

  public LeaderboardEntry getLeaderboardEasyLevelEntryByName(String name) {
    List<LeaderboardEntry> leaderboard = getLeaderboardEasyLevelUnsorted();

    System.gc();
    double startTime = System.nanoTime();
    System.out.println("\nMerge sort by name");
    System.out.println("------------------------------");
    System.out.println("Start time\t: " + (startTime / 1_000_000) + " ms");
    mergeSortName(leaderboard);
    
    double endTime = System.nanoTime();
    System.out.println("End time\t: " + (endTime / 1_000_000) + " ms");
    
    double timeDiff = endTime - startTime;
    System.out.println("Time taken\t: " + (timeDiff / 1_000_000) + " ms");
    System.out.println("------------------------------\n");

    System.gc();
    startTime = System.nanoTime();
    System.out.println("\nBinary search by name");
    System.out.println("------------------------------");
    System.out.println("Start time\t: " + (startTime / 1_000_000) + " ms");

    int index = binarySearchByName(leaderboard, name);

    endTime = System.nanoTime();
    System.out.println("End time\t: " + (endTime / 1_000_000) + " ms");
    
    timeDiff = endTime - startTime;
    System.out.println("Time taken\t: " + (timeDiff / 1_000_000) + " ms");
    System.out.println("------------------------------\n");

    if (index != -1) {
      return leaderboard.get(index);
    }

    return null;
  }

  public LeaderboardEntry getLeaderboardMediumLevelEntryByName(String name) {
    List<LeaderboardEntry> leaderboard = getLeaderboardMediumLevelUnsorted();

    mergeSortName(leaderboard);

    int index = binarySearchByName(leaderboard, name);
    if (index != -1) {
      return leaderboard.get(index);
    }

    return null;
  }

  public LeaderboardEntry getLeaderboardHardLevelEntryByName(String name) {
    List<LeaderboardEntry> leaderboard = getLeaderboardHardLevelUnsorted();

    mergeSortName(leaderboard);

    int index = binarySearchByName(leaderboard, name);
    if (index != -1) {
      return leaderboard.get(index);
    }

    return null;
  }

  public boolean isNameExists(String name) {
    List<LeaderboardEntry> easyLeaderboard = getLeaderboardEasyLevelUnsorted();
    List<LeaderboardEntry> mediumLeaderboard = getLeaderboardMediumLevelUnsorted();
    List<LeaderboardEntry> hardLeaderboard = getLeaderboardHardLevelUnsorted();

    mergeSortName(easyLeaderboard);
    mergeSortName(mediumLeaderboard);
    mergeSortName(hardLeaderboard);

    int easyIndex = binarySearchByName(easyLeaderboard, name);
    int mediumIndex = binarySearchByName(mediumLeaderboard, name);
    int hardIndex = binarySearchByName(hardLeaderboard, name);

    return easyIndex != -1 || mediumIndex != -1 || hardIndex != -1;
  }

  // Binary search by ID implementation
  public int binarySearchById(List<LeaderboardEntry> leaderboard, String id) {
    int size = leaderboard.size();
    int low = 0;
    int high = size - 1;

    while (low <= high) {
      int mid = low + (high - low) / 2;

      if (leaderboard.get(mid).getId().equals(id)) {
        return mid;
      } else if (leaderboard.get(mid).getId().compareTo(id) < 0) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }

    return -1;
  }

  // Binary search by name implementation
  public int binarySearchByName(List<LeaderboardEntry> leaderboard, String name) {
    int size = leaderboard.size();
    int low = 0;
    int high = size - 1;

    while (low <= high) {
      int mid = low + (high - low) / 2;

      if (leaderboard.get(mid).getName().equals(name)) {
        return mid;
      } else if (leaderboard.get(mid).getName().compareTo(name) < 0) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }

    return -1;
  }

  // Merge sort implementation - Score based
  private void mergeSort(List<LeaderboardEntry> leaderboard) {
    if (leaderboard.size() <= 1) {
      return;
    }

    int mid = leaderboard.size() / 2;

    List<LeaderboardEntry> left = new ArrayList<>(leaderboard.subList(0, mid));
    List<LeaderboardEntry> right = new ArrayList<>(leaderboard.subList(mid, leaderboard.size()));

    mergeSort(left);
    mergeSort(right);

    merge(leaderboard, left, right);
  }

  private void merge(
      List<LeaderboardEntry> leaderboard,
      List<LeaderboardEntry> left,
      List<LeaderboardEntry> right) {
    int leftIndex = 0;
    int rightIndex = 0;
    int leaderboardIndex = 0;

    while (leftIndex < left.size() && rightIndex < right.size()) {
      if (left.get(leftIndex).getScore() > right.get(rightIndex).getScore()) {
        leaderboard.set(leaderboardIndex++, left.get(leftIndex++));
      } else {
        leaderboard.set(leaderboardIndex++, right.get(rightIndex++));
      }
    }

    // Copy remaining elements from left
    while (leftIndex < left.size()) {
      leaderboard.set(leaderboardIndex++, left.get(leftIndex++));
    }

    // Copy remaining elements from right
    while (rightIndex < right.size()) {
      leaderboard.set(leaderboardIndex++, right.get(rightIndex++));
    }
  }

  private void mergeSortName(List<LeaderboardEntry> leaderboard) {
    if (leaderboard.size() <= 1) {
      return;
    }

    int mid = leaderboard.size() / 2;

    List<LeaderboardEntry> left = new ArrayList<>(leaderboard.subList(0, mid));
    List<LeaderboardEntry> right = new ArrayList<>(leaderboard.subList(mid, leaderboard.size()));

    mergeSortName(left);
    mergeSortName(right);

    mergeName(leaderboard, left, right);
  }

  private void mergeName(
      List<LeaderboardEntry> leaderboard,
      List<LeaderboardEntry> left,
      List<LeaderboardEntry> right) {
    int leftIndex = 0;
    int rightIndex = 0;
    int leaderboardIndex = 0;

    while (leftIndex < left.size() && rightIndex < right.size()) {
      if (left.get(leftIndex).getName().compareTo(right.get(rightIndex).getName()) < 0) {
        leaderboard.set(leaderboardIndex++, left.get(leftIndex++));
      } else {
        leaderboard.set(leaderboardIndex++, right.get(rightIndex++));
      }
    }

    // Copy remaining elements from left
    while (leftIndex < left.size()) {
      leaderboard.set(leaderboardIndex++, left.get(leftIndex++));
    }

    // Copy remaining elements from right
    while (rightIndex < right.size()) {
      leaderboard.set(leaderboardIndex++, right.get(rightIndex++));
    }
  }

  // Merge sort implementation - Id based
  private void mergeSortId(List<LeaderboardEntry> leaderboard) {
    if (leaderboard.size() <= 1) {
      return;
    }

    int mid = leaderboard.size() / 2;

    List<LeaderboardEntry> left = new ArrayList<>(leaderboard.subList(0, mid));
    List<LeaderboardEntry> right = new ArrayList<>(leaderboard.subList(mid, leaderboard.size()));

    mergeSortId(left);
    mergeSortId(right);

    mergeId(leaderboard, left, right);
  }

  private void mergeId(
      List<LeaderboardEntry> leaderboard,
      List<LeaderboardEntry> left,
      List<LeaderboardEntry> right) {
    int leftIndex = 0;
    int rightIndex = 0;
    int leaderboardIndex = 0;

    while (leftIndex < left.size() && rightIndex < right.size()) {
      if (left.get(leftIndex).getId().compareTo(right.get(rightIndex).getId()) < 0) {
        leaderboard.set(leaderboardIndex++, left.get(leftIndex++));
      } else {
        leaderboard.set(leaderboardIndex++, right.get(rightIndex++));
      }
    }

    // Copy remaining elements from left
    while (leftIndex < left.size()) {
      leaderboard.set(leaderboardIndex++, left.get(leftIndex++));
    }

    // Copy remaining elements from right
    while (rightIndex < right.size()) {
      leaderboard.set(leaderboardIndex++, right.get(rightIndex++));
    }
  }

  //// For Word-based Leaderboard

  public void addWordLeaderboardEntry(String sessionId, String name, int score, String gameType, String difficulty,
      int time) {
    DocumentReference docRef = firestore.collection("wordLeaderboard").document(sessionId);
    ApiFuture<WriteResult> result = docRef
        .set(new WordLeaderboardEntry(sessionId, name, score, gameType, difficulty, time));

    try {
      WriteResult writeResult = result.get();
      System.out.println("Added word leaderboard entry for session: " + writeResult);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void updateWordLeaderboardEntry(String sessionId, String name, int score, int time) {
    DocumentReference docRef = firestore.collection("wordLeaderboard").document(sessionId);

    Map<String, Object> updates = new HashMap<>();
    updates.put("score", score);
    updates.put("name", name);
    updates.put("time", time);

    ApiFuture<WriteResult> result = docRef.update(updates);

    try {
      WriteResult writeResult = result.get();
      System.out.println("Updated word leaderboard entry for session: " + writeResult);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void updateWordLeaderboardEntryName(String sessionId, String name) {
    DocumentReference docRef = firestore.collection("wordLeaderboard").document(sessionId);

    Map<String, Object> updates = new HashMap<>();
    updates.put("name", name);

    ApiFuture<WriteResult> result = docRef.update(updates);

    try {
      WriteResult writeResult = result.get();
      System.out.println("Updated word leaderboard entry for session: " + writeResult);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public WordLeaderboardEntry getWordLeaderboardEntry(String sessionId) {
    DocumentReference docRef = firestore.collection("wordLeaderboard").document(sessionId);
    ApiFuture<DocumentSnapshot> query = docRef.get();

    try {
      DocumentSnapshot document = query.get();
      return document.toObject(WordLeaderboardEntry.class);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

  public List<WordLeaderboardEntry> getWordLeaderboardEasyLevel() {
    List<WordLeaderboardEntry> leaderboard = new ArrayList<>();
    ApiFuture<QuerySnapshot> query = firestore.collection("wordLeaderboard").whereEqualTo("difficulty", "easy").get();

    try {
      query.get().getDocuments().forEach(document -> {
        WordLeaderboardEntry entry = document.toObject(WordLeaderboardEntry.class);
        leaderboard.add(entry);
      });
    } catch (Exception e) {
      e.printStackTrace();
    }

    mergeSortWord(leaderboard);

    return leaderboard;
  }

  public List<WordLeaderboardEntry> getWordLeaderboardMediumLevel() {
    List<WordLeaderboardEntry> leaderboard = new ArrayList<>();
    ApiFuture<QuerySnapshot> query = firestore.collection("wordLeaderboard").whereEqualTo("difficulty", "medium").get();

    try {
      query.get().getDocuments().forEach(document -> {
        WordLeaderboardEntry entry = document.toObject(WordLeaderboardEntry.class);
        leaderboard.add(entry);
      });
    } catch (Exception e) {
      e.printStackTrace();
    }

    mergeSortWord(leaderboard);

    return leaderboard;
  }

  public List<WordLeaderboardEntry> getWordLeaderboardHardLevel() {
    List<WordLeaderboardEntry> leaderboard = new ArrayList<>();
    ApiFuture<QuerySnapshot> query = firestore.collection("wordLeaderboard").whereEqualTo("difficulty", "hard").get();

    try {
      query.get().getDocuments().forEach(document -> {
        WordLeaderboardEntry entry = document.toObject(WordLeaderboardEntry.class);
        leaderboard.add(entry);
      });
    } catch (Exception e) {
      e.printStackTrace();
    }

    mergeSortWord(leaderboard);

    return leaderboard;
  }

  public Map<String, Integer> getWordProfileLeaderboard(String id) {
    Map<String, Integer> leaderboard = new HashMap<>();

    List<WordLeaderboardEntry> easyLeaderboard = getWordLeaderboardEasyLevel();
    List<WordLeaderboardEntry> mediumLeaderboard = getWordLeaderboardMediumLevel();
    List<WordLeaderboardEntry> hardLeaderboard = getWordLeaderboardHardLevel();

    String easyId = id + "-easy-word";
    String mediumId = id + "-medium-word";
    String hardId = id + "-hard-word";

    mergeSortWordId(easyLeaderboard);
    mergeSortWordId(mediumLeaderboard);
    mergeSortWordId(hardLeaderboard);

    int easyIndex = binarySearchWordById(easyLeaderboard, easyId);
    int mediumIndex = binarySearchWordById(mediumLeaderboard, mediumId);
    int hardIndex = binarySearchWordById(hardLeaderboard, hardId);

    if (easyIndex != -1) {
      leaderboard.put("easy", easyLeaderboard.get(easyIndex).getScore());
    } else {
      leaderboard.put("easy", -1);
    }

    if (mediumIndex != -1) {
      leaderboard.put("medium", mediumLeaderboard.get(mediumIndex).getScore());
    } else {
      leaderboard.put("medium", -1);
    }

    if (hardIndex != -1) {
      leaderboard.put("hard", hardLeaderboard.get(hardIndex).getScore());
    } else {
      leaderboard.put("hard", -1);
    }

    return leaderboard;
  }

  public WordLeaderboardEntry getWordLeaderboardEasyLevelEntryByName(String name) {
    List<WordLeaderboardEntry> leaderboard = getWordLeaderboardEasyLevel();

    mergeSortWordName(leaderboard);

    int index = binarySearchWordByName(leaderboard, name);
    if (index != -1) {
      return leaderboard.get(index);
    }

    return null;
  }

  public WordLeaderboardEntry getWordLeaderboardMediumLevelEntryByName(String name) {
    List<WordLeaderboardEntry> leaderboard = getWordLeaderboardMediumLevel();

    mergeSortWordName(leaderboard);

    int index = binarySearchWordByName(leaderboard, name);
    if (index != -1) {
      return leaderboard.get(index);
    }

    return null;
  }

  public WordLeaderboardEntry getWordLeaderboardHardLevelEntryByName(String name) {
    List<WordLeaderboardEntry> leaderboard = getWordLeaderboardHardLevel();

    mergeSortWordName(leaderboard);

    int index = binarySearchWordByName(leaderboard, name);
    if (index != -1) {
      return leaderboard.get(index);
    }

    return null;
  }

  public boolean isWordNameExists(String name) {
    List<WordLeaderboardEntry> easyLeaderboard = getWordLeaderboardEasyLevel();
    List<WordLeaderboardEntry> mediumLeaderboard = getWordLeaderboardMediumLevel();
    List<WordLeaderboardEntry> hardLeaderboard = getWordLeaderboardHardLevel();

    mergeSortWordName(easyLeaderboard);
    mergeSortWordName(mediumLeaderboard);
    mergeSortWordName(hardLeaderboard);

    int easyIndex = binarySearchWordByName(easyLeaderboard, name);
    int mediumIndex = binarySearchWordByName(mediumLeaderboard, name);
    int hardIndex = binarySearchWordByName(hardLeaderboard, name);

    return easyIndex != -1 || mediumIndex != -1 || hardIndex != -1;
  }

  // Binary search by ID implementation
  private int binarySearchWordById(List<WordLeaderboardEntry> leaderboard, String id) {
    int size = leaderboard.size();
    int low = 0;
    int high = size - 1;

    while (low <= high) {
      int mid = low + (high - low) / 2;

      if (leaderboard.get(mid).getId().equals(id)) {
        return mid;
      } else if (leaderboard.get(mid).getId().compareTo(id) < 0) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }

    return -1;
  }

  // Binary search by name implementation
  private int binarySearchWordByName(List<WordLeaderboardEntry> leaderboard, String name) {
    int size = leaderboard.size();
    int low = 0;
    int high = size - 1;

    while (low <= high) {
      int mid = low + (high - low) / 2;

      if (leaderboard.get(mid).getName().equals(name)) {
        return mid;
      } else if (leaderboard.get(mid).getName().compareTo(name) < 0) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }

    return -1;
  }

  // Merge sort implementation - Score and Time based
  private void mergeSortWord(List<WordLeaderboardEntry> leaderboard) {
    if (leaderboard.size() <= 1) {
      return;
    }

    int mid = leaderboard.size() / 2;

    List<WordLeaderboardEntry> left = new ArrayList<>(leaderboard.subList(0, mid));
    List<WordLeaderboardEntry> right = new ArrayList<>(leaderboard.subList(mid, leaderboard.size()));

    mergeSortWord(left);
    mergeSortWord(right);

    mergeWord(leaderboard, left, right);
  }

  private void mergeWord(
      List<WordLeaderboardEntry> leaderboard,
      List<WordLeaderboardEntry> left,
      List<WordLeaderboardEntry> right) {
    int leftIndex = 0;
    int rightIndex = 0;
    int leaderboardIndex = 0;

    while (leftIndex < left.size() && rightIndex < right.size()) {
      if (left.get(leftIndex).getScore() > right.get(rightIndex).getScore()) {
        leaderboard.set(leaderboardIndex++, left.get(leftIndex++));
      } else if (left.get(leftIndex).getScore() == right.get(rightIndex).getScore()) {
        if (left.get(leftIndex).getTime() < right.get(rightIndex).getTime()) {
          leaderboard.set(leaderboardIndex++, left.get(leftIndex++));
        } else {
          leaderboard.set(leaderboardIndex++, right.get(rightIndex++));
        }
      } else {
        leaderboard.set(leaderboardIndex++, right.get(rightIndex++));
      }
    }

    while (leftIndex < left.size()) {
      leaderboard.set(leaderboardIndex++, left.get(leftIndex++));
    }

    // Copy remaining elements from right
    while (rightIndex < right.size()) {
      leaderboard.set(leaderboardIndex++, right.get(rightIndex++));
    }
  }

  // Merge sort implementation - Id based
  private void mergeSortWordId(List<WordLeaderboardEntry> leaderboard) {
    if (leaderboard.size() <= 1) {
      return;
    }

    int mid = leaderboard.size() / 2;

    List<WordLeaderboardEntry> left = new ArrayList<>(leaderboard.subList(0, mid));
    List<WordLeaderboardEntry> right = new ArrayList<>(leaderboard.subList(mid, leaderboard.size()));

    mergeSortWordId(left);
    mergeSortWordId(right);

    mergeWordId(leaderboard, left, right);
  }

  private void mergeSortWordName(List<WordLeaderboardEntry> leaderboard) {
    if (leaderboard.size() <= 1) {
      return;
    }

    int mid = leaderboard.size() / 2;

    List<WordLeaderboardEntry> left = new ArrayList<>(leaderboard.subList(0, mid));
    List<WordLeaderboardEntry> right = new ArrayList<>(leaderboard.subList(mid, leaderboard.size()));

    mergeSortWordName(left);
    mergeSortWordName(right);

    mergeWordName(leaderboard, left, right);
  }

  private void mergeWordName(
      List<WordLeaderboardEntry> leaderboard,
      List<WordLeaderboardEntry> left,
      List<WordLeaderboardEntry> right) {
    int leftIndex = 0;
    int rightIndex = 0;
    int leaderboardIndex = 0;

    while (leftIndex < left.size() && rightIndex < right.size()) {
      if (left.get(leftIndex).getName().compareTo(right.get(rightIndex).getName()) < 0) {
        leaderboard.set(leaderboardIndex++, left.get(leftIndex++));
      } else {
        leaderboard.set(leaderboardIndex++, right.get(rightIndex++));
      }
    }

    // Copy remaining elements from left
    while (leftIndex < left.size()) {
      leaderboard.set(leaderboardIndex++, left.get(leftIndex++));
    }

    // Copy remaining elements from right
    while (rightIndex < right.size()) {
      leaderboard.set(leaderboardIndex++, right.get(rightIndex++));
    }
  }

  private void mergeWordId(
      List<WordLeaderboardEntry> leaderboard,
      List<WordLeaderboardEntry> left,
      List<WordLeaderboardEntry> right) {
    int leftIndex = 0;
    int rightIndex = 0;
    int leaderboardIndex = 0;

    while (leftIndex < left.size() && rightIndex < right.size()) {
      if (left.get(leftIndex).getId().compareTo(right.get(rightIndex).getId()) < 0) {
        leaderboard.set(leaderboardIndex++, left.get(leftIndex++));
      } else {
        leaderboard.set(leaderboardIndex++, right.get(rightIndex++));
      }
    }

    // Copy remaining elements from left
    while (leftIndex < left.size()) {
      leaderboard.set(leaderboardIndex++, left.get(leftIndex++));
    }

    // Copy remaining elements from right
    while (rightIndex < right.size()) {
      leaderboard.set(leaderboardIndex++, right.get(rightIndex++));
    }
  }
}
