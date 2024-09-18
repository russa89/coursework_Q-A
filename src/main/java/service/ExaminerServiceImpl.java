package service;

import exceptions.TooManyQuestionsException;
import model.Question;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;
    private final Random random;

    public ExaminerServiceImpl(QuestionService questionService, Random random) {
        this.questionService = questionService;
        this.random = new Random();
    }

    @Override
    public Collection<Question> getQuestions(int amount) {

        if (questionService.getAll().size() < amount){
            throw new TooManyQuestionsException();
        }

        List<Question> questions = new ArrayList<>();

        while (questions.size() < amount) {
            Question question = questionService.getRandomQuestion();
            if (!questions.contains(question)) {
                questions.add(question);
            }
        }
        return questions;

//        for (int i = 0; i < questions.size(); i++) {
//            int q = random.nextInt(questions.size());
//            return (Collection<Question>) questions.toArray()[q];
//            }

        }
    }



