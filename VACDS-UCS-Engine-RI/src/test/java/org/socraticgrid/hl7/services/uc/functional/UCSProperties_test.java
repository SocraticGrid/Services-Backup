/**
 * 
 */
package org.socraticgrid.hl7.services.uc.functional;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.socraticgrid.hl7.services.uc.functional.UCSProperties.PROPS;

/**
 * @author steven
 * @created Jan 22, 2014

 */
public class UCSProperties_test {

	@Ignore
	@Test
	public void test() {
		//tests reading the junit property in ucs.properties
		String junitProp = UCSProperties.getProperty(PROPS.JUNIT);
		assertEquals("test-property", junitProp);
	}

}
