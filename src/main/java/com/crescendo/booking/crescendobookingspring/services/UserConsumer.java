package com.crescendo.booking.crescendobookingspring.services;

import com.crescendo.booking.crescendobookingspring.common.Constant;
import com.crescendo.booking.crescendobookingspring.data.dtos.UserProjection;
import com.crescendo.booking.crescendobookingspring.data.repositories.UserRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserConsumer {

    private static final Logger logger = LoggerFactory.getLogger(UserConsumer.class);

    @Autowired
    UserRepository userRepository;

    @KafkaListener(topics = Constant.TOPIC, groupId = "crescendobooking")
    public void listen(ConsumerRecord<String, UserProjection> record) {
        UserProjection user = record.value();
        userRepository.save(
                new com.crescendo.booking.crescendobookingspring.data.entities.User(
                        user.getId(),
                        user.getEmail(),
                        user.getRole()
                )
        );
        logger.info(String.format("Consumed event from topic %s: key = %s", Constant.TOPIC, record.key()));
    }
}
