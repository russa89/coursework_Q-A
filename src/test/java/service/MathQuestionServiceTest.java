package service;

import exceptions.QuestionIsAlreadyAddedException;
import exceptions.QuestionIsOutException;
import model.Question;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import repositories.JavaQuestionRepository;
import repositories.MathQuestionRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@ExtendWith(MockitoExtension.class)
class MathQuestionServiceTest {
    private final List<Question> MATH_QUESTIONS = List.of(
            new Question("1", "4"),
            new Question("2", "5"),
            new Question("3", "6"));
    MathQuestionRepository repository = mock(MathQuestionRepository.class);
    MathQuestionService out = new MathQuestionService(repository);

    @Test
    @DisplayName("Должен добавить вопрос/ответ")
    void shouldAddQA() {

        Question expected = new Question("question", "answer");
        when(repository.add(expected)).thenReturn(expected);
        assertEquals(out.add("question", "answer"), expected);
    }

    @Test
    @DisplayName("Должен выбросить исключение, если вопрос/ответ уже добавлен")
    void shouldThrowAnExceptionWhenQAAlreadyAdded() {

        Question expected = new Question("1", "4");
        when(repository.getAll()).thenReturn(MATH_QUESTIONS);

        assertThatExceptionOfType(QuestionIsAlreadyAddedException.class).isThrownBy(
                () -> out.add(expected));
    }

    @Test
    @DisplayName("Должен удалить вопрос/ответ")
    void shouldRemoveQA() {

        Question expected = new Question("question", "answer");
        when(repository.remove(expected)).thenReturn(expected);

        assertEquals(expected, repository.remove(expected));
    }

    @Test
    @DisplayName("Должен выбросить исключение, если вопрос/ответ не найден")
    void shouldThrowAnException1() {

        when(repository.getAll()).thenReturn(MATH_QUESTIONS);

        assertThatExceptionOfType(QuestionIsOutException.class).isThrownBy(
                () -> out.remove(new Question("1", "2")));

    }

    @Test
    @DisplayName("Должен выбросить исключение, если параметр не задан")
    void shouldThrowAnException2() {

        Question expected = new Question(null, "2");
        when(repository.getAll()).thenReturn(MATH_QUESTIONS);

        assertThatExceptionOfType(QuestionIsOutException.class).isThrownBy(
                () -> out.remove(expected));

    }

    @Test
    @DisplayName("Получить все вопросы/ответы")
    void getAll() {

        when(repository.getAll()).thenReturn(MATH_QUESTIONS);

        List<Question> expected = new ArrayList<>(List.of(
                new Question("1", "4"),
                new Question("2", "5"),
                new Question("3", "6")
        ));
        assertEquals(repository.getAll(), expected);
    }

    @Test
    @DisplayName("Должен выбросить исключение, если параметр не задан")
    void getAllNegative() {

        Question expected = new Question(null, "2");
        when(repository.getAll()).thenReturn(MATH_QUESTIONS);

        assertThatExceptionOfType(QuestionIsOutException.class).isThrownBy(
                () -> out.remove(expected));
    }

    @Test
    @DisplayName("Получить рандомные вопросы/ответы")
    void getRandomQuestion() {
        when(repository.getAll()).thenReturn(MATH_QUESTIONS);
        List<Question> questions = (List<Question>) out.getAll();

        Question expected1 = new Question("1", "4");
        Question expected2 = new Question("2", "5");
        Question expected3 = new Question("3", "6");

        assertEquals(questions.get(0), expected1);
        assertEquals(questions.get(1), expected2);
        assertEquals(questions.get(2), expected3);

    }
}
