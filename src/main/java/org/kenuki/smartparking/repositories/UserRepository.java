package org.kenuki.smartparking.repositories;

import org.kenuki.smartparking.models.enities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNickname(String nickname);
    Optional<User> findByEmail(String email);
    Optional<User> findByNicknameOrEmail(String nick, String email);

}
