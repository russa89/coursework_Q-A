package service;

import model.Question;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import com.github.javafaker.Faker;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class JavaQuestionServiceTest {

    private static final Faker faker = new Faker();
    private final JavaQuestionService service = new JavaQuestionService();

    @Test
    @DisplayName("Должен добавить вопрос/ответ")
    void shouldAddQA() {

        Question expected = new Question("question", "answer");
        Question actual = service.add(new Question("question", "answer"));

        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Должен удалить вопрос/ответ")
    void shouldRemoveQA() {

        Question actual = service.add(new Question("question", "answer"));

        assertEquals(actual, service.remove(new Question(actual.getQuestion(), actual.getAnswer())));

    }

    @Test
    @DisplayName("Получить все вопросы/ответы")
    void getAll() {

        service.add("q", "a");
        service.getAll();

        assertEquals(service.getAll().size(), 1);
    }

    @Test
    void getRandomQuestion() {

        service.add("q", "a");
        service.add("q1", "a1");
        service.add("q2", "a2");

        for (int i = 0; i < service.getAll().size(); i++) {
            Assertions.assertThat(service.getAll())
                    .contains(service.getRandomQuestion());
        }
    }
}