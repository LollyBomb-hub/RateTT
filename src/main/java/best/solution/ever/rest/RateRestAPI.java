package best.solution.ever.rest;

import best.solution.ever.kafka.sender.KafkaSender;
import best.solution.ever.repositories.elastic.ESRate;
import best.solution.ever.models.RateRequestModel;
import best.solution.ever.repositories.postgres.PSRate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.camunda.zeebe.client.ZeebeClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RateRestAPI {

    private final ZeebeClient zeebeClient;
    private final KafkaSender kafkaSender;
    private final JpaRepository<PSRate, UUID> rateRepository;
    @Value("${best.solution.ever.dmn.processId}")
    private String processId;

    @PostMapping("/rate")
    public ResponseEntity<Boolean> rate(@RequestBody RateRequestModel model) {
        var capital = model.getCapital();
        if (capital == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
        var inn = model.getInn();
        if (inn == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
        final var id = UUID.randomUUID();
        var psRate = new PSRate();
        psRate.setId(id);
        psRate.setInn(inn);
        psRate.setCapital(capital);
        psRate = rateRepository.saveAndFlush(psRate);
        var processInstance =
                zeebeClient
                        .newCreateInstanceCommand()
                        .bpmnProcessId(processId)
                        .latestVersion()
                        .variables(Map.of("inn", inn, "capital", capital))
                        .withResult()
                        .send()
                        .join();

        var variables = processInstance.getVariablesAsMap();
        var rateParam = variables.get("rate");
        var parsedRate = rateParam == null ? null : Boolean.parseBoolean(rateParam.toString());
        var esRate = new ESRate();
        esRate.setId(id);
        esRate.setInn(inn);
        esRate.setCapital(capital);
        esRate.setRate(parsedRate);
        psRate.setRate(parsedRate);
        rateRepository.saveAndFlush(psRate);
        kafkaSender.sendMessage(esRate);
        return ResponseEntity.ok(psRate.getRate());
    }

}
