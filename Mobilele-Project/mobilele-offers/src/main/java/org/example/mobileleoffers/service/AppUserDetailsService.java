package org.example.mobileleoffers.service;


import org.example.mobileleoffers.model.user.MobileleUserDetails;
import org.example.mobileleoffers.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.userRepository.findByEmail(email)
                .map(MobileleUserDetails::mapToMobileleUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("User with name " + email + " not found!"));

    }

}
