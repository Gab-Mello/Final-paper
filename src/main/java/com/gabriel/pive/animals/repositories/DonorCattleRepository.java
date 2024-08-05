package com.gabriel.pive.animals.repositories;

import com.gabriel.pive.animals.entities.DonorCattle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonorCattleRepository extends JpaRepository<DonorCattle,Long> {
}
