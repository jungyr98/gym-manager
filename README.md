
# Gym Manager Project Backend 🥊📊

<!-- 프로젝트 결과물 -->
* 로그인
![image](https://github.com/user-attachments/assets/2bfd38f5-30c7-4316-9c49-43f0fd09adf8)

* 대시보드
![image](https://github.com/user-attachments/assets/211e99a1-c1bb-41d2-973e-ab4c22cf143a)

* 회원 목록
![image](https://github.com/user-attachments/assets/f6f4c69a-5326-4c31-bd1d-6ce403e25ff2)

* 회원 상세
![image](https://github.com/user-attachments/assets/69381718-cdb3-4db2-a6f9-ed46c4b9acc3)

* 캘린더
![image](https://github.com/user-attachments/assets/b539931f-b159-48fc-9922-5a3e37a56441)

* 매출 통계
![image](https://github.com/user-attachments/assets/499c6df9-21d7-4907-b7f9-e0a6a633e97b)




## ❔ 소개
- 체육시설 운영자를 위한 체육시설 관리 웹 프로젝트.
  * 여러 복싱 체육관을 다니면서, 내가 체육관 운영자라면 어떤 관리자 전용 웹을 사용하고 싶을지 고민하며 제작하게 된 프로젝트
- 특징
  * Spring Boot / React & Next.js / TypeScript 를 이용한 서버 API와 사용자 친화적인 웹 UI를 갖춘 애플리케이션
  * Spring Security와 JWT(JSON Web Token)을 이용한 간편한 인증 방식
  * Ant Design 라이브러리를 이용한 정돈된 UI
  * Apache ECharts 라이브러리를 이용한 각종 통계 차트
  * Recoil을 이용한 메뉴 상태 관리
  * 대시보드, 회원 관리, 일정 관리, 방문 기록, 매출 통계 등 주 기능 존재

## 📑 Spec

### Language
<img src="https://img.shields.io/badge/Java-407291?style=flat-square&logo=java&logoColor=white"/> <img src="https://img.shields.io/badge/Next.js-000000?style=flat-square&logo=nextdotjs&logoColor=white"/> <img src="https://img.shields.io/badge/Node.js-5FA04E?style=flat-square&logo=Node.js&logoColor=white"/>   
<img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=flat-square&logo=javascript&logoColor=white"/> <img src="https://img.shields.io/badge/React-61DAFB?style=flat-square&logo=react&logoColor=white"/> <img src="https://img.shields.io/badge/TypeScript-3178C6?style=flat-square&logo=typescript&logoColor=white"/> <img src="https://img.shields.io/badge/CSS3-1572B6?style=flat-square&logo=css3&logoColor=white"/>

### Build Tool
<img src="https://img.shields.io/badge/Gradle-02303A?style=flat-square&logo=gradle&logoColor=white"/> <img src="https://img.shields.io/badge/Next.js-000000?style=flat-square&logo=nextdotjs&logoColor=white"/>

### Skill
<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat-square&logo=springboot&logoColor=white"/> <img src="https://img.shields.io/badge/Lombok-a14933?style=flat-square&logo=lombok&logoColor=white"/> <img src="https://img.shields.io/badge/Spring Data JPA-6DB33F?style=flat-square&logo=spring&logoColor=white"/> <img src="https://img.shields.io/badge/Swagger-85EA2D?style=flat-square&logo=swagger&logoColor=white"/>   
<img src="https://img.shields.io/badge/.ENV-ECD53F?style=flat-square&logo=dotenv&logoColor=white"/> <img src="https://img.shields.io/badge/Ant Design-0170FE?style=flat-square&logo=antdesign&logoColor=white"/> <img src="https://img.shields.io/badge/Spring Securiy-6DB33F?style=flat-square&logo=springsecurity&logoColor=white"/>
<img src="https://img.shields.io/badge/Apache ECharts-AA344D?style=flat-square&logo=apacheecharts&logoColor=white"/> <img src="https://img.shields.io/badge/Recoil-3578E5?style=flat-square&logo=recoil&logoColor=white"/>

### DBMS
<img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white"/>

### Version Controll System
<img src="https://img.shields.io/badge/Git-F05032?style=flat-square&logo=git&logoColor=white"/>

### IDE
<img src="https://img.shields.io/badge/Eclipse IDE-2C2255?style=flat-square&logo=eclipseide&logoColor=white"/> <img src="https://img.shields.io/badge/VSCode-22a6f2?style=flat-square&logo=visualstudiocode&logoColor=white"/>

## :wrench: 테이블 설계
![image](https://github.com/user-attachments/assets/12bb41f4-9f62-4627-953c-257d2d0184ab)

### :pushpin: 사용법
#### Backend
- src/main/recsources 디렉터리 구조 참조 및 설정 파일 생성
```bash
── resources
   ├── common [공통]
   │   └── config.properties [토큰 정보]
   ├── local [로컬]
   │   ├── config.properties [포트 및 스웨거 설정 정보]
   │   └── database.properties [DB 정보]
   └── application.properties
``` 
#### Frontend
- .env 생성 후 해당 정보 기입
```
NEXT_PUBLIC_NEXT_HOST=[HOST IP]
NEXT_PUBLIC_API_HOST=[API IP]
NEXTAUTH_URL=http:[HOST IP]/api/auth

NEXT_PUBLIC_API_URL=/api
NEXT_PUBLIC_API_VERSION=[API 버전]
```
