package ar.edu.utn.frc.tup.lciii.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PingController {

    private Integer counter = 0;

    @GetMapping("/ping")
    public String pong() {
        return "pong from micro b";
    }

    @GetMapping("/micro")
    public String micro() {
        counter++;
        if(counter > 10 && counter < 30) {
            log.info("Call N° " + counter + " - Error en Micro B");
            throw new RuntimeException("Error");
        }
        log.info("Call N° " + counter + " - OK en Micro B");
        return "pong from micro b - counter = " + counter;
    }
}
