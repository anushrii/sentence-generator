package sentenceGenerator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Anushree Singh
 */
public class SingleDefinitionTest {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Test method for {@link sentenceGenerator.SingleDefinition#toString()}.
     */
    @Test
    public final void testToString() {
       
    	SingleDefinition testObj = new SingleDefinition();
    	testObj.add("the");
    	testObj.add("dog");
    	testObj.add("eats");
    	testObj.add("fruits");
    	
    	String expected = "the dog eats fruits";
    	String actual = testObj.toString();
    	assertEquals(expected, actual);
    		
    	
    }

}
