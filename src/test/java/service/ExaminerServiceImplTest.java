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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    JavaQuestionService javaQuestionService = mock(JavaQuestionService.class);
    MathQuestionService mathQuestionService = mock(MathQuestionService.class);

    ExaminerServiceImpl out = new ExaminerServiceImpl(javaQuestionService, mathQuestionService);

    private final List<Question> javaQuestions = List.of(
            new Question("q1", "a1"),
            new Question("q2", "a2")
    );
    private final List<Question> mathQuestions = List.of(
            new Question("q3", "a3"),
            new Question("q4", "a4")
    );

    @Test
    void getQuestions() {

        when(javaQuestionService.getRandomQuestion()).thenReturn(javaQuestions.get(0)).
                thenReturn(javaQuestions.get(1));
        when(mathQuestionService.getRandomQuestion()).thenReturn(mathQuestions.get(0)).
                thenReturn(mathQuestions.get(1));
        when(javaQuestionService.getAll()).thenReturn(javaQuestions);
        when(mathQuestionService.getAll()).thenReturn(mathQuestions);
        int expectedAmount = 4;
        assertEquals(expectedAmount, out.getQuestions(expectedAmount).size());
    }

}

