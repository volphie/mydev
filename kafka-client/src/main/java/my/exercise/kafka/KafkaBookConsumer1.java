package my.exercise.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class KafkaBookConsumer1 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
		Properties props = new Properties();
		props.put("bootstrap.servers","localhost:9092");
		props.put("group.id", "test-group-01");
		props.put("enable.auto.commit", "true");
		props.put("auto.offset.reset","latest");
		props.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
		
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Arrays.asList("test-topic"));
		
		try{
			while(true){
				ConsumerRecords<String, String> records = consumer.poll(100);
				for (ConsumerRecord<String, String> record : records)
					System.out.printf("Topic: %s, Partition: %s, Offset:%d, key: %s, Value: %s\n", record.topic(), record.partition(), record.offset(), record.key(), record.value());
			}
		} finally{
			consumer.close();
		}
			
    }
}
