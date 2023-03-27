package com.mysite.sbb.domain.question.controller;

import com.mysite.sbb.domain.answer.dto.AnswerForm;
import com.mysite.sbb.domain.question.dto.QuestionForm;
import com.mysite.sbb.domain.question.model.Question;
import com.mysite.sbb.domain.question.service.QuestionService;
import com.mysite.sbb.domain.user.model.User;
import com.mysite.sbb.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {

    private final QuestionService questionService;
    private final UserService userService;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page){
        Page<Question> paging = this.questionService.getList(page);

        for (Question question:paging.getContent())
            System.out.println(question);
        model.addAttribute("paging",paging);
        return "question_list";
    }
    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id, AnswerForm answerForm){
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question",question);
        return "question_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm){
        return "question_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal)
    {
        User user = this.userService.getUser(principal.getName());
        if(bindingResult.hasErrors())
        {
            return "question_form";
        }
        questionService.create(questionForm.getSubject(),questionForm.getContent(), user);
        return "redirect:/question/list";
    }
}
