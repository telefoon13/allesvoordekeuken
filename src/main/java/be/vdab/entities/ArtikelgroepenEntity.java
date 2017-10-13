package be.vdab.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "artikelgroepen", schema = "allesvoordekeuken")
public class ArtikelgroepenEntity {

	private long id;
	private String naam;
	private Set<ArtikelsEntity> artikelsEntities;

	public ArtikelgroepenEntity(long id, String naam, Set<ArtikelsEntity> artikelsEntities) {
		this.id = id;
		this.naam = naam;
		this.artikelsEntities = artikelsEntities;
	}

	public ArtikelgroepenEntity() {
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Basic
	@Column(name = "naam")
	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	@OneToMany(mappedBy = "artikelgroep")
	@OrderBy("naam, id")
	public Set<ArtikelsEntity> getArtikelsEntities() {
		return artikelsEntities;
	}

	public void setArtikelsEntities(Set<ArtikelsEntity> artikelsEntities) {
		this.artikelsEntities = artikelsEntities;
	}

	public void addArtikelsEntity(ArtikelsEntity artikelsEntity){
		this.artikelsEntities.add(artikelsEntity);
		if (artikelsEntity.getArtikelgroep() != this){
			artikelsEntity.setArtikelgroep(this);
		}
	}

	public void removeArtikelsEntity(ArtikelsEntity artikelsEntities){
		this.artikelsEntities.remove(artikelsEntities);
		if (artikelsEntities.getArtikelgroep() == this){
			artikelsEntities.setArtikelgroep(null);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ArtikelgroepenEntity that = (ArtikelgroepenEntity) o;

		if (id != that.id) return false;
		if (naam != null ? !naam.equals(that.naam) : that.naam != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = (int) id;
		result = 31 * result + (naam != null ? naam.hashCode() : 0);
		return result;
	}
}
