package com.geekster.Recipe_Management.repositories;

import com.geekster.Recipe_Management.models.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthenticationRepo extends JpaRepository<AuthenticationToken,Long> {

    AuthenticationToken findByToken(String token);
}
