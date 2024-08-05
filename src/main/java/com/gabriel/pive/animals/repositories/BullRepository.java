package com.gabriel.pive.animals.repositories;

import com.gabriel.pive.animals.entities.Bull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BullRepository extends JpaRepository<Bull,Long> {
}
