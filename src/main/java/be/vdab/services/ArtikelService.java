package be.vdab.services;

import be.vdab.entities.ArtikelsEntity;
import be.vdab.filters.JPAFilter;
import be.vdab.repositories.ArtikelRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

public class ArtikelService {

	private final ArtikelRepository artikelRepository = new ArtikelRepository();

	public Optional<ArtikelsEntity> read(long id){
		EntityManager entityManager = JPAFilter.getEntityManager();
		try {
			return artikelRepository.read(id, entityManager);
		} finally {
			entityManager.close();
		}
	}

	public void create(ArtikelsEntity artikel){
		EntityManager entityManager = JPAFilter.getEntityManager();
		entityManager.getTransaction().begin();
		try {
			artikelRepository.create(artikel,entityManager);
			entityManager.getTransaction().commit();
		} catch (RuntimeException ex){
			entityManager.getTransaction().rollback();
		}
	}
}
