
-- SSL 통신을 위한 사설 인증서(key 파일) 만들기
keytool -genkey -alias spring -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 4000

-- Spring boot 어플리케이션이 실행 시 인증서 파일을 찾을 수 있도록 src/main/resource 아래 복사

-- Spring boot 환경 설정(SSL 및 HTTP2) : application.properties
# 환경 변수의 각 값들은 인증서 생성시 사용한 값들과 일치해야 함
server.ssl.key-store=classpath:keystore.p12
server.ssl.key-store-type=PKCS12
server.ssl.key-store-password=springhttp2
server.ssl.key-alias=spring
server.port=8443
server.http2.enabled=true

-- Java8 환경에서 embedded tomcat을 이용하여 HTTP2 통신을 하기 위한 Option을 포함한 java 실행문
java -Djava.library.path=C:\tomcat\tomcat-native-1.2.21\bin\x64 -jar ./target/springboothttp2-0.1-SNAPSHOT.jar

-- Chrome Browser에서 주소입력하여 Test
https://localhost:8443/hello
# Chrome 개발자 도구 화면의 Network 화면의 resource 테이블 헤더에 프로토콜을 선택하여 보이도록 한 상태에서 요청 실행
# 프로토콜 필드에서 h2 표시되는 것 확인. 반면에 http://localhost:8070/hello로 요청하면 HTTP/1.1로 조회됨

-- curl을 이용한 Command Line Test
# Windows 기본 설치된 curl은 HTTP2를 지원하지 않음