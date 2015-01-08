package ee.ttu.catering.validator;

import static org.junit.Assert.assertEquals;


/**
 * Defines a value to be asserted (in JUnit test) and an expected result.
 * This class is useful to define an array of values to be validated and results which expected 
 * 
 * Example of usage:
 * <pre>
 * 	for (AssertedValue av : FORMAT_SSN_EE) {
 * 	   try {
 *	   	  ssnValidator.validate(av.getValue());
 *	   	  av.assertValue(true);
 *	   } catch (RuntimeException e) {
 *	   	  av.assertValue(false);
 *	   }
 *	}
 * </pre>
 */
public class AssertedValue {

	private String value;
	private Object expectedResult;
	
	public AssertedValue(String value, Object expectedResult) {
		this.value = value;
		this.expectedResult = expectedResult;
	}

	public String getValue() {
		return value;
	}

	public void assertValue(boolean actualResult) {
		assertEquals("Value:" + value, expectedResult, actualResult);
	}
	
	public void assertValue(String actualResult) {
		assertEquals("Value:" + value, expectedResult, actualResult);
	}
	
	public void assertValue(int actualResult) {
		assertEquals("Value:" + value, expectedResult, actualResult);
	}
	
	public void assertValue(long actualResult) {
		assertEquals("Value:" + value, expectedResult, actualResult);
	}
}
