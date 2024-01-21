package simulation.producer.api;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import simulation.producer.managers.SimulationManager;
import simulation.producer.models.ResponseObject;

@Component
public class SimulationUpdater {

    private final SimpMessagingTemplate messagingTemplate;

    public SimulationUpdater(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Scheduled(fixedRate = 1000)
    public void sendSimulationUpdates() {
        messagingTemplate.convertAndSend("/topic/updates", new ResponseObject(SimulationManager.getInstance().getMachines(), SimulationManager.getInstance().getQueues()));
        System.out.println(SimulationManager.getInstance().getMachines());
    }
}
