package org.kenuki.smartparking.services;

import lombok.AllArgsConstructor;
import org.kenuki.smartparking.models.enities.User;
import org.kenuki.smartparking.repositories.UserRepository;
import org.kenuki.smartparking.utils.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String nicknameOrEmail) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByNickname(nicknameOrEmail);
        if (user.isEmpty())
            user = userRepository.findByEmail(nicknameOrEmail);

        return user.map(CustomUserDetails::new)
                   .orElseThrow(() -> new UsernameNotFoundException("[" + nicknameOrEmail + "] not found!"));
    }
}
