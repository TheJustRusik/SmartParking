package org.kenuki.smartparking.services;

import lombok.AllArgsConstructor;
import org.kenuki.smartparking.models.dtos.LoginDTO;
import org.kenuki.smartparking.models.dtos.RegisterDTO;
import org.kenuki.smartparking.models.dtos.TokenDTO;
import org.kenuki.smartparking.models.enities.User;
import org.kenuki.smartparking.repositories.RoleRepository;
import org.kenuki.smartparking.repositories.UserRepository;
import org.kenuki.smartparking.utils.JwtTokenUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtTokenUtils jwtTokenUtils;
    private final PasswordEncoder passwordEncoder;
    public TokenDTO register(RegisterDTO registerDTO) {
        User newUser = new User();
        newUser.setNickname(registerDTO.getUsername());
        newUser.setEmail(registerDTO.getEmail());
        newUser.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        newUser.setRoles(Set.of(roleRepository.findById(1L).orElseThrow()));
        userRepository.save(newUser);
        return login(registerDTO.getEmail(), registerDTO.getPassword());

    }
    public TokenDTO login(LoginDTO loginDTO) {
        return login(loginDTO.getEmail(), loginDTO.getPassword());
    }
    private TokenDTO login(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new TokenDTO(jwtTokenUtils.generateToken(authentication));
    }

}
