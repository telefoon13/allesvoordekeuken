package be.vdab.services;

import be.vdab.entities.ArtikelsEntity;
import be.vdab.repositories.ArtikelRepository;

import java.util.List;
import java.util.Optional;

public class ArtikelService extends AbstractService {

	private final ArtikelRepository artikelRepository = new ArtikelRepository();

	public Optional<ArtikelsEntity> read(long id){
		return artikelRepository.read(id);
	}

	public void create(ArtikelsEntity artikel){
		beginTransaction();
		try {
			artikelRepository.create(artikel);
			commit();
		} catch (RuntimeException ex){
			rollback();
			throw ex;
		}
	}

	public List<ArtikelsEntity> findByNaam(String naam, int vanafRij, int aantalRijen) {
		return artikelRepository.findByNaam(naam,vanafRij,aantalRijen);
	}
}
