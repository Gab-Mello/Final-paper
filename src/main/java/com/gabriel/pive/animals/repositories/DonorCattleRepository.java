package com.gabriel.pive.animals.repositories;

import com.gabriel.pive.animals.entities.DonorCattle;
import com.gabriel.pive.animals.entities.ReceiverCattle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonorCattleRepository extends JpaRepository<DonorCattle,Long> {
    List<DonorCattle> findByRegistrationNumber(String registrationNumber);
}
