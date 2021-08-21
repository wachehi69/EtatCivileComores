package org.mairie.comores;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GestionMairieComoresApplicationTests {

@Test
	public void contextLoads() {
	}
	
	@Test
	 public void asserThanOnePlusOneIsTwo() {
		int somme = 1 + 1;
		assertThat(somme).isNotNull();
		assertThat(somme).isEqualTo(2);
		assertThat(somme).isEqualTo(3);
	}
	
	@Test
	 public void asserThanOnePlusTwoIsThree() {
		int somme = 2 + 1;
		assertThat(somme).isNotNull();
		assertThat(somme).isEqualTo(3);
		assertThat(somme).isEqualTo(4);
		
		
		
	}
	

}
