package efrei.propertyStake.services;

import efrei.propertyStake.models.Investment;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class InvestmentService {
    private final Map<UUID, Investment> investments = new HashMap<>();

    public Investment addInvestment(Investment investment) {
        investment.setId(UUID.randomUUID());
        investments.put(investment.getId(), investment);
        return investment;
    }

    public List<Investment> listInvestmentsByUser(UUID userId) {
        return investments.values().stream()
                .filter(investment -> investment.getId().equals(userId))
                .collect(Collectors.toList());
    }

    public Investment getInvestment(UUID id) {
        return investments.get(id);
    }

    public List<Investment> getInvestmentsByProperty(UUID propertyId) {
        return investments.values().stream()
                .filter(investment -> investment.getPropertyId().equals(propertyId))
                .collect(Collectors.toList());
    }
}
