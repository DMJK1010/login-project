package com.example.login_project.repository;

import com.example.login_project.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 로그인, 중복 가입 체크
    Optional<User> findByEmail(String email);

}