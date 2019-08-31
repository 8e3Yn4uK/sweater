package com.ve3yn4uk.sweater.repos;

import com.ve3yn4uk.sweater.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
