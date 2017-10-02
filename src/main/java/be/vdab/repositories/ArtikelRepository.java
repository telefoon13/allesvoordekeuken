package be.vdab.repositories;

import be.vdab.entities.ArtikelsEntity;
import be.vdab.filters.JPAFilter;

import javax.persistence.EntityManager;
import java.util.Optional;

public class ArtikelRepository {

	public Optional<ArtikelsEntity> read(long id, EntityManager entityManager){
		return Optional.ofNullable(entityManager.find(ArtikelsEntity.class, id));
	}

	public void create(ArtikelsEntity artikel, EntityManager entityManager){
		entityManager.persist(artikel);
	}
}
