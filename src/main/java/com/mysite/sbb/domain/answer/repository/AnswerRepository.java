package com.mysite.sbb.domain.answer.repository;

import com.mysite.sbb.domain.answer.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
