spring boot 1.3.1.RELEASE
lombok (설치 및 dependency 추가 해야 함) 
H2 database



## @SpringBootApplication 
	@Configuration + @EnableAutoConfiguration +  @ComponentScan

	@Configuration tags the class as a source of bean definitions for the application context.
	@EnableAutoConfiguration tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
		Normally you would add @EnableWebMvc for a Spring MVC app, but Spring Boot adds it automatically when it sees spring-webmvc on the classpath. This flags the application as a web application and activates key behaviors such as setting up a DispatcherServlet.
	@ComponentScan tells Spring to look for other components, configurations, and services in the the hello package, allowing it to find the HelloController.
	

## SPRING INITIALIZR
	http://start.spring.io 해당 URL에서 템플릿 프로젝트 다운로드 
	mvn spring-boot:run :컴파일 및 run
	mvn package : /target/xxx-SNAPSHOT.jar 파일 생성
	java -jar xxx-SNAPSHOT.jar
	
## CommandLineRunner
	context.getBean으로 할 필요가 없다.
	annotation으로만 설정한다. 
	
## 스프링 부트는 자동설정(auto configure)이라는 기능을 통해 Datasource, JdbcTemplate, NamedParameterJdbcTemplate을 자동으로 생성하여 DI 컨테이너에 등록합니다.
	com.astraltear.database.H2ConTest
	datasource, NamedParamterJdbcTemplate에 대해서 의존성 주입을 명시적으로 하지 않았음에도 불구하고 작동한다.
	