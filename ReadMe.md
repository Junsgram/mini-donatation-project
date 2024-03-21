# Side Project: Donation

웹사이트는 기부자가 마일리지를 충전하여, 에피소드별 기부 또는 상품 구매를 통해 기부할 수 있는 시스템을 구축하는 것을 목표

---

### 1. 개발환경 설정

- IntelliJ IDEA, Java SDK, 그리고 필요한 Spring Boot IntelliJ 플러그인 설치 및 설정.

### 2. 프로젝트 생성 및 기본 설정

- Spring Initializr를 사용하여 새 Spring Boot 프로젝트 생성.
- 필요한 Spring Boot Starter (예: Spring Web, Spring Data JPA, Spring Security) 추가.
-  **`application.yml`** 파일을 통해 데이터베이스 및 기타 환경 설정.

### 3. 도메인 모델 및 DB설계

- 기부자(Donor): 기부자 정보, 마일리지 잔액

### **4. 리포지토리 계층 구현**

- Spring Data JPA를 사용하여 각 도메인 모델에 해당하는 리포지토리 인터페이스 구현.

### **5. 서비스 계층 구현**

- 사용자 관리, 마일리지 관리, 에피소드 기부, 상품 구매 등의 비즈니스 로직을 처리하는 서비스 계층 구현.

### **6. 컨트롤러 및 API 구현**

- 사용자 등록, 로그인, 마일리지 충전 및 사용, 에피소드 및 상품에 대한 기부를 위한 REST API 구현.

### **7. 보안 설정**

- Spring Security를 사용하여 인증 및 권한 부여 설정. 사용자 인증을 위한 로그인 API 및 인증 토큰 관리 구현.

### **8. 프론트엔드 개발**

- BootStrap을 사용하여 사용자 인터페이스 구축. 사용자 등록, 로그인, 마일리지 관리, 에피소드 및 상품 게시판 구현.

### **9. 테스트 및 배포**

- Heroku 클라우드 서비스를 사용하여 웹 애플리케이션 배포 예정.

---
# UseCase
![UseCase](https://github.com/Junsgram/mini-donatation-project/assets/158553195/2e4672b6-815d-415f-b4f6-783709759122)
---
# ERD
![Donemile_Project_ERD](https://github.com/Junsgram/mini-donatation-project/assets/158553195/2a30bc59-7d10-4551-923f-a089deea4231)
