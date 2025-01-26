package efrei.propertyStake.services;

import efrei.propertyStake.models.Agent;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AgentService {
    private final Map<UUID, Agent> agents = new HashMap<>();

    public Agent addAgent(Agent agent) {
        agent.setId(UUID.randomUUID());
        agents.put(agent.getId(), agent);
        return agent;
    }

    public List<Agent> listAgents() {
        return new ArrayList<>(agents.values());
    }

    public Agent getAgent(UUID id) {
        return agents.get(id);
    }

    public void removeAgent(UUID id) {
        agents.remove(id);
    }
}
