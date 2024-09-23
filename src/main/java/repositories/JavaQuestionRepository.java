package repositories;

import jakarta.annotation.PostConstruct;
import model.Question;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class JavaQuestionRepository implements QuestionRepository {
    private final Set<Question> JAVA_QUESTIONS = new HashSet<>();

    @PostConstruct
    public void init() {
        JAVA_QUESTIONS.add(new Question("5", "7"));
        JAVA_QUESTIONS.add(new Question("6", "8"));
        JAVA_QUESTIONS.add(new Question("7", "9"));
    }

    @Override
    public Question add(Question question) {

        JAVA_QUESTIONS.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {

        JAVA_QUESTIONS.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {

        return List.copyOf(JAVA_QUESTIONS);
    }
}
