package br.com.k19.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.k19.modelo.Livro;

public class CalculaMediaDosPrecosDosLivros {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("K21_criteria_pu");
		EntityManager manager = factory.createEntityManager();
		
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Double> c = cb.createQuery(Double.class);
		Root<Livro> livro = c.from(Livro.class);
		c.select(cb.avg(livro.<Double>get("preco")));
		
		TypedQuery<Double> query = manager.createQuery(c);
		Double media = query.getSingleResult();
		
		System.out.println("Média: " + media);
				
		manager.close();
		factory.close();
	}
	
}
