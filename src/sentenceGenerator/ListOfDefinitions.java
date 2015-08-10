package sentenceGenerator;
import java.util.ArrayList;

/**
 * A <code>ListOfDefinitions</code> object consists of a list of alternatives
 * (each of which is a list of terminals and/or nonterminals), but
 * does not include the thing being defined.
 * 
 * @author Anushree Singh
 */
public class ListOfDefinitions extends ArrayList<SingleDefinition> {
    
    /**
     * Constructs an empty list of definitions.
     */
    ListOfDefinitions() {}
    
    /**
     * Returns a string containing the contents of this <code>ArrayList</code>,
     * separated by <code>" | "</code> symbols.
     * 
     * @see java.util.AbstractCollection#toString()
     */
    @Override
    public String toString() {
    	 String result = "";
         
         for (int i=0; i<this.size() ; i++){
         	if(i==0){
         		result = result + this.get(i).toString();
         	}
         	else
         	{	
         	result= result + " | " + this.get(i).toString();
         	}
         }
         return result;
     
    }
}
