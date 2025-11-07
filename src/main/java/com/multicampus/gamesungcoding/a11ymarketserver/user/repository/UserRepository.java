package com.multicampus.gamesungcoding.a11ymarketserver.user.repository;

import com.multicampus.gamesungcoding.a11ymarketserver.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
