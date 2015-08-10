package sentenceGenerator;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * Represents a BNF grammar.
 * @author Anushree Singh
 */
public class Grammar {
    private Map<String, ListOfDefinitions> grammar; // rules for all the nonterminals

    Map<String,ListOfDefinitions> getMap(){
    	return grammar;
    }
    /**
     * Constructs a new, empty grammar.
     */
    public Grammar() {
        grammar = new TreeMap<String, ListOfDefinitions>();
    }

    /**
     * Prompts the user to choose an input file, then reads and parses
     * a BNF grammar from that file. Each grammar rule must be on a single
     * line, but may have multiple alternatives. The same nonterminal may
     * be defined in multiple rules. Example:
     * <pre> &lt;np&gt; ::= &lt;det&gt; &lt;n&gt; | &lt;det&gt; &lt;adjs&gt; &lt;n&gt;
     * &lt;np&gt; ::= &lt;det&gt; &lt;n&gt; &lt;pp&gt;</pre>
     * 
     * @throws IOException If there is an error reading the file.
     */
    public Grammar(BufferedReader reader) throws IOException {
        grammar = new TreeMap<String,ListOfDefinitions>();
        
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
        	
			addRule(currentLine);
		}
       
        
    }

    /**
     * Adds definitions for a single nonterminal to this grammar. The input
     * text should be in the form:
     * <ul><li>A single nonterminal (the thing being defined),</li>
     *     <li>The symbol "::=", and</li>
     *     <li>A list of zero or more definitions, separated by the "|" symbol.</li>
     * </ul>
     * 
     * @param ruleText The text to be parsed and kept as definitions.
     * @throws IllegalArgumentException If the input parameter has a syntax error.
     */
    public void addRule(String ruleText) throws IllegalArgumentException {
        BnfTokenizer tokenizer = new BnfTokenizer();
        SingleDefinition sD = new SingleDefinition();
        
        
        tokenizer.tokenize(ruleText);
        boolean flag = false;
        String lhs = null;
		String token = null;
		
		token = tokenizer.nextToken();
		if("EOF".equals(token) || "[ERROR]".equals(token) )
			throw new IllegalArgumentException();
		else
			lhs = token;
		
		token = tokenizer.nextToken();
		if("EOF".equals(token) || "[ERROR]".equals(token) || !("::=".equals(token)) )
			throw new IllegalArgumentException();
		
		
		while(!"EOF".equals((token = tokenizer.nextToken()))){
	
			if("|".equals(token)){
				SingleDefinition temp =new SingleDefinition();
				temp.addAll(sD);
				addToGrammar(lhs,temp);
				sD.clear();
				continue;
			}
			if("[ERROR]".equals(token)){
				throw new IllegalArgumentException();
			}
			sD.add(token);
		}
		SingleDefinition temp =new SingleDefinition();
		temp.addAll(sD);
		addToGrammar(lhs,temp);
      
    }

    /**
     * Adds a single definition to this <code>Grammar</code>. If the
     * nonterminal has already been defined, the new definition is
     * appended to the existing definitions.
     * 
     * @param lhs The nonterminal being defined.
     * @param singleDefinition The new definition.
     */
    private void addToGrammar(String lhs, SingleDefinition singleDefinition) {
        
    	if(grammar.containsKey(lhs)){
    		ListOfDefinitions list = grammar.get(lhs);
    		list.add(singleDefinition);
    		grammar.put(lhs, list);
    	}
    	else{
    		ListOfDefinitions list = new ListOfDefinitions();
    		list.add(singleDefinition);
    		grammar.put(lhs, list);
    	}
    }

    /**
     * Throws an <code>IllegalArgumentException</code>, with the input parameter
     * as part of the exception message.
     * 
     * @param rule The text to be included in the exception.
     * @throws IllegalArgumentException To indicate a syntax error.
     */
    private void syntaxError(String rule) {
        throw new IllegalArgumentException("Syntax error in rule: " + rule);
    }
    
    /**
     * Returns a list of definitions for the given nonterminal.
     * 
     * @param nonterminal The nonterminal whose definitions are to be returned.
     * @return The definitions of the given nonterminal.
     */
    public ListOfDefinitions getDefinitions(String nonterminal) {
        return grammar.get(nonterminal);
    }
    
    /**
     * Prints this Grammar.
     */
    public void print() {
    	for (Entry<String, ListOfDefinitions> entry : grammar.entrySet())
    	{
    	    System.out.println(entry.getKey() + " ::= " + entry.getValue());
    	} 
       
        
    }

    /**
     * Returns <code>true</code> if the given string is a nonterminal,
     * as indicated by an initial <code>'&lt;'</code>.
     * @param s The token to be tested.
     * @return <code>true</code> if <code>s</code> is a nonterminal.
     */
    private static boolean isNonterminal(String s) {
        return s.startsWith("<");
    }
}