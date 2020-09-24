package org.mairie.comores.dao;

import org.mairie.comores.entities.ExtraitDecesPersonne;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExtraitDecesPersonneRepository  extends JpaRepository<ExtraitDecesPersonne,Long>{
	
	@Query("select e from ExtraitDecesPersonne e where e.nom like :X order by e.nom asc")
	public Page<ExtraitDecesPersonne>listeExtraitDecesParNom(@Param("X")String nom, Pageable pageable);
	
	@Query("select e from ExtraitDecesPersonne e where e.numExtraitDeces =:X order by e.numExtraitDeces asc")
	public Page<ExtraitDecesPersonne>listeExtraitDecesParNumExtraitDeces(@Param("X")Long numExtraitDeces, Pageable pageable);

}
