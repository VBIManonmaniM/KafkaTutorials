package com.learning.kafkagettingstarted.chapter5;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Random;

public class KafkaSimpleProducer {
    public static void main(String args[]) {
        Properties props = new Properties();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");

        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");


        KafkaProducer kafkaProducer = new KafkaProducer(props);

        try {
            int startKey = (new Random()).nextInt(1000) ;
            for (int key = startKey; key <= startKey+10; key++) {
                ProducerRecord rec = new ProducerRecord("kafka.usecases.students", String.valueOf(key), "This is student" + key);
                System.out.println("Sending Message : "+ rec.toString());
                kafkaProducer.send(rec);
                Thread.sleep(2000);
            }

        } catch (Exception e) {

        } finally {
            kafkaProducer.close();
        }

    }
}