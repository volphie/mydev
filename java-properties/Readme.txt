
1. src/main/resources 아래에 properties 디렉토리를 만들고 그 아래 config.properties 파일을 만듦
2. config.properties 파일 생성 시 UTF-8로 저장
3. Property 파일을 읽을 때, classpath의 상대경로 properties/config.properties 파일을 읽도록 프로그램 함
4. 실행 시 System Property file.encoding 값을 UTF-8로 설정하도록 JVM -D option을 설정
  > java -classpath java-properties-1.0-SNAPSHOT.jar -Dfile.encoding=UTF-8 my.exercise.java.properties.JavaPropertiesEncoding