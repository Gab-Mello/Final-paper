package com.gabriel.pive.animals.repositories;

import com.gabriel.pive.animals.entities.ReceiverCattle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiverCattleRepository extends JpaRepository<ReceiverCattle,Long> {
    ReceiverCattle findByRegistrationNumber(String registrationNumber);

    List<ReceiverCattle> findByEmbryoIsNull();
}
