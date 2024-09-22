package com.gabriel.pive.animals.repositories;

import com.gabriel.pive.animals.entities.DonorCattle;
import com.gabriel.pive.animals.entities.ReceiverCattle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonorCattleRepository extends JpaRepository<DonorCattle,Long> {
    DonorCattle findByRegistrationNumber(String registrationNumber);
    List<DonorCattle> findByRegistrationNumberStartingWith(String registrationNumber);

    @Query("SELECT d FROM DonorCattle d WHERE d.id NOT IN " +
            "(SELECT o.donorCattle.id FROM OocyteCollection o WHERE o.fiv.id = :fivId) " +
            "OR d.id NOT IN " +
            "(SELECT o.donorCattle.id FROM OocyteCollection o)")
    List<DonorCattle> findDonorsNotUsedInFiv(@Param("fivId") Long fivId);


    @Query("SELECT d FROM DonorCattle d " +
            "ORDER BY d.averageViableOocytes DESC")
    List<DonorCattle> findDonorsWithHighestOocytesCollected();

    @Query("SELECT d FROM DonorCattle d " +
            "ORDER BY d.averageEmbryoPercentage DESC")
    List<DonorCattle> findDonorsWithHighestEmbryoPercentage();
}
