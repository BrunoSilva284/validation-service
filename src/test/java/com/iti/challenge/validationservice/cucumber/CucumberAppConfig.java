package com.iti.challenge.validationservice.cucumber;

import com.iti.challenge.validationservice.model.ValidateInput;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Ignore
public class CucumberAppConfig {

    private final RestTemplate restTemplate;

    @LocalServerPort
    protected int port;

    public CucumberAppConfig(){
        this.restTemplate = new RestTemplate();
    }

    private String getEndpoint(){
        return "http://localhost:" + port ;
    }

    protected ResponseEntity<Boolean> postValidar(ValidateInput input){
        HttpEntity<ValidateInput> httpInput = new HttpEntity<>(input);

        return restTemplate.exchange(getEndpoint() + "/validate", HttpMethod.POST, httpInput, Boolean.class);
    }

    protected String getStatusUp(){
        return restTemplate.getForObject(getEndpoint() + "/", String.class);
    }
}
