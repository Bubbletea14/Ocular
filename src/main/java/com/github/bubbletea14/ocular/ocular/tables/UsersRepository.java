package com.github.bubbletea14.ocular.ocular.tables;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Long> {
    Users findByusername(String username);
}
