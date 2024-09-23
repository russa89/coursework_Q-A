package controller;

import exceptions.TooManyQuestionsException;
import model.Question;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.ExaminerService;
import service.ExaminerServiceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class ExamController {

    private final ExaminerService service;

    public ExamController(ExaminerService service) {
        this.service = service;
    }

    @GetMapping("/get/{amount}")
    public Collection<Question> getQuestions(@PathVariable("amount") int amount) {

        return service.getQuestions(amount);

    }
}
