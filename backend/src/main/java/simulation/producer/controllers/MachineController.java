package simulation.producer.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class MachineController {
    
    @PostMapping("/machine")
    public String addMachine(@RequestBody String machine) {
        return machine;
    }

    @PostMapping("/machine/connect")
    public String connectMAchineToQueue(@RequestBody String machine) {
        return machine;
    }

    // @PostMapping("/machine/remove")
    // public String removeMachine(@RequestBody String machine) {
    //     return machine;
    // }
}
