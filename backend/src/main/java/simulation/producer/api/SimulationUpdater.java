package simulation.producer.api;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import simulation.producer.managers.SimulationManager;

@Component
public class SimulationUpdater {

    private final SimpMessagingTemplate messagingTemplate;

    public SimulationUpdater(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Scheduled(fixedRate = 500)
    public void sendSimulationUpdates() {
        messagingTemplate.convertAndSend("/topic/updates", "skksksk");
        messagingTemplate.convertAndSend("/topic/updates", "skksksk");
        System.out.println(SimulationManager.getInstance().getMachines());
    }
}
