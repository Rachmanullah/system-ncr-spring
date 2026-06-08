package com.example.ncrsystem.ncrsystem.repository;

import com.example.ncrsystem.ncrsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<User, BigInteger> {
    @Query("""
        SELECT user
        from User user
        where UPPER(user.username) = UPPER(:username)
        and user.deleted = 0
        and user.status = 0
    """)
    Optional<User> findByUsername(@Param("username") String username);
}
