package com.mysite.sbb;


import com.mysite.sbb.domain.question.model.Question;
import com.mysite.sbb.domain.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;


@Controller
@RequiredArgsConstructor
public class HelloController {
    private final QuestionRepository questionRepository;

    @Transactional
    @GetMapping("/")
    public String root(){
        Question question1 = new Question();
        question1.setSubject("subject1");
        question1.setContent("content1");
        question1.setCreateDate(LocalDateTime.now());

        Question question2 = new Question();
        question2.setSubject("subject2");
        question2.setContent("content2");
        question2.setCreateDate(LocalDateTime.now());

        questionRepository.save(question1);
        questionRepository.save(question2);

        return "redirect:/question/list";
    }
}
