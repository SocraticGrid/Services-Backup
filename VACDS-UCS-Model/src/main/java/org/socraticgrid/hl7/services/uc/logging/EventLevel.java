package org.socraticgrid.hl7.services.uc.logging;

public enum EventLevel {
	none(1000),
	trace(10),
	debug(30),
	info(50),
	warn(80),
	error(100),
	all(0);
	
	private int numVal;

	EventLevel(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }
}
