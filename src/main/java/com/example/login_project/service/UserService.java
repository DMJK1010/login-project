package com.example.login_project.service;

import com.example.login_project.utils.JwtUtil;
import com.example.login_project.dto.UserLoginDto;
import com.example.login_project.dto.UserSignupDto;
import com.example.login_project.repository.UserRepository;
import com.example.login_project.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final JwtUtil jwtUtil;

    @Transactional
    public Long signUp(UserSignupDto userSignupDto) throws Exception {

        //이메일 중복 검사
        if(userRepository.findByEmail(userSignupDto.getEmail()).isPresent()){
            throw new Exception("이미 존재하는 이메일입니다.");
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(userSignupDto.getPassword());

        User user = User.builder().email(userSignupDto.getEmail()).password(encodedPassword).build();

        userRepository.save(user);

        emailService.sendEmail(user.getEmail(), "가입을 축하합니다!", "회원가입이 성공적으로 완료되었습니다.");

        return user.getId();
    }

    public String login(UserLoginDto userLoginDto) throws Exception {
        User user = userRepository.findByEmail(userLoginDto.getEmail()).orElseThrow(() -> new Exception("이메일 또는 비밀번호가 일치하지 않습니다."));

        if(!passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword())){
            throw new Exception("이메일 또는 비밀번호가 일치하지 않습니다.");
        }
        return jwtUtil.createToken(user.getEmail(), user.getId());
    }
}
