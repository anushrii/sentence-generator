package sentenceGenerator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * This is my version of a CIT594 assignment to read in a BNF grammar
 * and produce sentences from that grammar.
 */
public class SentenceGenerator extends JFrame {
    private Grammar grammar;
    private Random random = new Random();

    /**
     * Prompts the user for a file containing a BNF grammar, then
     * generates several sentences from that grammar.
     * 
     * @param args Unused.
     */
    public static void main(String[] args) {
        try {
            new SentenceGenerator().run();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Does the work of this class.
     * 
     * @throws IOException If an input exception occurs.
     */
    private void run() throws IOException {
        
        BufferedReader br = getFileReader();
        grammar = new Grammar(br);
//        grammar.print();
        for(int i=0;i<20;i++){
        	printAsSentence(generate("<sentence>"));
        }
        
    }
    
    /**
     * Expands the given term into a list of terminals. If the given
     * term is already a terminal, a list containing this single term
     * is returned.
     * 
     * @param term A terminal or nonterminal to expand into a list.
     * @return A list of terminals.
     */
    List<String> generate(String term) {
        List<String> result = new ArrayList<String>();

//        String tempTerm = new String(term);
//        while(true){
//        	if(!isNonTerminal(tempTerm)){
//        		result.add(tempTerm);
//        		break;
//             	}
//        	else {
//        		 ListOfDefinitions lst = grammar.getDefinitions(tempTerm);
//        		 SingleDefinition sd = (SingleDefinition)chooseRandomElement(lst);
//        		 result.addAll(sd);
//        	}
//        }
        result.add(term);
        
        for(int i=0;i<result.size();i++){
        	
        	if(isNonTerminal(result.get(i))){
        		ListOfDefinitions list = grammar.getDefinitions(result.get(i));
        		SingleDefinition sd = (SingleDefinition)chooseRandomElement(list);
        		for(String s : sd){
        					

        			if(i<=result.size()){
        				result.add(i,s);
        				i++;
        			}else{
        				result.add(s);
        			}
        		}
        		result.remove(i);
        		i=-1;
        	}
        }
        
        
        

        return result;
        
    }
    
    /**
     * Checks if the term contains a terminal 
     * 
     * @param term A terminal or nonterminal.
     * @return A boolean.
     */
    
    private boolean isNonTerminal(String term ){
    	return term.contains("<") && term.contains(">");
    }

    /**
     * Randomly choose and return one element from a list.
     * 
     * @param list The list from which the selection is to be made.
     * @return The randomly selected element.
     */
    private Object chooseRandomElement(List list) {
        return (list.get(random.nextInt(list.size())));
    }

    /**
     * Prints the given list of words as a sentence. The first word is
     * capitalized, and a period is printed at the end.
     * 
     * @param list The words to be printed.
     */
    void printAsSentence(List<String> list) {
    	
    	String sentence = list.get(0).toUpperCase();
    	for (int i = 1; i<list.size() ; i++ ){
    		sentence = sentence + " "+ list.get(i); 
    	}
    	sentence = sentence + ".";
    	System.out.println(sentence);
    }
    
    /**
     * Prompts the user to choose a file, which should contain a BNF grammar.
     * 
     * @return The chosen file, or <code>null</code> if none is chosen.
     * @throws FileNotFoundException 
     */
    private BufferedReader getFileReader() throws FileNotFoundException {
        BufferedReader reader = null;
        JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(this);
		File selectedFile = null;
		if (result == JFileChooser.APPROVE_OPTION) {
		     selectedFile = fileChooser.getSelectedFile();
		    
		}
        reader = new BufferedReader(new FileReader(selectedFile));
        return reader;
    }
}
