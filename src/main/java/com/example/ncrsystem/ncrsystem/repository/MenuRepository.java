package com.example.ncrsystem.ncrsystem.repository;

import com.example.ncrsystem.ncrsystem.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, BigInteger> {
    @Query("""
        SELECT u
        FROM Menu u
        WHERE u.deleted = 0
    """)
    List<Menu> findAllMenu();
}
