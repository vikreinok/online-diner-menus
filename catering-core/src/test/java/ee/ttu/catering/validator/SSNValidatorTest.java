package ee.ttu.catering.validator;

import ee.ttu.catering.rest.validator.SSNValidator;
import org.junit.Test;

public class SSNValidatorTest {

	
	// "^[1-6]\\d{2}([0][1-9]|[1][0-2])([1-9]|0[1-9]|1[0-9]|2[0-9]|3[0-1])\\d{4}$";
	public static AssertedValue[] FORMAT_SSN_EE = new AssertedValue[] {
		new AssertedValue("39008160326", true), // Aktiivne isikukood.
		new AssertedValue("19008160326", true), // [1-6]   piirjuht 1
		new AssertedValue("69008160326", true), // [1-6]   piirjuht 2
		new AssertedValue("09008160326", false),// [1-6]   piirjuht 3
		new AssertedValue("79008160326", false),// [1-6]   piirjuht 4
		new AssertedValue("30108160326", true), // \\d{2}  piirjuht 1
		new AssertedValue("39908160326", true), // \\d{2}  piirjuht 2
		new AssertedValue("3aa08160326", false),// \\d{2}  piirjuht 3
		new AssertedValue("39001160326", true), // ([0][1-9]|[1][0-2]) piirjuht 1
		new AssertedValue("39012160326", true), // ([0][1-9]|[1][0-2]) piirjuht 2
		new AssertedValue("39013160326", false),// ([0][1-9]|[1][0-2]) piirjuht 3
		new AssertedValue("39000160326", false),// ([0][1-9]|[1][0-2]) piirjuht 4
		new AssertedValue("39008010326", true), // ([1-9]|0[1-9]|1[0-9]|2[0-9]|3[0-1])  piirjuht 1
		new AssertedValue("39008310326", true), // ([1-9]|0[1-9]|1[0-9]|2[0-9]|3[0-1])  piirjuht 2
		new AssertedValue("39008000326", false),// ([1-9]|0[1-9]|1[0-9]|2[0-9]|3[0-1])  piirjuht 3
		new AssertedValue("39008320326", false),// ([1-9]|0[1-9]|1[0-9]|2[0-9]|3[0-1])  piirjuht 4
		new AssertedValue("39008160000", true), // \\d{4} piirjuht 1
		new AssertedValue("39008169999", true), // \\d{4} piirjuht 2
		new AssertedValue("39008161i11", false),// \\d{4} piirjuht 3
		new AssertedValue("39008160326", true), // Pikkus piirjuht 1
		new AssertedValue("390081603261",false),// Pikkus piirjuht 2
		new AssertedValue("3900816032",  false),// Pikkus piirjuht 3  Test failib
	};
		
	public static AssertedValue[] CEHECSUM_SSN_EE = new AssertedValue[] {
		new AssertedValue("35206200311", true),
		new AssertedValue("51001091072", true),
		new AssertedValue("39008160043", true),
		new AssertedValue("39008160054", true),
		new AssertedValue("14212128025", true),
		new AssertedValue("39008160326", true),
		new AssertedValue("39008160337", true),
		new AssertedValue("39008160228", true),
		new AssertedValue("38110240229", true),
		new AssertedValue("39008160250", true),
		new AssertedValue("38002240211", false),
		new AssertedValue("05058810082", false),
		new AssertedValue("05058810083", false),
		new AssertedValue("39008160327", false),
		new AssertedValue("14212128029", false),
	};
	
	SSNValidator ssnValidator;
	
	@Test
	public void ssnFormat() {
		ssnValidator = new SSNValidator(false);
		
		for (AssertedValue av : FORMAT_SSN_EE) {
			try {
				ssnValidator.validate(av.getValue());
				av.assertValue(true);
			} catch (RuntimeException e) {
				av.assertValue(false);
			}
		}
	}
	
	@Test
	public void ssnChecksumGeneric() {
		ssnValidator = new SSNValidator(true);

		for (AssertedValue av : CEHECSUM_SSN_EE) {
			try {
				ssnValidator.validate(av.getValue());
				av.assertValue(true);
			} catch (RuntimeException e) {
				av.assertValue(false);
			}
		}
	}
	
}
