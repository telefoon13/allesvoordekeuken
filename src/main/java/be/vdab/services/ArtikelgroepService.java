package be.vdab.services;

import be.vdab.entities.ArtikelgroepenEntity;
import be.vdab.repositories.ArtikelgroepRepository;

import java.util.List;
import java.util.Optional;

public class ArtikelgroepService extends AbstractService {

	private final ArtikelgroepRepository artikelgroepRepository = new ArtikelgroepRepository();

	public List<ArtikelgroepenEntity> findAll(){
		return artikelgroepRepository.findAll();
	}

	public Optional<ArtikelgroepenEntity> read(long id){
		return artikelgroepRepository.read(id);
	}

}
