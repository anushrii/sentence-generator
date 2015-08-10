package sentenceGenerator;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Anushree Singh
 */
public class GrammarTest {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

   

    /**
     * Test method for {@link sentenceGenerator.Grammar#addRule(java.lang.String)}.
     */
    @Test
    public final void testAddRule() {
        Grammar grammar = new Grammar();
        grammar.addRule("A ::= B C");
        Map<String,ListOfDefinitions> map = grammar.getMap();
        assertTrue(map.containsKey("A"));
        assertEquals(map.get("A").toString(),"B C");
    }

    /**
     * Test method for {@link sentenceGenerator.Grammar#getDefinitions(java.lang.String)}.
     */
    @Test
    public final void testGetDefinitions() {
        Grammar grammar = new Grammar();
        Map<String,ListOfDefinitions> map = grammar.getMap();
        SingleDefinition s = new SingleDefinition();
        s.add("bar");
        s.add("baz");
        ListOfDefinitions l = new ListOfDefinitions();
        l.add(s);
        map.put("foo", l);
        assertEquals("bar baz",grammar.getDefinitions("foo").toString());
    }

}
