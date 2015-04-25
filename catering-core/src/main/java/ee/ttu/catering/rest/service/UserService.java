package ee.ttu.catering.rest.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ee.ttu.catering.rest.model.User;
import ee.ttu.catering.rest.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private static final String USER_NOT_FOUND = "User not found";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username);
        if (user != null) {
        	org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(user.geName(), user.getPassword(), Collections.EMPTY_LIST);
            return userDetails;
        }
        throw new UsernameNotFoundException(USER_NOT_FOUND);
    }

}
