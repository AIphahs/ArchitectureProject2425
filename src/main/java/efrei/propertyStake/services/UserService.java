package efrei.propertyStake.services;

import efrei.propertyStake.models.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private final Map<UUID, User> users = new HashMap<>();

    public User createUser(User user) {
        user.setId(UUID.randomUUID());
        users.put(user.getId(), user);
        return user;
    }

    public List<User> listUsers() {
        return new ArrayList<>(users.values());
    }

    public User getUser(UUID id) {
        return users.get(id);
    }

    public void deleteUser(UUID id) {
        users.remove(id);
    }
}
