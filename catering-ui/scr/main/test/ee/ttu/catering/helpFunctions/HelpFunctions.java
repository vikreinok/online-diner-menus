package ee.ttu.catering.helpFunctions;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelpFunctions {
	
	public LinkedList<String> extractDigits(String src) {
		LinkedList<String> numbers = new LinkedList<String>();

		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(src); 
		while (m.find()) {
		   numbers.add(m.group());
		}
	    return numbers;
	}

}
