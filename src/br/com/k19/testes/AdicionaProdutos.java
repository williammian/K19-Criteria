package br.com.k19.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.k19.modelo.Produto;

public class AdicionaProdutos {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("K21_criteria_pu");
		EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();
		
		for(int i = 0; i < 100; i++){
			Produto p = new Produto();
			p.setNome("produto " + i);
			p.setPreco(i * 10.0);
			manager.persist(p);
		}
		
		manager.getTransaction().commit();
		
		manager.close();
		factory.close();
	}
	
}
