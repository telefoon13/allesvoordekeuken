package be.vdab.valueobjects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;

@Embeddable
public class Korting implements Serializable{

	private static final long serialVersionUID = 1L;
	private int vanafAantal;
	private BigDecimal kortingsPercentage;

	public Korting(int vanafAantal, BigDecimal kortingsPercentage) {
		this.vanafAantal = vanafAantal;
		this.kortingsPercentage = kortingsPercentage;
	}

	public Korting() {
	}

	@Basic
	@Column(name = "vanafaantal")
	public int getVanafAantal() {
		return vanafAantal;
	}

	public void setVanafAantal(int vanafAantal) {
		this.vanafAantal = vanafAantal;
	}

	@Basic
	@Column(name = "kortingspercentage")
	public BigDecimal getKortingsPercentage() {
		return kortingsPercentage;
	}

	public void setKortingsPercentage(BigDecimal kortingsPercentage) {
		this.kortingsPercentage = kortingsPercentage;
	}
}
