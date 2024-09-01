package com.gabriel.pive.fiv.pregnancy.repositories;

import com.gabriel.pive.fiv.pregnancy.entities.Pregnancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PregnancyRepository extends JpaRepository<Pregnancy, Long> {
}
