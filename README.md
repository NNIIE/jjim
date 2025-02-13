# 에이블리 백엔드 과제 - 이용석

<br>

## 기능
- 제시된 기능 요구사항 모두 구현
- 회원 정보 변경, 회원 탈퇴, 로그아웃 기능 추가
- Unit Test 작성

<br>

## 어플리케이션 실행 방법
aaa

<br>

## 🛠️ 기술 스택
- Java 17
- Spring Boot
- Jpa
- MySQL

<br>

## 📜 API
### 회원
| 기능       | METHOD | URL                  |
|----------|--------|----------------------|
| 회원 정보 조회 | GET    | /user              |
| 회원 가입    | POST   | /user                |
| 회원 정보 변경 | PATCH  | /user              |
| 회원 탈퇴    | DELETE | /user              |

### 인증
| 기능   | METHOD | URL            |
|------|--------|----------------|
| 로그인  | POST   | /auth/sign-in  |
| 로그아웃 | POST   | /auth/sign-out |

### 찜서랍
| 기능        | METHOD | URL          |
|-----------|--------|--------------|
| 찜서랍 목록 조회 | GET    | /drawer      |
| 찜서랍 생성    | POST   | /drawer      |
| 찜서랍 삭제    | DELETE  | /drawer/{id} |

### 찜
| 기능      | METHOD | URL                   |
|---------|--------|-----------------------|
| 찜 목록 조회 | GET    | /favorite/drawer/{id} |
| 찜 추가    | POST   | /favorite             |
| 찜 삭제    | DELETE  | /favorite/{id}        |