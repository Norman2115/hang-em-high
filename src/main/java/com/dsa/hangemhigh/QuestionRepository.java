package com.dsa.hangemhigh;

import java.util.Map;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.lang.String;

@Repository
public class QuestionRepository {
  private Map<String, List<Question>> questions;

  public QuestionRepository() {
    questions = new HashMap<String, List<Question>>();
    loadEasyQuestions();
    loadMediumQuestions();
    loadHardQuestions();
  }

  public List<Question> get(String difficulty) {
    return questions.get(difficulty);
  }

  private void loadEasyQuestions() {
    List<Question> easyQuestions = new ArrayList<Question>();

    easyQuestions.add(new Question(
        "When did Malaysia gain independence?",
        new ArrayList<>(List.of("1955", "1957", "1963", "1971")),
        "1957",
        "easy"));

    easyQuestions.add(new Question(
        "Who was the first Prime Minister of Malaysia?",
        new ArrayList<>(List.of("Tunku Abdul Rahman", "Dr. Mahathir Mohamad", "Tun Hussein Onn", "Tun Abdul Razak")),
        "Tunku Abdul Rahman",
        "easy"));

    easyQuestions.add(new Question(
        "What is the national flower of Malaysia?",
        new ArrayList<>(List.of("Hibiscus", "Rafflesia", "Orchid", "Sunflower")),
        "Hibiscus",
        "easy"));

    easyQuestions.add(new Question(
        "Who is known as the 'Father of Modernization'?",
        new ArrayList<>(List.of("Tunku Abdul Rahman", "Dr. Mahathir Mohamad", "Tun Hussein Onn", "Tun Abdul Razak")),
        "Dr. Mahathir Mohamad",
        "easy"));

    easyQuestions.add(new Question(
        "What is the currency of Malaysia?",
        new ArrayList<>(List.of("Ringgit", "Dollar", "Pound", "Yen")),
        "Ringgit",
        "easy"));

    easyQuestions.add(new Question(
        "What is the official religion of Malaysia?",
        new ArrayList<>(List.of("Islam", "Christianity", "Buddhism", "Hinduism")),
        "Islam",
        "easy"));

    easyQuestions.add(new Question(
        "Who is known as the 'Father of Unity'",
        new ArrayList<>(List.of("Tunku Abdul Rahman", "Dr. Mahathir Mohamad", "Tun Hussein Onn", "Tun Abdul Razak")),
        "Tun Hussein Onn",
        "easy"));

    easyQuestions.add(new Question(
        "Which island is shared between Malaysia, Indonesia, and Brunei?",
        new ArrayList<>(List.of("Borneo", "Sumatra", "Java", "Sulawesi")),
        "Borneo",
        "easy"));

    easyQuestions.add(new Question(
        "Who was Hang Tuah?",
        new ArrayList<>(List.of("A warrior", "A king", "A poet", "A sailor")),
        "A warrior",
        "easy"));

    easyQuestions.add(new Question(
        "What is the national anthem of Malaysia?",
        new ArrayList<>(List.of("Negaraku", "Jalur Gemilang", "Tunku", "Malaysia, My Homeland")),
        "Negaraku",
        "easy"));

    easyQuestions.add(new Question(
        "When was the Federation of Malaysia formed?",
        new ArrayList<>(List.of("1957", "1963", "1971", "1983")),
        "1963",
        "easy"));

    easyQuestions.add(new Question(
        "Who is known as the 'Father of Development'",
        new ArrayList<>(List.of("Tunku Abdul Rahman", "Dr. Mahathir Mohamad", "Tun Hussein Onn", "Tun Abdul Razak")),
        "Tun Abdul Razak",
        "easy"));

    easyQuestions.add(new Question(
        "Who colonized Malaysia before independence?",
        new ArrayList<>(List.of("Portugal", "Spain", "Britain", "France")),
        "Britain",
        "easy"));

    easyQuestions.add(new Question(
        "What is the name of Malaysia's legislative building?",
        new ArrayList<>(List.of("Parliament House", "Supreme Court", "Congress", "Royal Palace")),
        "Parliament House",
        "easy"));

    easyQuestions.add(new Question(
        "What is the capital city of Malaysia?",
        new ArrayList<>(List.of("Kuala Lumpur", "Putrajaya", "Penang", "Johor Bahru")),
        "Kuala Lumpur",
        "easy"));

    easyQuestions.add(new Question(
        "How many states does Malaysia have?",
        new ArrayList<>(List.of("10", "11", "12", "13")),
        "13",
        "easy"));

    easyQuestions.add(new Question(
        "What is Jalur Gemilang?",
        new ArrayList<>(
            List.of("The Malaysian flag", "The national anthem", "The national flower", "A historical site")),
        "The Malaysian flag",
        "easy"));

    easyQuestions.add(new Question(
        "Which strait separates Malaysia from Indonesia?",
        new ArrayList<>(List.of("Sunda Strait", "Lombok Strait", "Malacca Strait", "Karimata Strait")),
        "Malacca Strait",
        "easy"));

    easyQuestions.add(new Question(
        "Where is the Petronas Twin Towers located?",
        new ArrayList<>(List.of("Kuala Lumpur", "Penang", "Johor Bahru", "Putrajaya")),
        "Kuala Lumpur",
        "easy"));

    easyQuestions.add(new Question(
        "Who is known as the 'Father of Independence'",
        new ArrayList<>(List.of("Tunku Abdul Rahman", "Dr. Mahathir Mohamad", "Tun Hussein Onn", "Tun Abdul Razak")),
        "Tunku Abdul Rahman",
        "easy"));

    questions.put("easy", easyQuestions);
  }

  private void loadMediumQuestions() {
    List<Question> mediumQuestions = new ArrayList<Question>();

    // 1
    mediumQuestions.add(new Question(
        "What agreement led to the formation of the Federation of Malaysia in 1963?",
        new ArrayList<>(List.of("Cobbold Commission", "London Agreement", "Mergers Agreement", "Malaysia Agreement")),
        "Malaysia Agreement",
        "medium"));

    // 2
    mediumQuestions.add(new Question(
        "What was the significance of the Treaty of Pangkor (1874)",
        new ArrayList<>(
            List.of("Granted independence to Malaya", "Marked the beginning of British intervention in Perak",
                "United the Malay states", "Formed the Federation of Malaysia")),
        "Marked the beginning of British intervention in Perak",
        "medium"));

    // 3
    mediumQuestions.add(new Question(
        "Which European country was the first to colonize Malacca?",
        new ArrayList<>(List.of("Portugal", "Spain", "Britain", "France")),
        "Portugal",
        "medium"));

    // 4
    mediumQuestions.add(new Question(
        "What year did the British introduce the Malayan Union?",
        new ArrayList<>(List.of("1946", "1948", "1950", "1952")),
        "1946",
        "medium"));

    // 5
    mediumQuestions.add(new Question(
        "What was the purpose of the Malayan Union established in 1946?",
        new ArrayList<>(
            List.of("To unite the Malay states", "To promote local industry", "To introduce democracy in Malaya",
                "To defend against foreign threats")),
        "To unite the Malay states",
        "medium"));

    // 6
    mediumQuestions.add(new Question(
        "The concept of ‘Bangsa Malaysia’ was introduced in which policy?",
        new ArrayList<>(List.of("Rukun Negara", "New Economic Policy", "Vision 2020", "National Education Policy")),
        "Vision 2020",
        "medium"));

    // 7
    mediumQuestions.add(new Question(
        "Which Sultan controlled Malacca before the Portuguese conquest?",
        new ArrayList<>(
            List.of("Sultan Mansur Shah", "Sultan Mahmud Shah", "Sultan Muhammad Shah", "Sultan Abdullah Shah")),
        "Sultan Mahmud Shah",
        "medium"));

    // 8
    mediumQuestions.add(new Question(
        "Which historical figure founded the Melaka Sultanate?",
        new ArrayList<>(List.of("Parameswara", "Hang Tuah", "Hang Jebat", "Hang Kasturi")),
        "Parameswara",
        "medium"));

    // 9
    mediumQuestions.add(new Question(
        "What was the main reason for the downfall of the Melaka Sultanate?",
        new ArrayList<>(List.of("Portuguese invasion", "Dutch invasion", "British invasion", "Spanish invasion")),
        "Portuguese invasion",
        "medium"));

    // 10
    mediumQuestions.add(new Question(
        "What agreement officially marked the formation of Malaysia in 1963?",
        new ArrayList<>(List.of("Cobbold Commission", "London Agreement", "Mergers Agreement", "Malaysia Agreement")),
        "Malaysia Agreement",
        "medium"));

    // 11
    mediumQuestions.add(new Question(
        "Which state was not part of Malaysia when it was first formed?",
        new ArrayList<>(List.of("Sabah", "Sarawak", "Johor", "Singapore")),
        "Singapore",
        "medium"));

    // 12
    mediumQuestions.add(new Question(
        "What caused Singapore to leave Malaysia in 1965?",
        new ArrayList<>(
            List.of("Cultural differences", "Political disagreements", "Economic issues", "Religious conflict")),
        "Political disagreements",
        "medium"));

    // 13
    mediumQuestions.add(new Question(
        "What was the primary goal of the Malayan Emergency (1948-1960)?",
        new ArrayList<>(
            List.of("To fight against communism", "To gain independence from Britain", "To promote democracy",
                "To establish a new government")),
        "To fight against communism",
        "medium"));

    // 14
    mediumQuestions.add(new Question(
        "What was the main cause of the 13 May Incident in 1969?",
        new ArrayList<>(List.of("Racial tensions", "Economic issues", "Political disagreements", "Religious conflict")),
        "Racial tensions",
        "medium"));

    // 15
    mediumQuestions.add(new Question(
        "What was the purpose of the Reid Commission?",
        new ArrayList<>(List.of("To draft the Federal Constitution", "To establish the Malayan Union",
            "To form the Federation of Malaysia", "To fight against communism")),
        "To draft the Federal Constitution",
        "medium"));

    // 16
    mediumQuestions.add(new Question(
        "Who was the architect behind the New Economic Policy (NEP)?",
        new ArrayList<>(List.of("Tun Abdul Razak", "Tunku Abdul Rahman", "Dr. Mahathir Mohamad", "Tun Hussein Onn")),
        "Tun Abdul Razak",
        "medium"));

    // 17
    mediumQuestions.add(new Question(
        "What was the main goal of the New Economic Policy (NEP)?",
        new ArrayList<>(List.of("To reduce poverty and promote economic growth", "To fight against communism",
            "To establish a new government", "To promote democracy")),
        "To reduce poverty and promote economic growth",
        "medium"));

    // 18
    mediumQuestions.add(new Question(
        "Which concept emphasizes unity among the different races in Malaysia?",
        new ArrayList<>(List.of("Rukun Negara", "Vision 2020", "National Education Policy", "New Economic Policy")),
        "Rukun Negara",
        "medium"));

    // 19
    mediumQuestions.add(new Question(
        "Which state in Malaysia was once under the rule of the Brooke family?",
        new ArrayList<>(List.of("Sarawak", "Sabah", "Johor", "Perak")),
        "Sarawak",
        "medium"));

    // 20
    mediumQuestions.add(new Question(
        "What was the purpose of the Rukun Negara, introduced in 1970?",
        new ArrayList<>(
            List.of("To promote national unity", "To fight against communism", "To establish a new government",
                "To reduce poverty")),
        "To promote national unity",
        "medium"));

    questions.put("medium", mediumQuestions);
  }

  public void loadHardQuestions() {
    List<Question> hardQuestions = new ArrayList<Question>();

    // 1
    hardQuestions.add(new Question(
      "What was the key feature of the Treaty of Bangkok (1909)?",
      new ArrayList<>(List.of("Transfer of Sabah to Malaysia", "Transfer of Perlis, Kedah, Kelantan, and Terengganu to British control", "Recognition of Singapore’s sovereignty", "Establishment of British control in Sarawak")),
      "Transfer of Perlis, Kedah, Kelantan, and Terengganu to British control",
      "hard"
    ));

    // 2
    hardQuestions.add(new Question(
      "What role did the British Resident system play in Malaya?",
      new ArrayList<>(List.of("To oversee the administration of the Malay states", "To promote local industry", "To introduce democracy in Malaya", "To defend against foreign threats")),
      "To oversee the administration of the Malay states",
      "hard"
    ));

    // 3
    hardQuestions.add(new Question(
      "Why did the Malayan Union face strong opposition from the Malays",
      new ArrayList<>(List.of("It threatened Malay sovereignty", "It promoted democracy", "It was controlled by the British", "It was too expensive")),
      "It threatened Malay sovereignty",
      "hard"
    ));

    // 4
    hardQuestions.add(new Question(
      "How did the Japanese occupation (1942-1945) impact Malaya?",
      new ArrayList<>(List.of("Increased anti-colonial sentiments among locals", "Promoted economic growth", "Strengthened the colonial government", "Introduced new educational policies")),
      "Increased anti-colonial sentiments among locals",
      "hard"
    ));

    // 5
    hardQuestions.add(new Question(
      "What was the main objective of the Briggs Plan during the Malayan Emergency?",
      new ArrayList<>(List.of("To relocate rural populations to resettlement areas", "To promote democracy", "To establish a new government", "To fight against communism")),
      "To relocate rural populations to resettlement areas",
      "hard"
    ));

    // 6
    hardQuestions.add(new Question(
      "When was the concept of a constitutional monarchy introduced in Malaya?",
      new ArrayList<>(List.of("1957", "1963", "1971", "1983")),
      "1957",
      "hard"
    ));

    // 7
    hardQuestions.add(new Question(
      "Which principle of Rukun Negara emphasizes loyalty to the nation?",
      new ArrayList<>(List.of("Belief in God", "Loyalty to King and Country", "Upholding the Constitution", "Rule of Law")),
      "Loyalty to King and Country",
      "hard"
    ));

    // 8
    hardQuestions.add(new Question(
      "What did the British introduce to unite Malaya politically in 1946?",
      new ArrayList<>(List.of("Malayan Union", "Federation of Malaya", "Malayan Emergency", "Reid Commission")),
      "Malayan Union",
      "hard"
    ));

    // 9
    hardQuestions.add(new Question(
      "Which event led to the formation of Malaysia in 1963?",
      new ArrayList<>(List.of("Malayan Union collapse", "Separation of Singapore", "Cobbold Commission's recommendations", "The signing of the Bangkok Treaty")),
      "Cobbold Commission's recommendations",
      "hard"
    ));

    // 10
    hardQuestions.add(new Question(
      "What major economic strategy did Malaysia implement in 1971?",
      new ArrayList<>(List.of("New Economic Policy", "Vision 2020", "Rukun Negara", "National Education Policy")),
      "New Economic Policy",
      "hard"
    ));

    // 11
    hardQuestions.add(new Question(
      "What role did the Malayan Communist Party (MCP) play during the Japanese Occupation?",
      new ArrayList<>(List.of("Supported Japanese forces", "Fought against the British", "Formed the Malayan People's Anti-Japanese Army (MPAJA)", "Aided British forces in Malaya")),
      "Formed the Malayan People's Anti-Japanese Army (MPAJA)",
      "hard"
    ));

    // 12
    hardQuestions.add(new Question(
      "What was a major reason for the 13th May 1969 racial riots?",
      new ArrayList<>(List.of("The success of the communist insurgency", "Controversial election results", "Tensions between colonial powers", "Overthrow of the monarchy")),
      "Controversial election results",
      "hard"
    ));

    // 13
    hardQuestions.add(new Question(
      "Which agreement determined the political status of Sabah and Sarawak in 1963?",
      new ArrayList<>(List.of("Cobbold Commission Report", "London Agreement", "Mergers Agreement", "Malaysia Agreement")),
      "Malaysia Agreement",
      "hard"
    ));

    // 14
    hardQuestions.add(new Question(
      "Which party represented the interests of Chinese Malaysians during the independence movement?",
      new ArrayList<>(List.of("Democratic Action Party (DAP)", "Malaysian Chinese Association (MCA)", "United Malays National Organization (UMNO)", "Malaysian Indian Congress (MIC)")),
      "Malaysian Chinese Association (MCA)",
      "hard"
    ));

    // 15
    hardQuestions.add(new Question(
      "What was the main consequence of the Baling Talks in 1955?",
      new ArrayList<>(List.of("The communists agreed to surrender", "Peace was achieved between the British and MCP", "Tunku Abdul Rahman rejected the communist terms", "Emergency laws were lifted")),
      "Tunku Abdul Rahman rejected the communist terms",
      "hard"
    ));

    // 16
    hardQuestions.add(new Question(
      "Which British policy created dissatisfaction among the Malays before independence?",
      new ArrayList<>(List.of("Formation of the Malayan Union", "Implementation of trade tariffs", "Appointment of local governors", "Expansion of urban development projects")),
      "Formation of the Malayan Union",
      "hard"
    ));

    // 17
    hardQuestions.add(new Question(
      "What were the responsibilities of the Straits Settlements?",
      new ArrayList<>(List.of("Overseeing British colonies in Malaya", "Managing trade routes in the region", "Administering the Malay states", "Defending against foreign threats")),
      "Managing trade routes in the region",
      "hard"
    ));

    // 18
    hardQuestions.add(new Question(
      "What was the key feature of the Malacca Sultanate's administration?",
      new ArrayList<>(List.of("A decentralized system", "Integration of Islamic law", "Absolute power by the sultan", "Elected local rulers")),
      "Integration of Islamic law",
      "hard"
    ));

    // 19
    hardQuestions.add(new Question(
      "What was the main impact of the introduction of local council elections in 1951?",
      new ArrayList<>(List.of("Strengthened British political control", " Increased communist influence", "Gave more political representation to locals", "Abolished the sultanate system")),
      "Gave more political representation to locals",
      "hard"
    ));

    // 20
    hardQuestions.add(new Question(
      "How did the British colonial administration impact the education system in Malaya?",
      new ArrayList<>(List.of("It established English as the medium of instruction", "It encouraged religious-based schools", " It created a system favoring the Malay population", "It standardized education across all ethnic groups")),
      "It established English as the medium of instruction",
      "hard"
    ));

    questions.put("hard", hardQuestions);
  }
}
