package org.mairie.comores.metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mairie.comores.dao.EmployeRepository;
import org.mairie.comores.dao.UserRepository;
import org.mairie.comores.entities.Employe;
import org.mairie.comores.entities.EmployeUsers;
import org.mairie.comores.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserMetierImpl implements IUserMetier {

	@Autowired
	private UserRepository userRepository;

	private EmployeRepository employeRepository;
	


	@Override
	public Users saveUsers(Users user) {
		return userRepository.save(user);
	}

	@Override
	public List<Users> listUsers() {
		return userRepository.findAll();
	}

	@Override
	public Users UpdateUser(Users users) {
		return userRepository.saveAndFlush(users);
	}

	@Override
	public void deleteUsers(String username) {
		userRepository.deleteById(username);

	}

	@Override
	public Users ConsultationUser(String nom, String prenom) {
		return null;
	}

	@Override
	public Employe getEmploye(String username) {
		Users user = getUsers(username);
		if (null == user)
			throw new RuntimeException("username inexistant !!!");
		Optional<Employe> emp = employeRepository.findById(user.getEmploye().getIdempl());
		return emp.get();
	}

	@Override
	public Users getUsers(String username) {
		Optional<Users> user = userRepository.findById(username);
		if (user.get() == null)
			throw new RuntimeException("username inexistant !!!!");
		return user.get();
	}

	/**
	 * Cette methode permet d'afficher les utilisateurs
	 */
	@Override
	public List<EmployeUsers> listeUsersEmployeParNom(String motCle) {
		
	  List<EmployeUsers> listEmplUSers = new ArrayList<>();

		List<Employe> listEmployeUsers = userRepository.listeUsersEmployeParNom("%" + motCle + "%");
			if(listEmployeUsers== null || listEmployeUsers.isEmpty()) throw new RuntimeException("Employe inexistant");
			
		 List<Users> listUSersEmpl = userRepository.listeEmployeUsesParNom("%" + motCle + "%");
		 if(listUSersEmpl== null || listUSersEmpl.isEmpty()) throw new RuntimeException("Utilisateur inexistant");

		for (Employe employe : listEmployeUsers) {
			EmployeUsers employeUsers = new EmployeUsers();

			for (Users users : listUSersEmpl) {

				if (employe.getIdempl() == users.getEmploye().getIdempl()) {
					employeUsers.setIdempl(employe.getIdempl());
					employeUsers.setNomDuSexe(employe.getNomDuSexe());
					employeUsers.setNomemp(employe.getNomemp());
					employeUsers.setPrenemp(employe.getPrenemp());
					employeUsers.setUsername(users.getUsername());
					employeUsers.setPassword(users.getPassword());
					employeUsers.setRoles(users.getRoles());
					listEmplUSers.add(employeUsers);
				}
			}

		}
		return listEmplUSers;
	}

	@Override
	public List<Employe> listeEmployeParNom(String nom) {
		return userRepository.listeEmployeParNom(nom);
	}

}
