package service;

import exceptions.IncorrectQuestionAnswerInput;
import exceptions.QuestionIsAlreadyAddedException;
import exceptions.QuestionIsOutException;
import model.Question;
import org.springframework.stereotype.Service;
import repositories.JavaQuestionRepository;
import repositories.MathQuestionRepository;
import repositories.QuestionRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@Service
public class JavaQuestionService implements QuestionService {

    private final QuestionRepository repository;

    public JavaQuestionService(JavaQuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Question add(String question, String answer) {

        Question newQuestion = new Question(question, answer);
        checkQAContentForNull(question, answer);

        if (repository.getAll().contains(question)) {
            throw new QuestionIsAlreadyAddedException("Question is already added");
        }

        return repository.add(newQuestion);
    }

    @Override
    public Question add(Question question) {

        if (repository.getAll().contains(question)) {
            throw new QuestionIsAlreadyAddedException("Question is already added");
        }
        return repository.add(question);
    }

    @Override
    public Question remove(Question question) {
        checkIfQuestionIsNull();
        if (!repository.getAll().contains(question)) {
            throw new QuestionIsOutException("Question doesn't exist");
        }
        return repository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        checkIfQuestionIsNull();
        return repository.getAll();
    }

    @Override
    public Question getRandomQuestion() {

        Random random = new Random();
        List<Question> questions = (List<Question>) repository.getAll();
        return questions.get(random.nextInt(questions.size()));

    }

    private void checkIfQuestionIsNull() {
        List<Question> questions = (List<Question>) repository.getAll();

        if (questions.isEmpty()) {
            throw new QuestionIsOutException("Question is null");
        }
    }

    private void checkQAContentForNull(String question, String answer) {
        if (question == null || answer == null) {
            throw new IncorrectQuestionAnswerInput();
        }
    }

}
