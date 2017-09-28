package be.vdab.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "artikels", schema = "allesvoordekeuken")
public class ArtikelsEntity {
	private long id;
	private String naam;
	private BigDecimal aankoopprijs;
	private BigDecimal verkoopprijs;

	@Id
	@Column(name = "id")
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

	@Basic
	@Column(name = "aankoopprijs")
	public BigDecimal getAankoopprijs() {
		return aankoopprijs;
	}

	public void setAankoopprijs(BigDecimal aankoopprijs) {
		this.aankoopprijs = aankoopprijs;
	}

	@Basic
	@Column(name = "verkoopprijs")
	public BigDecimal getVerkoopprijs() {
		return verkoopprijs;
	}

	public void setVerkoopprijs(BigDecimal verkoopprijs) {
		this.verkoopprijs = verkoopprijs;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ArtikelsEntity that = (ArtikelsEntity) o;

		if (id != that.id) return false;
		if (naam != null ? !naam.equals(that.naam) : that.naam != null) return false;
		if (aankoopprijs != null ? !aankoopprijs.equals(that.aankoopprijs) : that.aankoopprijs != null) return false;
		if (verkoopprijs != null ? !verkoopprijs.equals(that.verkoopprijs) : that.verkoopprijs != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = (int) id;
		result = 31 * result + (naam != null ? naam.hashCode() : 0);
		result = 31 * result + (aankoopprijs != null ? aankoopprijs.hashCode() : 0);
		result = 31 * result + (verkoopprijs != null ? verkoopprijs.hashCode() : 0);
		return result;
	}
}
