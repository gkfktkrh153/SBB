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
        return "redirect:/question/list";
    }
}
