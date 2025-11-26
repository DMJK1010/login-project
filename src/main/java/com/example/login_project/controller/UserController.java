package com.example.login_project.controller;

import com.example.login_project.dto.UserLoginDto;
import com.example.login_project.dto.UserSignupDto;
import com.example.login_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController // 결과값(JSON/String)을 그대로 리턴 (화면 이동 X)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public String signUp(@RequestBody UserSignupDto userSignupDto) throws Exception {
        userService.signUp(userSignupDto);
        return "회원가입 성공";
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginDto userLoginDto) throws Exception {

        return userService.login(userLoginDto);
    }

    @GetMapping("/info")
    public String userInfo(Authentication auth){
        return "로그인된 사용자는 " + auth.getName() + " 입니다.";
    }
}