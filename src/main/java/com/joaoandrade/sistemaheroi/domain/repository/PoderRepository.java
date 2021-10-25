package com.joaoandrade.sistemaheroi.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.joaoandrade.sistemaheroi.domain.model.Poder;

@Repository
public interface PoderRepository extends JpaRepository<Poder, Long> {

	@Query("select p from Poder p where p.id = :poderId and p.heroi.id = :heroiId")
	Optional<Poder> buscarPoderDoheroi(Long heroiId, Long poderId);

}
