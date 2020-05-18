package org.mairie.comores.dao;


import org.mairie.comores.entities.Employe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeRepository extends JpaRepository<Employe, Long>{
	
	@Query("select e from Employe e where e.nomemp like :X order by e.nomemp asc")
	public Page<Employe>listeEmployeParNom(@Param("X")String nom, Pageable pageable);
	
	@Query("select e from Employe e where e.nomemp=:X and e.prenemp=:Y")
	public Employe chercherEmployeParNomEtPrenom(@Param("X")String nom, @Param("Y")String prenom);

}
