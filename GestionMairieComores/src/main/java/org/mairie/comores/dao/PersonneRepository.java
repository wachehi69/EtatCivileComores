package org.mairie.comores.dao;

import org.mairie.comores.entities.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonneRepository extends JpaRepository<Personne, Long>{

}
