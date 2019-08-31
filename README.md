# 모임 문화 플랫폼 together

## 서버 사이드 템플릿 엔진 Thymeleaf
* [참고](https://araikuma.tistory.com/30)
* 의존성 추가

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

### Spring Boot에서의 불편함
* Spring과 달리 Spring Boot는 front-end는 resources 디렉토리에서 관리하기 때문에 수정이 있는 경우 서버를 재시작해야하는 불편함이 있다.
* devtools 라이브러리를 활용하자.
* 의존성 추가

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-devtools</artifactId>
</dependency>
```

* application.properties 혹은 application.yml에 다음과 같은 설정을 추가한다.

```
spring:
  profiles: local
  devtools:
    livereload:
      enabled: true
  thymeleaf:
    cache: false # thymeleaf 템플릿 엔진 캐시 비활성화
```

### Thymeleaf로 화면 구성하기(layout)
* [Thymeleaf Page Layouts 참고](https://www.thymeleaf.org/doc/articles/layouts.html)
* [블로그 참고](https://elfinlas.github.io/2018/02/16/thymeleaf-layout-dialect_exam/)
* Thymeleaf에서 레이아웃과 템플릿을 작성할 수 있도록 도와주는 dialect를 추가한다.

```xml
<dependency>
  <groupId>nz.net.ultraq.thymeleaf</groupId>
  <artifactId>thymeleaf-layout-dialect</artifactId>
</dependency>
```

* Tiles의 경우 새로운 페이지를 개발 할 때 마다 xml에 설정을 잡아 줘야 했었다.
* Thymeleaf은 html 안에서 모든걸 해결한다.
* 화면 구성을 하면서 별도 xml에 선언을 해주거나 설정을 잡을 필요가 없다.
* 단점은 사용하는 페이지에 명시적으로 layout을 설정해 줘야 한다.


## 로컬 Spring Boot에 SSL 적용
* [HTTPS 설정하기 참고](https://github.com/cheese10yun/spring-security-oauth2-social/blob/master/doc/step-00.md)
* [로컬 Spring Boot에서 SSL 적용하기 참고](https://jojoldu.tistory.com/350)
* 페이스북에서는 https만 리다이렉트 URL 설정이 가능하기 때문에 로컬 환경에서도 SSL 인증서를 적용해야 한다.
* Spring Boot 로컬 환경에서 SSL 인증서(https)를 적용해보자.
* 프로젝트 디렉토리 안에 다음과 같은 명령어를 실행한다.

```
keytool -genkey -alias bns-ssl -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 3650
```

* `-alias bns-ssl` : key alias를 bns-ssl로 지정한다.
* `-keystore keystore.p12` : key store 이름을 keystore.p12로 지정한다.

![keystore 생성](/md_images/image1.png)
* 위와 같이 값을 입력 후 생성이 끝나면 keystore 파일이 프로젝트 디렉토리 안에 생성된다.
* application.yml 혹은 application.properties에 다음과 같이 설정한다.

```
---
# local 환경
spring:
  profiles: local
  
server:
  ssl:
    enabled: true
    key-store: keystore.p12 # 프로젝트 디렉토리 내부에 있기 때문에 절대 경로를 지정하지 않음
    key-store-password: <비번>
    key-store-type: PKCS12
    key-alias: bns-ssl
  port: 8081
```

* 설정 후 아래와 같이 https로 요청하고 인증서라는 경고문은 아래와 같이 처리하면 인증서가 적용된 사이트를 볼 수 있다.

```
https://localhost:8081
```

![경고문1](/md_images/image2.png)
![경고문2](/md_images/image3.png)
