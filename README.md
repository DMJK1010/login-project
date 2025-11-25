# 🔐 Login Project (Spring Boot Practice)

Spring Boot를 활용한 **회원가입 및 이메일 발송 기능** 구현 프로젝트입니다.
보안을 고려한 비밀번호 암호화와 실제 이메일 전송 기능을 포함하고 있습니다.

## 🛠 Tech Stack

- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA** (MySQL)
- **Spring Security** (Password Encoding)
- **Java Mail Sender** (SMTP)
- **Lombok**

## ✨ Key Features (구현 기능)

### 1. 회원가입 (Sign Up)
- **API Endpoint:** `POST /signup`
- 사용자로부터 `email`과 `password`를 입력받아 회원가입을 진행합니다.
- **중복 검사:** 이미 가입된 이메일이 존재하면 예외를 발생시킵니다.
- **암호화:** `BCryptPasswordEncoder`를 사용하여 비밀번호를 암호화해 DB에 저장합니다.

### 2. 이메일 발송 (Email Notification)
- 회원가입이 정상적으로 완료되면, 가입한 이메일 주소로 **"가입 축하 메일"**이 자동 발송됩니다.
- Google SMTP 서버를 연동하여 구현했습니다.

## 📝 API Specification

### 회원가입 요청 (Request)
**POST** `http://localhost:8081/signup`

**Body (JSON)**
```
json
{
  "email": "test@example.com",
  "password": "mypassword123"
}
```

## Project Structer
```
src/main/java/com/example/login_project
├── config       # Security 설정 (SecurityConfig)
├── controller   # 웹 요청 처리 (UserController)
├── dto          # 데이터 전송 객체 (UserSignupDto)
├── repository   # DB 접근 (UserRepository)
├── service      # 비즈니스 로직 (UserService, EmailService)
└── user         # DB 엔티티 (User)
```
