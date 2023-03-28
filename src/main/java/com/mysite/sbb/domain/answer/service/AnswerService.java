package com.mysite.sbb.domain.answer.service;

import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.domain.answer.model.Answer;
import com.mysite.sbb.domain.answer.repository.AnswerRepository;
import com.mysite.sbb.domain.question.model.Question;
import com.mysite.sbb.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    public Answer create(Question question, String content, User author) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        answer.setAuthor(author);
        this.answerRepository.save(answer);
        return answer;
    }
    public Answer getAnswer(Long id){
        Optional<Answer> answer = this.answerRepository.findById(id);
        if(answer.isPresent()){
            return answer.get();
        }
        else{
            throw new DataNotFoundException("answer not found");
        }
    }
    public void modify(Answer answer, String content){
        answer.setContent(content);
        answer.setModifyDate(LocalDateTime.now());
        this.answerRepository.save(answer);
    }
    public void delete(Answer answer) {
        this.answerRepository.delete(answer);
    }
    public void vote(Answer answer, User siteUser) {
        answer.getVoter().add(siteUser);
        this.answerRepository.save(answer);
    }
}
