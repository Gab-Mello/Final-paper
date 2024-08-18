package com.gabriel.pive.fiv.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gabriel.pive.fiv.cultivation.entities.Cultivation;
import com.gabriel.pive.fiv.oocyteCollection.entities.OocyteCollection;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_fivs")
@Entity
public class Fiv {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "oocyteCollection_id")
    private OocyteCollection oocyteCollection;

    @OneToOne
    @JoinColumn(name = "cultivation_id")
    private Cultivation cultivation;



}
