# 1. Project Overview (프로젝트 개요)
- 네이버_실장님 프로젝트_BE
- 프로젝트 설명: CRUD 기능이 포함된 간단한 게시판  웹 프로젝트


# 2. Team Members (팀원 및 팀 소개)
| 이름            | 역할 | GitHub 링크                                 |
| --------------- |----| ------------------------------------------- |
| 이수연          | PL | [GitHub](https://github.com/lsy28901)        |
| 유종화          | BE | [GitHub](https://github.com/YOOJONGHWA)        |
| 서현욱          | BE | [GitHub](https://github.com/seohyeonwook)        |
| 이현배          | BE | [GitHub](http://github.com/HyunbaeL)       |


# 3. Key Features (주요 기능)
- **회원**:
    - 회원가입 시 DB에 유저정보가 등록됩니다.

- **로그인**:
    - 이메일, 비밀번호를 입력하여 로그인 완료시 jwt토큰이 발급 됩니다.

- **조회**:
  - 사용자가 작성한 게시물을 전체 조회 가능합니다.
  - 작성자 이메일을 통해 특정 게시물을 검색할수 있습니다.
  - 게시물 상세 페이지에 댓글들을 조회 할수 있습니다.

- **생성**:
  - 댓글을 새롭게 만들 수 있습니다.
  - 게시물을 새롭게 만들 수 있습니다.

- **수정**:
  - 기존 댓글의 글을 수정 할수 있습니다.
  - 기존 게시물의 글을 수정 할수 있습니다.

- **삭제**:
  - 기존 댓글의 글을 삭제 할수 있습니다.
  - 기존 게시물의 글을 삭제 할수 있습니다.

- **좋아요**:
  - 기존 게시물에 좋아요를 추가 삭제 할수 있습니다.



# 4. Tasks & Responsibilities (작업 및 역할 분담)
| 이름  | 역할 및 작업                                                               |
|-----|-----------------------------------------------------------------------|
| 이수연 | <ul><li>프로젝트 계획 및 관리</li><li>팀 리딩 및 커뮤니케이션</li><li>댓글 CRUD </li></ul> |
| 유종화 | <ul><li>리엑트 연동</li><li>좋아요 기능</li><li>GIT Repository 관리</li>          |
| 서현욱 | <ul><li>게시판CRUD</li><li>이메일 검색</li>                                   |
| 이현배 | <ul><li>로그인</li><li>회원가입</li><li>jwt</li><li>Security</li>            |


# 5. 개발환경 및 기술 스택


이 프로젝트는 다음의 개발 환경 및 기술 스택을 사용하여 구현되었습니다.

## 운영 체제
- **Windows**

## IDE
- **IntelliJ IDEA (IJ)**

## 버전 관리
- **Git**
- **GitHub**

## API 테스트 도구
- **Postman**

## 데이터베이스
- **MySQL**

## 프로그래밍 언어
- **Java**

## 프레임워크
- **Spring Boot**
- **Spring Security**
- **Spring Data JPA**

## 기타 라이브러리
- **Project Lombok**
- **JWT (JSON Web Token)**
- **Swagger UI**
<br/>

# 6. Project Structure (프로젝트 구조)
```plaintext

study.supercoding_1
│
├── config              # 설정 관련 패키지
│   └── ...
│
├── controller          # HTTP 요청을 처리하는 컨트롤러 패키지
│   └── ...
│
├── dto                 # Data Transfer Object 패키지
│   └── ...
│
├── entity              # 엔티티 클래스 패키지
│   └── ...
│
├── exception           # 예외 처리 관련 패키지
│   └── ...
│
├── jwt                 # JWT 관련 클래스 패키지
│   └── ...
│
├── repository          # 데이터베이스와의 상호작용을 위한 레포지토리 패키지
│   └── ...
│
└── service             # 비즈니스 로직을 처리하는 서비스 패키지
    └── ...
```

# 7. Development Workflow (개발 워크플로우)
## 브랜치 전략 (Branch Strategy)
우리의 브랜치 전략은 Git Flow를 기반으로 하며, 다음과 같은 브랜치를 사용합니다.

- Main Branch
    - 배포 가능한 상태의 코드를 유지합니다.
    - 모든 배포는 이 브랜치에서 이루어집니다.
- dev Branch
    - 각자의 개발 브랜치를 합병하는 브렌치입니다.
    - 모든 코드가 정상 작동 하는 확인이 이루어집니다.
- {name} Branch
    - 팀원 각자의 개발 브랜치입니다.
    - 모든 기능 개발은 이 브랜치에서 이루어집니다.


