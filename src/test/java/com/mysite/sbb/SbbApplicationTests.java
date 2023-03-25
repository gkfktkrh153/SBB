package com.mysite.sbb;

import com.mysite.sbb.domain.question.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SbbApplicationTests {

    @Autowired
    private QuestionService questionService;


    @Test
    void testJpa(){
        for (int i = 0; i <= 300; i++){
            String subject = String.format("테스트 데이터[%d]입니다", i);
            String content = "내용없음";
            this.questionService.create(subject, content);
        }
    }

}
