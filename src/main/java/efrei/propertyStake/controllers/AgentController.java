package efrei.propertyStake.controllers;

import efrei.propertyStake.models.Agent;
import efrei.propertyStake.services.AgentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/agents")
public class AgentController {
    private final AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @PostMapping
    public Agent addAgent(@RequestBody Agent agent) {
        return agentService.addAgent(agent);
    }

    @GetMapping
    public List<Agent> listAgents() {
        return agentService.listAgents();
    }

    @GetMapping("/{id}")
    public Agent getAgent(@PathVariable UUID id) {
        return agentService.getAgent(id);
    }

    @DeleteMapping("/{id}")
    public void removeAgent(@PathVariable UUID id) {
        agentService.removeAgent(id);
    }
}
