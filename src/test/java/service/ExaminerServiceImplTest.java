package service;

import org.assertj.core.api.Assertions;
import exceptions.TooManyQuestionsException;
import model.Question;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private JavaQuestionService javaQuestionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    private final Collection<Question> questions = Set.of(
            new Question("q1", "a1"),
            new Question("q2", "a2"),
            new Question("q3", "a3"),
            new Question("q4", "a4")
    );

    @Test
    void getQuestions() {

        when(javaQuestionService.getAll()).thenReturn(questions);
        when(javaQuestionService.getRandomQuestion()).thenReturn(
                new Question("q1", "a1"),
                new Question("q2", "a2"),
                new Question("q3", "a3")
        );

        Assertions.assertThat(examinerService.getQuestions(3))
                .hasSize(3)
                .containsExactlyInAnyOrder(
                        new Question("q1", "a1"),
                        new Question("q3", "a3"),
                        new Question("q2", "a2")
                );

    }
}
