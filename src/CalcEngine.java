import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * The main part of the calculator doing the calculations.
 * 
 * @author  David J. Barnes and Michael Kolling 
 * @version 2008.03.30
 */
public class CalcEngine
{
    // The calculator's state is maintained in three fields:
    //     buildingDisplayValue, haveLeftOperand, and lastOperator.
    
    // Are we already building a value in the display, or will the
    // next digit be the first of a new one?
    private boolean buildingDisplayValue;


    // The current value (to be) shown in the display.
    private String displayString;

    // The value of an existing left operand.

    
    private Set<String> setA = new HashSet<>();
    private Set<String> setB = new HashSet<>();
    
    private Set<String> setResult = new HashSet<>();

    /**
     * Create a CalcEngine.
     */
    public CalcEngine()
    {
        clear();
    }
    


    /**
     * @return The value that should currently be displayed
     * on the calculator display.
     */
    public String getDisplayString()
    {
    	return displayString;
    }
    
    public void setDisplayString(String string)
    {
    	displayString=string;
    }
    
    /**
     * A number button was pressed.
     * Either start a new operand, or incorporate this number as
     * the least significant digit of an existing one.
     * @param command The number pressed on the calculator.
     */


    // transforms a string into a set ; separates the string at each ","
	private Set<String> stringToSet(String s) {
		Set<String> set = new HashSet<>();
		String component = "";
		char c;
		
		for (int i = 0; i < s.length(); i++) {
			c = s.charAt(i);
			if (c == ',') {
				set.add(component);
				component = "";
			} else if(i==s.length()-1) {//so that the last number is added to the set
				component += c;
				set.add(component);
			}else component += c;
		}
		return set;
	}
	
    // transforms set to a string ; elements are separated by a ","
	private String setToString(Set<String> setResult) {
		String result = "";
		//iterator so that its possible to check whether we are at the end of the set
		//needed to NOT add a "," at the end
		Iterator<String> iterator= setResult.iterator();
		while(iterator.hasNext()) {
			String element= iterator.next();
			if (iterator.hasNext()) {
				result = result + element + ",";
			}
			else  result = result + element;
		}
		
		return result;
	}
    /**
     * The 'plus' button was pressed. 
     */
	//empties the set which contains the result
    private void emptyResultSet() {
    	setResult.clear();
    }
    
    //transforms the string setA and setB to sets and saves them in the fields set and setB
    public void getSets(String setA, String setB) {
    	this.setA= stringToSet(setA);
    	this.setB= stringToSet(setB);
    }
    
    public void union()
    {
    	//make the result set
    	emptyResultSet();
    	for(String element:setA) {
    		setResult.add(element);
    	}
    	for(String element:setB) {
    		setResult.add(element);
    	}
    	//set the displayString
    	String resultString= setToString(setResult);
    	setDisplayString(resultString);
    }

    
    /**
     * The 'minus' button was pressed.
     */
    public void subtraction()
    {
    	//make the result set
    	emptyResultSet();
    	
    	for(String element:setA) {
    		if(!setB.contains(element))setResult.add(element);
    	}
    	//set the displayString
    	String resultString= setToString(setResult);
    	setDisplayString(resultString);
    }
    
    /**
     * The 'minus' button was pressed.
     */
    public void intersection()
    {
    	//make the result set
    	emptyResultSet();
    	
    	for(String element:setA) {
    		if(setB.contains(element))setResult.add(element);
    	}
    	//set the displayString
    	String resultString= setToString(setResult);
    	setDisplayString(resultString);
    }
    
    // sets displayString the length of setA
    public void lengthSetA() {
    	int length= setA.size();
    	setDisplayString(""+length);
    }
 // sets displayString the length of setB
    public void lengthSetB() {
    	int length= setB.size();
    	setDisplayString(""+length);
    }



   

	/**
     * The 'C' (clear) button was pressed.
     * Reset everything to a starting state.
     */
    public void clear()
    {
        displayString= "";
    }

    /**
     * @return The title of this calculation engine.
     */
    public String getTitle()
    {
        return "Java Calculator";
    }

    /**
     * @return The author of this engine.
     */
    public String getAuthor()
    {
        return "David J. Barnes and Michael Kolling";
    }

    /**
     * @return The version number of this engine.
     */
    public String getVersion()
    {
       return "Version 1.0";
    }



    /**
     * Report an error in the sequence of keys that was pressed.
     */
    private void keySequenceError()
    {
        System.out.println("A key sequence error has occurred.");
        // Reset everything.
        clear();
    }
}
