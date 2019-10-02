
1. Zookeeper 서버를 실행함
> zookeeper-server-start.bat ./config/zookeeper.properties

2. Kafka 서버를 실행함
> kafka-server-start.bat ./config/server.properties

3. Kafka Topic을 생성함
> kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test-topic

4. Kafka Topic 목록을 확인하여 생성되었는지 확인함
> kafka-topics.bat --list --bootstrap-server localhost:9092

5. Command Line에서 kafka consumer를 실행함
> kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test-topic --from-beginning

5-1. Consumer Group을 지정하는 consumer를 실행함
> kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test-topic --group test-group-01 --from-beginning

6. Command Line에서 kafka producer를 실행함
> kafka-console-producer.bat --broker-list localhost:9092 --topic test-topic

7. Spring kafka를 이용하여 kafka consumer 역할을 하는 Client 프로그램을 생성하고 실행함
spring boot 버전과 spring kafka 버전 간 지원 여부 확인 필요
- 초기 예제에 포함된 버전(Spring boot 2.1.5와 Spring Kafka 2.1.6)을 이용하여 테스트하니 booting시에 runtime에러 발생
- 검색해보니 Spring boot 2.1.x는 Spring Kafka 2.2.x와 호환된다고 하여 Spring Kafka 버전을 2.2.1로 변경하고 테스트하니 실행 성공
※ 검색 시 활용한 에러 메시지
java.lang.NoClassDefFoundError: org/springframework/kafka/listener/ContainerProperties$AckMode



> kafka-consumer-groups.bat --bootstrap-server localhost:9092 --list