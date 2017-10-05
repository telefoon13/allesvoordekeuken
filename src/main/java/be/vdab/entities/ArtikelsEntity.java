package be.vdab.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "artikels", schema = "allesvoordekeuken")
@DiscriminatorColumn(name = "soort")
public abstract class ArtikelsEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private String naam;
	private BigDecimal aankoopprijs;
	private BigDecimal verkoopprijs;

	public ArtikelsEntity(String naam, BigDecimal aankoopprijs, BigDecimal verkoopprijs) {
		if (aankoopprijs.compareTo(verkoopprijs) > 0){
			throw new IllegalArgumentException();
		}
		setNaam(naam);
		setAankoopprijs(aankoopprijs);
		setVerkoopprijs(verkoopprijs);
	}

	public ArtikelsEntity() {
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
		if ( ! isNaamValid(naam)) {
			throw new IllegalArgumentException();
		}
		this.naam = naam;
	}

	@Basic
	@Column(name = "aankoopprijs")
	public BigDecimal getAankoopprijs() {
		return aankoopprijs;
	}

	public void setAankoopprijs(BigDecimal aankoopprijs) {
		if (!isPrijsValid(aankoopprijs)){
			throw new IllegalArgumentException();
		}
		this.aankoopprijs = aankoopprijs;
	}

	@Basic
	@Column(name = "verkoopprijs")
	public BigDecimal getVerkoopprijs() {
		return verkoopprijs;
	}

	public void setVerkoopprijs(BigDecimal verkoopprijs) {
		if (!isPrijsValid(verkoopprijs)){
			throw new IllegalArgumentException();
		}
		this.verkoopprijs = verkoopprijs;
	}



	public static boolean isNaamValid(String naam) {
		return naam != null && ! naam.isEmpty();
	}

	public static boolean isPrijsValid(BigDecimal prijs) {
		return prijs != null && prijs.compareTo(BigDecimal.valueOf(0.01)) >= 0;
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
