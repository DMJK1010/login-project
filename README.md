# 🔐 Login Project (Spring Boot Practice)

> Spring Boot를 활용한 **회원가입, 로그인 및 JWT 인증** 구현 프로젝트입니다.
보안을 고려한 비밀번호 암호화, 이메일 전송, 그리고 **JWT(JSON Web Token)** 기반의 stateless 인증 시스템을 포함하고 있습니다.

## 🛠 Tech Stack

- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA** (MySQL)
- **Spring Security** (Password Encoding & Security Filter)
- **Java Mail Sender** (SMTP)
- **JWT (jjwt)** (Token based Authentication)
- **Lombok**

## ✨ Key Features (구현 기능)

### 1. 회원가입 (Sign Up)
- **API Endpoint:** `POST /signup`
- 사용자로부터 `email`과 `password`를 입력받아 회원가입을 진행합니다.
- **중복 검사:** 이미 가입된 이메일이 존재하면 예외를 발생시킵니다.
- **암호화:** `BCryptPasswordEncoder`를 사용하여 비밀번호를 안전하게 암호화해 DB에 저장합니다.

### 2. 이메일 발송 (Email Notification)
- 회원가입이 정상적으로 완료되면, 가입한 이메일 주소로 **"가입 축하 메일"**이 자동 발송됩니다.
- Google SMTP 서버를 연동하여 구현했습니다.

### 3. 로그인 (Login) & 토큰 발급
- **API Endpoint:** `POST /login`
- 이메일과 비밀번호를 검증하여 일치할 경우 **JWT(Access Token)**을 발급합니다.
- **보안 강화:** 계정 열거 공격 방지를 위해 아이디/비밀번호 오류 시 메시지를 통일했습니다.

### 4. JWT 인증 및 인가 (Authentication & Authorization)
- **Security Filter:** `JwtFilter`를 통해 모든 요청의 헤더(`Authorization`)를 검사합니다.
- **Token 검증:** 유효한 토큰(Bearer Token)을 가진 사용자만 보호된 리소스(예: 내 정보 보기)에 접근할 수 있습니다.

---

## 📝 API Specification

### 1. 회원가입 (Sign Up)
**POST** 'http://localhost:8081/signup`

**Body (JSON)**
```json
{
  "email": "test@example.com",
  "password": "mypassword123"
}
```

### 2. 로그인 (Login)
**POST** 'http://localhost:8081/login'

**Body (JSON)**
```json
{
  "email": "test@example.com",
  "password": "mypassword123"
}
```

### 3. 내 정보 보기
**GET** 'http://localhost:8081/info'

**Headers**
- Authorization Bearer {token}

## ⚙️ Environment Variables
⚠️ 보안을 위해 비밀번호와 키 값은 application-secret.yaml과 환경 변수로 관리합니다.

## Project Structer
```
src/main/java/com/example/login_project
├── config       # Security 및 Filter 설정 (SecurityConfig, JwtFilter)
├── controller   # 웹 요청 처리 (UserController)
├── dto          # 데이터 전송 객체 (UserSignupDto, UserLoginDto)
├── repository   # DB 접근 (UserRepository)
├── service      # 비즈니스 로직 (UserService, EmailService)
├── user         # DB 엔티티 (User)
└── utils        # JWT 생성 및 검증 도구 (JwtUtil)
```

### 💡 11/26 추가된 내용 요약
1.  **Tech Stack:** `JWT (jjwt)`가 추가되었습니다.
2.  **Key Features:** `로그인`과 `JWT 인증` 기능 설명이 추가되었습니다.
3.  **API Specification:** `/login`과 `/info` (헤더 포함) 사용법이 추가되었습니다.
4.  **Project Structure:** `utils` 패키지와 새로 생긴 파일들이 구조도에 반영되었습니다.

