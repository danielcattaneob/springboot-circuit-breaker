package ar.edu.utn.frc.tup.lciii.clients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class MicroBRestClient {

    private static final String RESILIENCE4J_INSTANCE_NAME = "microCircuitBreakerB";
    private static final String FALLBACK_METHOD = "fallback";

    String baseResourceUrl = "http://localhost:8081/micro";

    private Integer counter = 0;

    @Autowired
    private RestTemplate restTemplate;

    @CircuitBreaker(name = RESILIENCE4J_INSTANCE_NAME, fallbackMethod = FALLBACK_METHOD)
    public ResponseEntity<String> getMicro() {
        counter++;
        log.info("Execution N° " + counter + " - Calling micro B");
        return restTemplate.getForEntity(baseResourceUrl, String.class);
    }

    public ResponseEntity<String> fallback(Exception ex) {
        counter++;
        log.warn("Execution N° {} - FallBack B - Error message: {}", counter, ex.getMessage());
        return ResponseEntity.status(503).body("Response from Circuit Breaker Fallback of Micro B");
    }
}
