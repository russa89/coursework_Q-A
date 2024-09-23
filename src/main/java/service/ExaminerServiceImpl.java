package service;

import exceptions.TooManyQuestionsException;
import model.Question;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    @Qualifier("javaQuestionService")
    private final QuestionService javaQuestionService;

    @Qualifier("mathQuestionService")
    private final QuestionService mathQuestionService;
//    private final Random random;

    public ExaminerServiceImpl(QuestionService javaQuestionService, QuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Random random = new Random();
        Set<Question> questions = new HashSet<>();

        if (javaQuestionService.getAll().size() +
                mathQuestionService.getAll().size() > amount) {
            throw new TooManyQuestionsException();
        }

        while (questions.size() < amount) {
            if (!javaQuestionService.getAll().isEmpty()) {
                questions.add(javaQuestionService.getRandomQuestion());
            } else if (!mathQuestionService.getAll().isEmpty()) {
                questions.add(mathQuestionService.getRandomQuestion());

            }
        }
        return questions;
    }
}



