package XindusTrade.Assignment.Services;

import XindusTrade.Assignment.Entities.User;
import XindusTrade.Assignment.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public String add(User user) {
        userRepository.save(user);
        return "User added successfully!!";
    }
}
