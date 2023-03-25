package com.mysite.sbb.domain.user.repository;

import com.mysite.sbb.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
