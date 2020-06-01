package org.mairie.comores.dao;

import java.util.List;
import java.util.Optional;

import org.mairie.comores.entities.Employe;
import org.mairie.comores.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<Users, String>{
	
	
	@Query("select e from Employe e where  e.nomemp like :X  order by e.nomemp asc")
	public List<Employe>listeUsersEmployeParNom(@Param("X")String nom);
	
	
	@Query("select u from Employe e, Users u where  e.nomemp like :X AND u.employe.idempl=e.idempl order by e.nomemp asc")
	public List<Users>listeEmployeUsesParNom(@Param("X")String nom);
	
	
	@Query("select e from Employe e where e.nomemp like :X order by e.nomemp asc")
	public List<Employe>listeEmployeParNom(@Param("X")String nom);


	public Users findByUsername(String username);
	

}
