package be.vdab.repositories;

import be.vdab.entities.ArtikelsEntity;
import be.vdab.filters.JPAFilter;
import be.vdab.services.ArtikelService;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class ArtikelRepository extends AbstractRepository {

	public Optional<ArtikelsEntity> read(long id){
		return Optional.ofNullable(getEntityManager().find(ArtikelsEntity.class, id));
	}

	public void create(ArtikelsEntity artikel){
		getEntityManager().persist(artikel);
	}

	public List<ArtikelsEntity> findByNaam(String naam, int vanafRij, int aantalRijen) {
		return getEntityManager()
				.createNamedQuery("ArtikelsEntity.findByNaam", ArtikelsEntity.class)
				.setParameter("naam","%" + naam + "%")
				.setFirstResult(vanafRij)
				.setMaxResults(aantalRijen)
				.getResultList();
	}

	public void prijsVerhoging(BigDecimal factor) {
		getEntityManager().createNamedQuery("ArtikelsEntity.prijsVerhoging").setParameter("factor", factor).executeUpdate();
	}
}
