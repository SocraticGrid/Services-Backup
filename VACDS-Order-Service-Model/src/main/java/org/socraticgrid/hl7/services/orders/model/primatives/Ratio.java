package org.socraticgrid.hl7.services.orders.model.primatives;


public class Ratio {

	Quantity numerator = new Quantity();
	Quantity demoninator= new Quantity();
	/**
	 * @return the numerator
	 */
	public Quantity getNumerator() {
		return numerator;
	}
	/**
	 * @param numerator the numerator to set
	 */
	public void setNumerator(Quantity numerator) {
		this.numerator = numerator;
	}
	/**
	 * @return the demoninator
	 */
	public Quantity getDemoninator() {
		return demoninator;
	}
	/**
	 * @param demoninator the demoninator to set
	 */
	public void setDemoninator(Quantity demoninator) {
		this.demoninator = demoninator;
	}
	
	
	
}
