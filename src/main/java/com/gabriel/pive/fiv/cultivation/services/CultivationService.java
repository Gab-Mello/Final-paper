package com.gabriel.pive.fiv.cultivation.services;

import com.gabriel.pive.fiv.cultivation.repositories.CultivationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CultivationService {

    @Autowired
    private CultivationRepository cultivationRepository;


}
