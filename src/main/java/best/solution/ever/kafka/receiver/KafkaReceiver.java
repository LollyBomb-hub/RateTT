package best.solution.ever.kafka.receiver;

import best.solution.ever.repositories.elastic.ESRate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaReceiver {

    private final ElasticsearchOperations elasticsearchOperations;

    @KafkaListener(topics = "${best.solution.ever.kafka-topic}", containerFactory = "kafkaListenerFactory", groupId = "${spring.kafka.consumer.group-id}")
    public void receive(ESRate rate) {
        log.debug("Saving rate({}) to es repository", rate.getId());
        elasticsearchOperations.save(rate);
        log.debug("Saved rate({}) to es repository", rate.getId());
    }

}
