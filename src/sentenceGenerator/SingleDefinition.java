package sentenceGenerator;
import java.util.ArrayList;

/**
 * An <code>SingleDefinition</code> is one of the alternatives in a
 * <code>ListOfDefinitions</code> object.
 * It consists of one definition (a sequence of terminals and/or
 * nonterminals) with no <code>'|'</code> symbols, not including the
 * thing being defined (the "definiendum"). For example, given the rule</br></br>
 * &lt;verb phrase&gt; ::= &lt;intransitive verb&gt; | &lt;transitive verb&gt; &lt;noun phrase&gt;<br /></br>
 * then &lt;intransitive verb&gt; is a SingleDefinition, and so is
 * &lt;transitive verb&gt; &lt;noun phrase&gt;.
 * 
 * @author Anushree Singh
 */
public class SingleDefinition extends ArrayList<String> {

    /**
     * Constructs an empty <code>SingleDefiniton</code> object; terms can be
     * added to it via the inherited <code>ArrayList.add(String)</code> method.
     */
    SingleDefinition() {}
    
    /**
     * Returns a String consisting of the elements of this
     * <code>ArrayList</code>, separated by spaces.
     * 
     * @see java.util.AbstractCollection#toString()
     */
    @Override
    public String toString() {
        String result = "";
        
        for (int i=0; i<this.size() ; i++){
        	if(i==0){
        		result = result + this.get(i);
        	}
        	else{
        		
        	
        	result= result + " " + this.get(i);
        	}
        }
        return result;
    }
}
