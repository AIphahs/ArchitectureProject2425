package efrei.propertyStake.services;

import efrei.propertyStake.models.Agent;
import efrei.propertyStake.repository.AgentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AgentService {

    private final AgentRepository agentRepository;

    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    public Agent createAgent(Agent agent) {
        return agentRepository.save(agent);
    }

    public List<Agent> getAllAgents() {
        return agentRepository.findAll();
    }

    public Agent getAgentById(UUID id) {
        return agentRepository.findById(id).orElse(null);
    }

    public Agent updateAgent(UUID id, Agent updatedAgent) {
        Optional<Agent> optAgent = agentRepository.findById(id);
        if (optAgent.isPresent()) {
            Agent existing = optAgent.get();
            existing.setFirstname(updatedAgent.getFirstname());
            existing.setLastname(updatedAgent.getLastname());
            existing.setEmail(updatedAgent.getEmail());
            existing.setPassword(updatedAgent.getPassword());
            existing.setUserType(updatedAgent.getUserType());
            existing.setAgencyName(updatedAgent.getAgencyName());
            return agentRepository.save(existing);
        }
        return null;
    }

    public boolean deleteAgent(UUID id) {
        if (agentRepository.existsById(id)) {
            agentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
