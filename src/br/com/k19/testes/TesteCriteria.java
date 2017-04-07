package br.com.k19.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.k19.modelo.Livro;

public class TesteCriteria {
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("K21_criteria_pu");
		EntityManager manager = factory.createEntityManager();
	
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Livro> c = cb.createQuery(Livro.class);
		Root<Livro> l = c.from(Livro.class);
		c.select(l);
		
		TypedQuery<Livro> query = manager.createQuery(c);
		List<Livro> livros = query.getResultList();
		
		for(Livro livro : livros){
			System.out.println(livro.getNome());
		}
		
		manager.close();
		factory.close();
	}
}
