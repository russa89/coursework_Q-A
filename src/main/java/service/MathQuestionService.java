package service;

import java.util.Random;

public class MathQuestionService {

    private final QuestionService questionService;
    private final Random random;

    public MathQuestionService(QuestionService questionService, Random random) {
        this.questionService = questionService;
        this.random = random;
    }
}
