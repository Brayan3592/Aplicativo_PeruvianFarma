package com.peruvianfarma.appweb.repository;

import com.peruvianfarma.appweb.model.Contacto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactoRepository  extends JpaRepository<Contacto, Integer>{
    
}
