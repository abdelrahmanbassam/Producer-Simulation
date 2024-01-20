package simulation.producer.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class MachineController {
    // implement machine controller here
    @PostMapping("/addMachine")
    public String addMachine(@RequestBody String machine) {
        return "Machine added";
    }
    
    
}
