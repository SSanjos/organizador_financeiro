package br.com.g6.organizadorfinanceiro.applicationSecurity;

import br.com.g6.organizadorfinanceiro.model.User;
import br.com.g6.organizadorfinanceiro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserEmail(username);

        if (user.isPresent()) {
            return new UserDetailsImpl(user.get());
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

    }




}
