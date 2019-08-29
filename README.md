# 모임 문화 플랫폼 tips

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
* [참고](https://elfinlas.github.io/2018/02/16/thymeleaf-layout-dialect_exam/)
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
