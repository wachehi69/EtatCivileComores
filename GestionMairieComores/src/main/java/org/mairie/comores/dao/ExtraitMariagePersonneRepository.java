package org.mairie.comores.dao;

import org.mairie.comores.entities.ExtraitMariagePersonne;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExtraitMariagePersonneRepository extends JpaRepository<ExtraitMariagePersonne,Long> {
	
	//On va chercher tous les traits dont les noms commencent par une chaîne particulière 
	@Query("select e from ExtraitMariagePersonne e where e.nomMari like :X order by e.nomMari asc")
	public Page<ExtraitMariagePersonne>listeExtraitMariageParNom(@Param("X")String nomMari, Pageable pageable);
	
	// cette methode renvoie tous les extraits de mariage dont les noms commencent par numExtMariage
	@Query("select e from ExtraitMariagePersonne e where e.numExtMariage =:X order by e.numExtMariage asc")
	public Page<ExtraitMariagePersonne>listeExtraitMariageParNumExtrait(@Param("X")Long numExtMariage, Pageable pageable);


}
