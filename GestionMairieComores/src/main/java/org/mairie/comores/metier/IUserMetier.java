package org.mairie.comores.metier;

import java.util.List;

import org.mairie.comores.entities.Employe;
import org.mairie.comores.entities.EmployeUsers;
import org.mairie.comores.entities.Users;
import org.springframework.data.repository.query.Param;

public interface IUserMetier {
	
	public Users saveUsers(Users user);
	public List<Users> listUsers();
	public Users ConsultationUser(String nom, String prenom);
	public Users getUsers(String username);
	public Users UpdateUser(Users users);
	public void deleteUsers(String username);
	public List<EmployeUsers> listeUsersEmployeParNom(String nom);
	public List<Employe>listeEmployeParNom(String nom);

}
