package org.socraticgrid.hl7.services.orders.model.types.orderitems;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.socraticgrid.hl7.services.orders.model.OrderItem;
import org.socraticgrid.hl7.services.orders.model.primatives.Code;
import org.socraticgrid.hl7.services.orders.model.primatives.Identifier;
import org.socraticgrid.hl7.services.orders.model.primatives.Period;
import org.socraticgrid.hl7.services.orders.model.primatives.Quantity;
import org.socraticgrid.hl7.services.orders.model.primatives.Ratio;

public class MedicationOrderItem extends OrderItem 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String additionalDosageIntructions;
	private String comment;
	
	private Quantity dispenseQuantity= new Quantity();
	
	
	private String dosageInstructions;
	private Code dosageMethod;
	
	
	private Quantity dosageQuantity = new Quantity();
	private Ratio dosageRate = new Ratio();
	private Code dosageSite = new Code();
	
	private Date dosageTiming;
	private Period dosageTimingPeriod = new Period();
	
	private List<Identifier> drug = new ArrayList<Identifier>(0);
	private Date endDate;
	
	private Quantity expectedSupplyDuration = new Quantity();
	
	private Ratio maxDosePerPeriod = new Ratio();
	
	private List<Identifier> medication = new ArrayList<Identifier>(0);
	
	private int numberOfRepeatsAllowed=0;
	
	private Code prescriber;
	
	private Code route = new Code();
	private String schedule;
	
	
	private Date startDate;


	/**
	 * @return the additionalDosageIntructions
	 */
	public String getAdditionalDosageIntructions() {
		return additionalDosageIntructions;
	}


	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}


	/**
	 * @return the dispenseQuantity
	 */
	public Quantity getDispenseQuantity() {
		return dispenseQuantity;
	}


	/**
	 * @return the dosageInstructions
	 */
	public String getDosageInstructions() {
		return dosageInstructions;
	}


	/**
	 * @return the dosageMethod
	 */
	public Code getDosageMethod() {
		return dosageMethod;
	}


	/**
	 * @return the dosageQuantity
	 */
	public Quantity getDosageQuantity() {
		return dosageQuantity;
	}


	/**
	 * @return the dosageRate
	 */
	public Ratio getDosageRate() {
		return dosageRate;
	}


	/**
	 * @return the dosageSite
	 */
	public Code getDosageSite() {
		return dosageSite;
	}


	/**
	 * @return the dosageTiming
	 */
	public Date getDosageTiming() {
		return dosageTiming;
	}


	/**
	 * @return the dosageTimingPeriod
	 */
	public Period getDosageTimingPeriod() {
		return dosageTimingPeriod;
	}


	/**
	 * @return the drug
	 */
	public List<Identifier> getDrug() {
		return drug;
	}


	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}


	/**
	 * @return the expectedSupplyDuration
	 */
	public Quantity getExpectedSupplyDuration() {
		return expectedSupplyDuration;
	}


	/**
	 * @return the maxDosePerPeriod
	 */
	public Ratio getMaxDosePerPeriod() {
		return maxDosePerPeriod;
	}


	/**
	 * @return the medication
	 */
	public List<Identifier> getMedication() {
		return medication;
	}


	/**
	 * @return the numberOfRepeatsAllowed
	 */
	public int getNumberOfRepeatsAllowed() {
		return numberOfRepeatsAllowed;
	}


	/**
	 * @return the prescriber
	 */
	public Code getPrescriber() {
		return prescriber;
	}


	/**
	 * @return the route
	 */
	public Code getRoute() {
		return route;
	}


	/**
	 * @return the schedule
	 */
	public String getSchedule() {
		return schedule;
	}


	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}



	/**
	 * @param additionalDosageIntructions the additionalDosageIntructions to set
	 */
	public void setAdditionalDosageIntructions(String additionalDosageIntructions) {
		this.additionalDosageIntructions = additionalDosageIntructions;
	}


	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}


	/**
	 * @param dispenseQuantity the dispenseQuantity to set
	 */
	public void setDispenseQuantity(Quantity dispenseQuantity) {
		this.dispenseQuantity = dispenseQuantity;
	}


	/**
	 * @param dosageInstructions the dosageInstructions to set
	 */
	public void setDosageInstructions(String dosageInstructions) {
		this.dosageInstructions = dosageInstructions;
	}


	/**
	 * @param dosageMethod the dosageMethod to set
	 */
	public void setDosageMethod(Code dosageMethod) {
		this.dosageMethod = dosageMethod;
	}


	/**
	 * @param dosageQuantity the dosageQuantity to set
	 */
	public void setDosageQuantity(Quantity dosageQuantity) {
		this.dosageQuantity = dosageQuantity;
	}

	/**
	 * @param dosageRate the dosageRate to set
	 */
	public void setDosageRate(Ratio dosageRate) {
		this.dosageRate = dosageRate;
	}


	/**
	 * @param dosageSite the dosageSite to set
	 */
	public void setDosageSite(Code dosageSite) {
		this.dosageSite = dosageSite;
	}


	/**
	 * @param dosageTiming the dosageTiming to set
	 */
	public void setDosageTiming(Date dosageTiming) {
		this.dosageTiming = dosageTiming;
	}


	/**
	 * @param dosageTimingPeriod the dosageTimingPeriod to set
	 */
	public void setDosageTimingPeriod(Period dosageTimingPeriod) {
		this.dosageTimingPeriod = dosageTimingPeriod;
	}


	/**
	 * @param drug the drug to set
	 */
	public void setDrug(List<Identifier> drug) {
		this.drug = drug;
	}


	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	/**
	 * @param expectedSupplyDuration the expectedSupplyDuration to set
	 */
	public void setExpectedSupplyDuration(Quantity expectedSupplyDuration) {
		this.expectedSupplyDuration = expectedSupplyDuration;
	}


	/**
	 * @param maxDosePerPeriod the maxDosePerPeriod to set
	 */
	public void setMaxDosePerPeriod(Ratio maxDosePerPeriod) {
		this.maxDosePerPeriod = maxDosePerPeriod;
	}


	/**
	 * @param medication the medication to set
	 */
	public void setMedication(List<Identifier> medication) {
		this.medication = medication;
	}


	/**
	 * @param numberOfRepeatsAllowed the numberOfRepeatsAllowed to set
	 */
	public void setNumberOfRepeatsAllowed(int numberOfRepeatsAllowed) {
		this.numberOfRepeatsAllowed = numberOfRepeatsAllowed;
	}


	/**
	 * @param prescriber the prescriber to set
	 */
	public void setPrescriber(Code prescriber) {
		this.prescriber = prescriber;
	}


	/**
	 * @param route the route to set
	 */
	public void setRoute(Code route) {
		this.route = route;
	}


	/**
	 * @param schedule the schedule to set
	 */
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}


	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	
	
	
		
}
