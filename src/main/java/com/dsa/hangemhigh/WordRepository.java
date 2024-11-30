package com.dsa.hangemhigh;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

@Repository
public class WordRepository {
  private Map<String, List<Word>> wordCount;

  public WordRepository() {
    this.wordCount = new HashMap<String, List<Word>>();
    loadEasyWords();
    loadMediumWords();
    loadHardWords();
  }

  public void loadEasyWords() {
    List<Word> easyWords = new ArrayList<>(List.of(
        new Word("Tunku Abdul Rahman", "Father of Independence.", "easy"),
        new Word("Mahathir Mohamad", "Father of Modernization.", "easy"),
        new Word("Jalur Gemilang", "National Flag of Malaysia.", "easy"),
        new Word("Negaraku", "National Anthem of Malaysia.", "easy"),
        new Word("Hibiscus", "National Flower of Malaysia.", "easy"),
        new Word("Borneo", "Island shared with Indonesia and Brunei.", "easy"),
        new Word("Hang Tuah", "Legendary warrior of Malacca, known for his loyalty.", "easy"),
        new Word("Sultan", "Title for Malay rulers.", "easy"),
        new Word("Malacca", "Historic state known invaded by the Portuguese in 1511.", "easy"),
        new Word("Tun Abdul Razak", "Second Prime Minister in Malaysia.", "easy")));

    wordCount.put("easy", easyWords);
  }

  public void loadMediumWords() {
    List<Word> easyWords = new ArrayList<>(List.of(
        new Word("Parameswara", "Historical figure who founded the Melaka Sultanate early on.", "medium"),
        new Word("Singapore", "State that was not part of Malaysia's initial formation.", "medium"),
        new Word("British", "The country that intervened in Perak per Treaty of Pangkor.", "medium"),
        new Word("Mahmud Shah", "Sultan of Malacca before the Portuguese conquest took place.", "medium"),
        new Word("Reid Commission", "Commission responsible for drafting the Federal Constitution of Malaysia.",
            "medium"),
        new Word("Malayan Emergency", "Conflict in Malaysia from 1948 to 1960 aimed at combating communism.", "medium"),
        new Word("Sarawak", "State in Malaysia was once under the rule of the Brooke family.", "medium"),
        new Word("Pangkor", "Treaty that marked British intervention in Perak.", "medium"),
        new Word("Malayan Union", "Political entity established by the British in 1946.", "medium"),
        new Word("Rukun Negara", "National ideology introduced in 1970 to foster unity among Malaysians.", "medium")));

    wordCount.put("medium", easyWords);
  }

  public void loadHardWords() {
    List<Word> easyWords = new ArrayList<>(List.of(
        new Word("Cobbold Commission",
            "Commission established to assessed views on joining Malaysia in North Borneo and Sarawak.", "hard"),
        new Word("Briggs Plan",
            "A British strategy during the Malayan Emergency aimed at relocating rural populations.", "hard"),
        new Word("Federation", "Union of self-governing states under a central government, as in Malaya.", "hard"),
        new Word("New Economic Policy", "Major economic strategy Malaysia implemented in 1971.", "hard"),
        new Word("Baling Talks", "A 1955 negotiation aimed at achieving peace during the Malayan Emergency.", "hard"),
        new Word("Borneo", "Island shared with Indonesia and Brunei.", "hard"),
        new Word("Raja Raja Melayu", "A collective term for the Malay rulers.", "hard"),
        new Word("Sovereignty", "The authority threatened by the Malayan Union.", "hard"),
        new Word("Colonialism", "A system where a country rules over another.", "hard"),
        new Word("Democracy", "A system of government by the whole population.", "hard")));

    wordCount.put("hard", easyWords);
  }

  public List<Word> get(String difficulty) {
    return wordCount.get(difficulty);
  }

  public void resetWords() {
    wordCount.clear();
    loadEasyWords();
    loadMediumWords();
    loadHardWords();
  }
}
