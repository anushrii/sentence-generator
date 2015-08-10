/**
 * 
 */
package sentenceGenerator;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

/**
 * @author David Matuszek
 */
public class BnfTokenizerTest {
    BnfTokenizer t;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        t = new BnfTokenizer();
    }

    /**
     * Test method for construction, setting text, and getting token.
     */
    @Test
    public final void testInitialization() throws IOException {
        try {
            t.nextToken();
            fail("Should have thrown IllegalStateException");
        }
        catch (IllegalStateException e) {}
        t.tokenize("");
        assertEquals("EOF", t.nextToken());
    }
    
    /**
     * Test method for {@link sentenceGenerator.BnfTokenizer#tokenize(java.lang.String)}.
     */
    @Test
    public final void testTokenize() throws IOException {        
        t.tokenize("<nonterminal> ::= \"quoted string\" |   terminal + . \n");
        assertEquals("<nonterminal>", t.nextToken());
        assertEquals("::=", t.nextToken());
        assertEquals("quoted string", t.nextToken());
        assertEquals("|", t.nextToken());
        assertEquals("terminal", t.nextToken());
        assertEquals("+", t.nextToken());
        assertEquals(".", t.nextToken());
        assertEquals("\n", t.nextToken());

        String token;
        t.tokenize(" :( ::: :- : x ::= ");
        assertEquals(":", t.nextToken());
        assertEquals("(", t.nextToken());
        assertEquals("[ERROR]", t.nextToken());
        assertEquals(":", t.nextToken());
        assertEquals("-", t.nextToken());
        assertEquals(":", t.nextToken());
        assertEquals("x", t.nextToken());
        assertEquals("::=", t.nextToken());
        assertEquals("EOF", t.nextToken());
        
        t.tokenize("<multi word nonterminal>");
        assertEquals("<multi word nonterminal>", t.nextToken());
        
        t.tokenize("<sentence>  ::=  <noun phrase> <verb phrase>");
        assertEquals("<sentence>", t.nextToken());
        assertEquals("::=", t.nextToken());
        assertEquals("<noun phrase>", t.nextToken());
        assertEquals("<verb phrase>", t.nextToken());
        assertEquals("EOF", t.nextToken());
    }
}
