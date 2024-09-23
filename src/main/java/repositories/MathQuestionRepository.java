package repositories;

import jakarta.annotation.PostConstruct;
import model.Question;
import org.springframework.stereotype.Repository;
import service.QuestionService;

import java.util.*;

@Repository
public class MathQuestionRepository implements QuestionRepository {

    private final Set<Question> MATH_QUESTIONS = new HashSet<>();

    @PostConstruct
    public void init() {
        MATH_QUESTIONS.add(new Question("1", "4"));
        MATH_QUESTIONS.add(new Question("2", "5"));
        MATH_QUESTIONS.add(new Question("3", "6"));
    }


    @Override
    public Question add(Question question) {
        MATH_QUESTIONS.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        MATH_QUESTIONS.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return List.copyOf(MATH_QUESTIONS);
    }
}
