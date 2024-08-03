package application.rabbitmq;

import application.dto.message.MessageDto;
import application.rabbitmq.dto.TaskMessage;
import application.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class RabbitConsumer {

    private final SimpMessagingTemplate template;
    private final MessageService messageService;

    @SneakyThrows
    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consume(TaskMessage message){
        log.info(String.format("Got the message %s", message.toString()));
        MessageDto messageDto = messageService.saveRabbitSystemMessage(message);
        template.convertAndSend("/topic/messages", messageDto);
//        log.info("Got the message");
//        template.convertAndSendToUser(message.getReceiverUsername(), "/queue/messages", message.getContent());
    }
}
