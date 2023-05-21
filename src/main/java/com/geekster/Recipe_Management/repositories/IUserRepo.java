package com.geekster.Recipe_Management.repositories;

import com.geekster.Recipe_Management.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User,Long> {

    User findByUserEmail(String userEmail);
}
