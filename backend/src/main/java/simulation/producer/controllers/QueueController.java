package simulation.producer.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class QueueController {
        
        @PostMapping("/queue")
        public String addQueue(@RequestBody String queue) {
            return queue;
        }
    
        @PostMapping("/queue/connect")
        public String connectQueueToMachine(@RequestBody String queue) {
            return queue;
        }
    
        // @PostMapping("/queue/remove")
        // public String removeQueue(@RequestBody String queue) {
        //     return queue;
        // }
}
