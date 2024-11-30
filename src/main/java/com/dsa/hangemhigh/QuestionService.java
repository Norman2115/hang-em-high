package com.dsa.hangemhigh;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class QuestionService {
  private final QuestionRepository questionRepository;
  private Map<String, Queue<Question>> questionQueues;

  public QuestionService(QuestionRepository questionRepository) {
    this.questionRepository = questionRepository;
    questionQueues = new HashMap<>();
    loadQuestionsIntoQueues();
  }

  private void loadQuestionsIntoQueues() {
    for (String difficulty : List.of("easy", "medium", "hard")) {
      List<Question> questionList = questionRepository.get(difficulty);
      if (questionList != null) {
        Collections.shuffle(questionList);

        for (Question question : questionList) {
          List<String> options = question.getOptions();
          Collections.shuffle(options);
          question.setOptions(options);
        }

        questionQueues.put(difficulty, new LinkedList<Question>(questionList));
      } else {
        questionQueues.put(difficulty, new LinkedList<Question>());
      }
    }
  }

  public Queue<Question> getShuffledQuestions(String difficulty) {
    return questionQueues.get(difficulty);
  }

  public void resetQuestions() {
    questionQueues.clear();
    loadQuestionsIntoQueues();
  }
}
