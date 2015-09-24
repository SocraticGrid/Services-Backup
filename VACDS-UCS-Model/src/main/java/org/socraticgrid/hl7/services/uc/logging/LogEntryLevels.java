package org.socraticgrid.hl7.services.uc.logging;


public class LogEntryLevels {
	EventLevel minLevel;
	EventLevel maxLevel;
	
	public LogEntryLevels ()
	{
		
	}
	
	public LogEntryLevels (EventLevel min, EventLevel max)
	{
		this.maxLevel=max;
		this.minLevel=min;
	}

	/**
	 * @return the minLevel
	 */
	public EventLevel getMinLevel() {
		return minLevel;
	}

	/**
	 * @param minLevel the minLevel to set
	 */
	public void setMinLevel(EventLevel minLevel) {
		this.minLevel = minLevel;
	}

	/**
	 * @return the maxLevel
	 */
	public EventLevel getMaxLevel() {
		return maxLevel;
	}

	/**
	 * @param maxLevel the maxLevel to set
	 */
	public void setMaxLevel(EventLevel maxLevel) {
		this.maxLevel = maxLevel;
	}
	
}
