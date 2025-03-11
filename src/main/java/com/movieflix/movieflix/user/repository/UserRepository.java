package com.movieflix.movieflix.user.repository;

import com.movieflix.movieflix.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<UserDetails> findUserByEmail(String email);
}
