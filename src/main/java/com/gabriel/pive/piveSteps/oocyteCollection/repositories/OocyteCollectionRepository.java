package com.gabriel.pive.piveSteps.oocyteCollection.repositories;

import com.gabriel.pive.piveSteps.oocyteCollection.entities.OocyteCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OocyteCollectionRepository extends JpaRepository<OocyteCollection, Long> {
}
