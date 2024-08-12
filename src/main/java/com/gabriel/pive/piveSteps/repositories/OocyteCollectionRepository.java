package com.gabriel.pive.piveSteps.repositories;

import com.gabriel.pive.animals.entities.DonorCattle;
import com.gabriel.pive.piveSteps.entities.OocyteCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OocyteCollectionRepository extends JpaRepository<Long, OocyteCollection> {
}
