package best.solution.ever.kafka.sender;

import best.solution.ever.repositories.elastic.ESRate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaSender {

    @Value("${best.solution.ever.kafka-topic}")
    private String topic;

    private final KafkaTemplate<String, ESRate> kafkaTemplate;

    public void sendMessage(ESRate rate) {
        kafkaTemplate.send(topic, rate);
    }

}
