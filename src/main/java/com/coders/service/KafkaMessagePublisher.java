package com.coders.service;

import com.coders.avro.User;
import com.coders.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
@Service
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String,Object> template;

    public void sendMessageToTopic(String message){
        CompletableFuture<SendResult<String, Object>> future = template.send("rishi-topic-8", message);
        future.whenComplete((result,ex)->{
            if(ex==null){
                System.out.println("Sent message: "+message+" , with offset: "+result.getRecordMetadata().offset());
            }else{
                System.out.println("Failed to send message: "+message+" , with offset: "+result.getRecordMetadata().offset());
            }

        });
    }

    public void sendEventsToTopic(Customer customer){
        CompletableFuture<SendResult<String, Object>> future = template.send("rishi-topic-8", customer);
        future.whenComplete((result,ex)->{
            if(ex==null){
                System.out.println("Sent message: "+customer.toString()+" , with offset: "+result.getRecordMetadata().offset());
            }else{
                System.out.println("Failed to send message: "+customer.toString()+" , with offset: "+result.getRecordMetadata().offset());
            }

        });

    }


    public void sendUser(User user) {
        CompletableFuture<SendResult<String, Object>> future = template.send("rishi-topic-8", user);
        future.whenComplete((result,ex)->{
            if(ex==null){
                System.out.println("Sent message: "+user.toString()+" , with offset: "+result.getRecordMetadata().offset());
            }else{
                System.out.println("Failed to send message: "+user.toString()+" , with offset: "+result.getRecordMetadata().offset());
            }

        });
    }
}
