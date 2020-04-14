package config;


import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Properties;



public class BoardKafkaConsumer {

    private final static String TOPIC = "test";
    private final static String BOOTSTRAP_SERVERS = "localhost:9092";

    private static Consumer<Long, String> createConsumer() {
        final Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                BOOTSTRAP_SERVERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG,
                "BoardKafkaConsumer");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                LongDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getName());
        // Create the consumer using props.
        final Consumer<Long, String> consumer =
                new KafkaConsumer<>(props);
        // Subscribe to the topic.
        consumer.subscribe(Collections.singletonList(TOPIC));
        return consumer;
    }


    public ConsumerRecords<Long, String> runConsumerAndGetMsg() {
        final Consumer<Long, String> consumer = createConsumer();
        final int giveUp = 100;
        int noRecordsCount = 0;

        final ConsumerRecords<Long, String> consumerRecords =
                consumer.poll(1000);

        consumerRecords.forEach(record -> {
            System.out.printf("Consumer Record:(%s, %d, %d)\n",
                    record.value(),
                    record.partition(), record.offset());
        });
        consumer.commitAsync();
        consumer.close();
        return consumerRecords;

//        while (true) {
//        final ConsumerRecords<Long, String> consumerRecords =
//                consumer.poll(1000);
//            if (consumerRecords.count()==0) {
//                noRecordsCount++;
//                if (noRecordsCount > giveUp) break;
//                else continue;
//            }
//        consumerRecords.forEach(record -> {
//            System.out.printf("Consumer Record:(%s, %d, %d)\n",
//                    record.value(),
//                    record.partition(), record.offset());
//        });
//        consumer.commitAsync();
//        return consumerRecords;
//        }
//        consumer.close();
//        System.out.println("DONE");
//        return null;
    }

//
//    public static void main(String... args) throws Exception {
//        BoardKafkaConsumer boardKafkaConsumer = new BoardKafkaConsumer();
//        boardKafkaConsumer.runConsumerAndGetMsg();
//    }


}
