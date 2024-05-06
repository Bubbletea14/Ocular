package com.github.bubbletea14.ocular.ocular.tables;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
    Users findByUsername(String username);
    //Users findbyEmail(String email);
}
