package org.mairie.comores.dao;

import org.mairie.comores.entities.ExtraitNaissancePersonne;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExtraitNaissancePersonneRepository  extends JpaRepository<ExtraitNaissancePersonne,Long>{
	
	@Query("select e from ExtraitNaissancePersonne e where e.nom like :X order by e.nom asc")
	public Page<ExtraitNaissancePersonne>listeExtraitNaissanceParNom(@Param("X")String nom, Pageable pageable);
	
	@Query("select e from ExtraitNaissancePersonne e where e.numExtrait =:X order by e.numExtrait asc")
	public Page<ExtraitNaissancePersonne>listeExtraitNaissanceParNumExtrait(@Param("X")Long numExtrait, Pageable pageable);

}
