package application.rabbitmq;

import application.rabbitmq.dto.TaskMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class RabbitConsumer {

    private final SimpMessagingTemplate template;

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consume(TaskMessage message){
        log.info(String.format("Got the message %s", message.toString()));
//        log.info("Got the message");
//        template.convertAndSendToUser(message.getReceiverUsername(), "/queue/messages", message.getContent());
    }
}
