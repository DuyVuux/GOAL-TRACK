package org.example.goaltrack.respository;

import org.example.goaltrack.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
