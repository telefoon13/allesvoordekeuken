package be.vdab.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("F")
public class FoodArtikels extends ArtikelsEntity {

	private static final long serialVersionUID = 1L;
	private int houdbaarheid;

	public FoodArtikels(String naam, BigDecimal aankoopprijs, BigDecimal verkoopprijs, int houdbaarheid) {
		super(naam, aankoopprijs, verkoopprijs);
		setHoudbaarheid(houdbaarheid);
	}

	public FoodArtikels() { }

	@Basic
	@Column(name = "houdbaarheid")
	public int getHoudbaarheid() {
		return houdbaarheid;
	}

	public void setHoudbaarheid(int houdbaarheid) {
		if (houdbaarheid < 1 || houdbaarheid > 365){
			throw new IllegalArgumentException();
		}
		this.houdbaarheid = houdbaarheid;
	}



}
