package com.dsa.hangemhigh;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Queue;
import java.util.List;
import java.util.LinkedList;
import java.util.Collections;
import java.util.HashMap;

@Service
public class WordService {
  private WordRepository wordRepository;
  private Map<String, Queue<Word>> wordQueues;

  public WordService(WordRepository wordRepository) {
    this.wordRepository = wordRepository;
    wordQueues = new HashMap<String, Queue<Word>>();
    loadWordsIntoQueues();
  }

  public void loadWordsIntoQueues() {
    for (String difficulty : List.of("easy", "medium", "hard")) {
      List<Word> wordList = wordRepository.get(difficulty);
      if (wordList != null) {
        Collections.shuffle(wordList);
        wordQueues.put(difficulty, new LinkedList<Word>(wordList));
      } else {
        wordQueues.put(difficulty, new LinkedList<Word>());
      }
    }
  }

  public Queue<Word> getShuffledWords(String difficulty) {
    return wordQueues.get(difficulty);
  }

  public void resetWords() {
    wordRepository.resetWords();
    wordQueues.clear();
    loadWordsIntoQueues();
  }
}
