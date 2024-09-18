package service;

import exceptions.IncorrectQuestionAnswerInput;
import exceptions.QuestionIsAlreadyAddedException;
import exceptions.QuestionIsOutException;
import model.Question;

import java.util.Collection;
import java.util.Random;

public class MathQuestionService {

    private final QuestionService questionService;
    private final Random random;

    public MathQuestionService(QuestionService questionService, Random random) {
        this.questionService = questionService;
        this.random = random;
    }

    @Override
    public Question add(String question, String answer) {

        checkQAContent(question, answer);

        Question text = new Question(question, answer);
        questions.add(text);

        return text;
    }

//    @Override
//    public Question add(Question question) {
//
//        if (questions.contains(question)) {
//            throw new QuestionIsAlreadyAddedException("Question is already added");
//        }
//
//        questions.add(question);
//        return question;
//    }
//
//    @Override
//    public Question remove(Question question) {
//
//        checkForNull();
//
//        questions.remove(question);
//        return question;
//    }
//
//    @Override
//    public Collection<Question> getAll() {
//
//        checkForNull();
//        return questions;
//    }
//
//    @Override
//    public Question getRandomQuestion() {
//
//        checkForNull();
//
//        Random random = new Random();
//        int number = random.nextInt(questions.size());
//
//        return questions.get(number);
//    }
//
//    private void checkForNull() {
//        if (questions.isEmpty()) {
//            throw new QuestionIsOutException("Question is null");
//        }
//    }
//
//    private void checkQAContent(String question, String answer) {
//        if (question == null || answer == null || question.equals(answer)) {
//            throw new IncorrectQuestionAnswerInput();
//        }
//    }
}
