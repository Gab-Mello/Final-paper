package com.gabriel.pive.fiv.cultivation.repositories;

import com.gabriel.pive.fiv.cultivation.entities.Embryo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmbryoRepository extends JpaRepository<Embryo,Long> {
}
