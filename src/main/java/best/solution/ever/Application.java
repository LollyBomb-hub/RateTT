package best.solution.ever;

//import io.camunda.zeebe.spring.client.EnableZeebeClient;

import io.camunda.zeebe.spring.client.annotation.Deployment;
import io.camunda.zeebe.spring.client.annotation.ZeebeDeployment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// DEPRECATED
// https://forum.camunda.io/t/enablezeebeclient-is-reporting-deprecated-how-to-implement-the-zeebe-client/46120
//@EnableZeebeClient

@Deployment(resources = {"classpath*:*.dmn", "classpath*:*.bpmn"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
