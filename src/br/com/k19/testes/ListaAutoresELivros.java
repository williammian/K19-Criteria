package br.com.k19.testes;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import br.com.k19.modelo.Autor;
import br.com.k19.modelo.Livro;

public class ListaAutoresELivros {
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("K21_criteria_pu");
		EntityManager manager = factory.createEntityManager();
		
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Autor> c = cb.createQuery(Autor.class);
		Root<Autor> a = c.from(Autor.class);
		a.fetch("livros", JoinType.LEFT);
		c.select(a).distinct(true);
		
		TypedQuery<Autor> query = manager.createQuery(c);
		List<Autor> autores = query.getResultList();
		
		System.out.println();
		
		for(Autor autor : autores){
			System.out.println("Autor: " + autor.getNome());
			Collection<Livro> livros = autor.getLivros();
			
			for(Livro livro : livros){
				System.out.println("Livro: " + livro.getNome());
			}
		}
		
		manager.close();
		factory.close();
	}
}
