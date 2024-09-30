package com.bidkoi.koiauction.repository;

import com.bidkoi.koiauction.pojo.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<Account, String> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<Account> findByUsername(String username);
    Optional<Account> findByEmail(String email);
}
