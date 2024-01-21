package simulation.producer.api;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import simulation.producer.managers.SimulationManager;

@Controller
public class SimulationController {

    // private final SimpMessagingTemplate messagingTemplate;

    // public SimulationController(SimpMessagingTemplate messagingTemplate) {
    //     this.messagingTemplate = messagingTemplate;
    // }

    // Handle start/stop/pause messages
    @MessageMapping("/start")
    @SendTo("/topic/updates")
    public String handleStart(String message) {
        // Process the start message (if needed)
        // SimulationManager.getInstance().start();
        return "Simulation started!";
    }

    @MessageMapping("/stop")
    @SendTo("/topic/updates")
    public String handleStop(String message) {
        // Process the stop message (if needed)
        SimulationManager.getInstance().stop();
        return "Simulation stopped!";
    }

    @MessageMapping("/pause")
    @SendTo("/topic/updates")
    public String handlePause(String message) {
        // Process the pause message (if needed)
        SimulationManager.getInstance().pause();
        return "Simulation paused!";
    }

}
