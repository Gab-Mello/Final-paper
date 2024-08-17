package com.gabriel.pive.fiv.repositories;

import com.gabriel.pive.fiv.entities.Fiv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FivRepository extends JpaRepository<Fiv,Long> {
}
