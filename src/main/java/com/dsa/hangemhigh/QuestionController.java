package com.dsa.hangemhigh;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/questions")
public class QuestionController {
  private QuestionService questionService;
  private Question currentQuestion;

  public QuestionController(QuestionService questionService) {
    this.questionService = questionService;
  }

  @GetMapping("/{difficulty}")
  public Question getQuestion(@PathVariable String difficulty) {
    currentQuestion = questionService.getShuffledQuestions(difficulty).poll();
    return currentQuestion;
  }

  @GetMapping("/remaining")
  public ResponseEntity<Integer> getRemainingQuestions() {
    if (currentQuestion == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(questionService.getShuffledQuestions(currentQuestion.getDifficulty()).size());
  }

  @PostMapping("/restart")
  public void restart() {
    questionService.resetQuestions();
  }
}
