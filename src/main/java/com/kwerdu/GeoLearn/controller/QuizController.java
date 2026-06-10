package com.kwerdu.GeoLearn.controller;

import com.kwerdu.GeoLearn.dto.QuizAnswerDto;
import com.kwerdu.GeoLearn.dto.QuizQuestionDto;
import com.kwerdu.GeoLearn.service.QuizService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/next")
    public QuizQuestionDto getNext(Authentication auth) {
        return quizService.getNextQuestion(auth.getName());
    }

    @PostMapping("/answer")
    public boolean submitAnswer(@RequestBody QuizAnswerDto answer, Authentication auth) {
        return quizService.submitAnswer(auth.getName(), answer);
    }
}