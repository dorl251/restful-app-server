## 백 엔드 App Server 연습 프로젝트

기존에 공부하던 Spring boot 를 젠킨스로 CI/CD를 구축한 프로젝트입니다. AWS를 활용했습니다.

![image-20201229201702453](README.assets/image-20201229201702453.png)

<br/>

스프링 부트 구축 내용 : https://jeonghoon.netlify.app/?category=Spring

젠킨스 구축 내용 : https://jeonghoon.netlify.app/?category=Jenkins

## 기능

### Spring boot

- 기본적인 CRUD
- HTTP 상태코드 제어
- 유효성 체크
- 다국어 처리
- Response 데이터 형식 변환
- Response 데이터 제어 필터링
- Rest API 버전 관리
- Spring Security 인증처리

<br/>

### Jenkins

- **빌드 자동화**
  - AWS CodeDeploy 연동
  - Github 연동
- **테스트 자동화**
- Email-ext-plugin으로 **이메일로 통합 결과 공유**
- Disk Usage Plugin으로 **디스크 용량**  **모니터링**
- Dependency Graph View Plugin으로 **의존성 그래프 보기**

