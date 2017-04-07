package br.com.k19.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import br.com.k19.modelo.Produto;

public class AumentaPreco {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("K21_criteria_pu");
		EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();

		CriteriaBuilder cb = manager.getCriteriaBuilder();
		
		CriteriaUpdate<Produto> update = cb.createCriteriaUpdate(Produto.class);
		Root<Produto> produto = update.from(Produto.class);
		update.set(produto.<Double>get("preco"), cb.prod(produto.<Double>get("preco"), 1.1));
		
		Query query = manager.createQuery(update);
		query.executeUpdate();
		
		manager.getTransaction().commit();
		
		manager.close();
		factory.close();
	}
	
}
