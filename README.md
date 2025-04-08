## 기능
- 제시된 기능 요구사항 모두 구현
- 회원 정보 변경, 회원 탈퇴, 로그아웃 기능 추가
- Swagger를 이용한 API 문서화
- Unit Test 작성

<br>
<br>

## 어플리케이션 실행 방법
### docker 실행
- docker-compose.yml 이 있는 root 에서 다음 명령어 실행.
  - docker compose up --build
### local 실행
- local 에서 실행중인 mysql 서버에 붙어야 함.
  - application.yml db설정에서 url, port, username, password 를 local mysql에 맞게 수정 후 어플리케이션 실행
### Swagger
- 어플리케이션 실행 후 http://localhost:8080/swagger-ui/index.html 에서 테스트.
  - 기본적으로 어플리케이션을 실행하면 테이블 및 더미 데이터가 자동으로 생성.
  - 회원가입 -> 로그인 -> 나머지 API 순으로 테스트.
  - 목록 조회 페이징의 경우 swagger의 sort[] 부분은 빼고 요청.
    - ex) { "page": 0, "size": 10 }
  - 다음 유저로 로그인 하시면 미리 생성해둔 더미 데이터로 찜, 찜서랍 목록 조회 가능.
    - email: aaa@ably.com
    - password: qwer1234!!


<br>
<br>

## 🛠️ 기술 스택
- Java 17
- Spring Boot 3
- MySQL 8.0
- Jpa
- QueryDSL
- Swagger

<br>
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
