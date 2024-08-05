package com.gabriel.pive.animals.repositories;

import com.gabriel.pive.animals.entities.Bull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BullRepository extends JpaRepository<Bull,Long> {
}
