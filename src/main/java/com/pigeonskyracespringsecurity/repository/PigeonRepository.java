package com.pigeonskyracespringsecurity.repository;

import com.pigeonskyracespringsecurity.model.entity.Pigeon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PigeonRepository extends JpaRepository<Pigeon, Long> {
}
