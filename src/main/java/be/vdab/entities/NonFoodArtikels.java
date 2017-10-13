package be.vdab.entities;

import be.vdab.valueobjects.Korting;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@DiscriminatorValue("NF")
public class NonFoodArtikels extends ArtikelsEntity{

	private static final long serialVersionUID = 1L;
	private int garantie;

	public NonFoodArtikels(String naam, BigDecimal aankoopprijs, BigDecimal verkoopprijs, int garantie) {
		super(naam, aankoopprijs, verkoopprijs);
		setGarantie(garantie);
	}

	public NonFoodArtikels() { }

	@Basic
	@Column(name = "garantie")
	public int getGarantie() {
		return garantie;
	}

	public void setGarantie(int garantie) {
		if (garantie < 1 || garantie > 48){
			throw new IllegalArgumentException();
		}
		this.garantie = garantie;
	}
}
