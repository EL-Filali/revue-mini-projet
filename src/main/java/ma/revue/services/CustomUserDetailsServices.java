package ma.revue.services;


import ma.revue.beans.User;
import ma.revue.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CustomUserDetailsServices implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user==null) new UsernameNotFoundException("Utilisateur non trouve");
        return user;
    }


    @Transactional
    public User loadUserById(Long id){
        Optional<User> user =  userRepository.findById(id);
        if(!user.isPresent()) new UsernameNotFoundException("User not found");
        return user.get();

    }
}
