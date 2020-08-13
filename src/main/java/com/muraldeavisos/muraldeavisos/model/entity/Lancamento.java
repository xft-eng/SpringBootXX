package com.muraldeavisos.muraldeavisos.model.entity;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 150)
    
    private String titulo;

    @Column(nullable = false, length = 4000)
   
    private String descricao;

    @Column(name = "created", updatable = false)
    private LocalDate created;
    
    @PrePersist
    public void prePersist(){
        setCreated(LocalDate.now());
    }
}

