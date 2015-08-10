package sentenceGenerator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Anushree Singh
 */
public class ListOfDefinitionsTest {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Test method for {@link sentenceGenerator.ListOfDefinitions#toString()}.
     */
    @Test
    public final void testToString() {
      	ListOfDefinitions testObj = new ListOfDefinitions();
      	SingleDefinition testObj1 = new SingleDefinition();
      	SingleDefinition testObj2 = new SingleDefinition();
      	
      	testObj1.add("the");
      	testObj1.add("dog");
      	testObj1.add("eats");
      	testObj1.add("fruits");
  
    	testObj2.add("eats");
    	testObj2.add("vegetables");
    	
    	testObj.add(testObj1);
    	testObj.add(testObj2);
    	
    	String expected = "the dog eats fruits | eats vegetables";
    	String actual = testObj.toString();
    	assertEquals(expected, actual);
    		
    	
    }

}
