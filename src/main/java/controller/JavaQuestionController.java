package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.JavaQuestionService;
import model.Question;
import service.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping(path = "/exam/java")

public class JavaQuestionController {
    @Autowired
    private final QuestionService questionService;

    public JavaQuestionController(@Qualifier("JavaQuestionService")
                                  QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/add")
    public Question addQuestion (@RequestParam("question") String question,
                            @RequestParam("answer") String answer) {

       return questionService.add(question, answer);

    }

    @GetMapping("/remove")
    public Question removeQuestion(@RequestParam("question") String question,
                                   @RequestParam("answer") String answer) {

        return questionService.remove(new Question(question, answer));
    }

    @GetMapping("")
    public Collection<Question> showAllQuestions() {

        return questionService.getAll();
    }

//        if (questions.contains(text)) {
//            throw new QuestionIsAlreadyAddedException("Question is already added");
//        }
//
//        Question question = new Question(text, answer);
//        questions.add(question);
//        return question;
//    }

//
//    Collection<Question> getQuestions();
//    Question removeQuestion(String question, String answer);


}