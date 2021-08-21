package com.template.webserver.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public User login(User user) {
        Optional<User> result=userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if(result.isPresent()){
            result.get().setPassword(null);
            return result.get();
        }
        return null;
    }

}
