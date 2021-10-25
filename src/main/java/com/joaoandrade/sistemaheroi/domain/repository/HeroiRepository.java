package com.joaoandrade.sistemaheroi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joaoandrade.sistemaheroi.domain.model.Heroi;

@Repository
public interface HeroiRepository extends JpaRepository<Heroi, Long> {

}
