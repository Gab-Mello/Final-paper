package com.gabriel.pive.animals.repositories;

import com.gabriel.pive.animals.entities.Bull;
import com.gabriel.pive.animals.entities.ReceiverCattle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BullRepository extends JpaRepository<Bull,Long> {
    Bull findByRegistrationNumber(String registrationNumber);

    List<Bull> findByRegistrationNumberStartingWith(String registrationNumber);

    @Query("SELECT t FROM Bull t " +
            "ORDER BY t.averageEmbryoPercentage DESC")
    List<Bull> findBullsWithHighestEmbryoPercentage();
}
