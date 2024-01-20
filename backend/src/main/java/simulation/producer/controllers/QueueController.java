package simulation.producer.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class QueueController {
    @PostMapping("/addQueue")
    public String addQueue(@RequestBody String queue) {
        return "Queue added";
    }

}
