package ee.ttu.catering.rest.validator;

import java.util.regex.Pattern;


public class SSNValidator {
	
    private static final String SSN_FORMAT_ERROR = "SSN format error";
    private static final String SSN_CHECKSUM_ERROR = "SSN checksum error";
	public static final Pattern PATTERN_SSN_EE = Pattern.compile("^[1-6]\\d{2}([0][1-9]|[1][0-2])([1-9]|0[1-9]|1[0-9]|2[0-9]|3[0-1])\\d{4}$");
	
	private boolean enableCheckSum;
	
	public SSNValidator(boolean enableCheckSum) {
		this.enableCheckSum = enableCheckSum;
	}

	public void validate(String ssn) throws RuntimeException {
		validate(ssn, PATTERN_SSN_EE);
	}
	
    protected void validate(String ssn, Pattern pattern) throws RuntimeException {
        ssn = ssn.trim();
        if (pattern.matcher(ssn).matches()) {
        	if (!validateChecksum(ssn)) 
        		throw new RuntimeException(SSN_FORMAT_ERROR);
        }
        else {
        	throw new RuntimeException(SSN_CHECKSUM_ERROR);
        }
    }
    
	protected boolean validateChecksum(String ssn) {
		if(enableCheckSum)
			return calculateChecksum(ssn)  == Character.getNumericValue( ssn.charAt(ssn.length()-1) );
		else
			return true;
	}
	
	protected int calculateChecksum(String ssn) {
		
		String firstTenDigits = ssn.substring(0, 10);
		final int[] WEIGHTS_1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 1};
		final int[] WEIGHTS_2 = { 3, 4, 5, 6, 7, 8, 9, 1, 2, 3};
		
	    int controlDigit = 0;
	    for(int i = 0; i < 10; i++)
	        controlDigit +=  Character.getNumericValue(firstTenDigits.charAt(i)) * WEIGHTS_1[i];
	    controlDigit = controlDigit % 11;
	    if (controlDigit == 10) {
	        controlDigit = 0;
	        for(int i = 0; i < 10; i++)
	            controlDigit +=   Character.getNumericValue(firstTenDigits.charAt(i)) * WEIGHTS_2[i];
	        controlDigit = controlDigit % 11;
	        if (controlDigit == 10)
	            controlDigit = 0;
	    }
	    return controlDigit;
		
	}
}
