package com.example.login_project.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter // getter 자동 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED) //protected User() {}
@Entity // db에 엔티티라고 명시
@Table(name = "users") // 테이블 이름 users
public class User {
    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // db가 순서대로 1,2,3,4
    @Column(name = "id", updatable = false) // 변경 불가
    private long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    //User.builder().email("a@b.com").password("1234").build()
    //처럼 호출해서 기존 new User("a@b.com", "1234")처럼 순서를 지키지 않아도 됨
    @Builder
    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

}