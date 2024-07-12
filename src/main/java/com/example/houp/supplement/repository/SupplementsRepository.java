package com.example.houp.supplement.repository;

import com.example.houp.supplement.Supplements;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplementsRepository extends JpaRepository<Supplements, Long> {
}