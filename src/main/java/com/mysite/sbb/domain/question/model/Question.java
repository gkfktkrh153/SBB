package com.mysite.sbb.domain.question.model;

import com.mysite.sbb.domain.answer.model.Answer;
import com.mysite.sbb.domain.user.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    private User author;

    @ManyToMany
    Set<User> voter;

    private LocalDateTime createDate;
    private LocalDateTime modifyDate;


    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;
}
