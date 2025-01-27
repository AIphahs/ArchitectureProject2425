package efrei.propertyStake.controllers;

import efrei.propertyStake.models.Agent;
import efrei.propertyStake.services.AgentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/agents")
public class AgentController {

    private final AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @PostMapping
    public Agent createAgent(@RequestBody Agent agent) {
        return agentService.createAgent(agent);
    }

    @GetMapping
    public List<Agent> getAllAgents() {
        return agentService.getAllAgents();
    }

    @GetMapping("/{id}")
    public Agent getAgentById(@PathVariable UUID id) {
        return agentService.getAgentById(id);
    }

    @PutMapping("/{id}")
    public Agent updateAgent(@PathVariable UUID id, @RequestBody Agent updatedAgent) {
        return agentService.updateAgent(id, updatedAgent);
    }

    @DeleteMapping("/{id}")
    public boolean deleteAgent(@PathVariable UUID id) {
        return agentService.deleteAgent(id);
    }
}
