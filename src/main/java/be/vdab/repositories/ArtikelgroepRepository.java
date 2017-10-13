package be.vdab.repositories;

import be.vdab.entities.ArtikelgroepenEntity;

import java.util.List;
import java.util.Optional;

public class ArtikelgroepRepository extends AbstractRepository {

	public List<ArtikelgroepenEntity> findAll(){
		return getEntityManager().createNamedQuery("ArtikelgroepenEntity.findAll", ArtikelgroepenEntity.class).getResultList();
	}

	public Optional<ArtikelgroepenEntity> read(long id){
		return Optional.ofNullable(getEntityManager().find(ArtikelgroepenEntity.class, id));
	}
}
