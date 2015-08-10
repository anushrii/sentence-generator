package sentenceGenerator;
import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringReader;

/**
 * A tokenizer for BNF definitions.
 * 
 * @author David Matuszek
 */

public class BnfTokenizer {
    private enum State { INITIAL, IN_NONTERMINAL, AFTER_COLON, AFTER_COLON_COLON }
    private StreamTokenizer tokenizer;
    
    /**
     * Constructs a <code>BnfTokenizer</code> with no text to tokenize; must call
     * <code>tokenize(String)</code> before asking for tokens.
     */
    public BnfTokenizer() {}
    
    /**
     * Constructs a <code>BnfTokenizer</code> with specific initial text to tokenize.
     * 
     * @param text The text to be tokenized.
     */
    public BnfTokenizer(String text) { tokenize(text); }
    
    /**
     * Sets the text to be turned into tokens by calls to nextToken().
     * 
     * @param text The text to be tokenized.
     */
    public void tokenize(String text) {
        Reader reader = new StringReader(text);
        tokenizer = new StreamTokenizer(reader);
        tokenizer.eolIsSignificant(true);
        tokenizer.ordinaryChar('\'');
        tokenizer.ordinaryChar('.');
    }

    /**
     * Tokenizes BNF definitions and returns each token as a String,
     * one token per call.
     * <br>Examples:
     * <table border="1" cellpadding="4">
     *   <tr>
     *     <th>Input characters</th>
     *     <th>Returned string</th>
     *   </tr>
     *   <tr><td>&lt;nonterminal&gt;</td><td>&lt;nonterminal&gt;</td></tr>
     *   <tr><td>terminal</td><td>nonterminal</td></tr>
     *   <tr><td>"terminal"</td><td>nonterminal</td></tr>
     *   <tr><td>+</td><td>+</td></tr>
     *   <tr><td>::=</td><td>::=</td></tr>
     *   <tr><td>|</td><td>|</td></tr>
     *   <tr><td>\n</td><td>\n</td></tr>
     *   <tr><td>(end of input)</td><td>[END OF FILE]</td></tr>
     * </table>
     * A single ':' is a legal terminal, but the sequence "::" must be followed
     * by "=", or "[ERROR]" is returned.
     * 
     * @return The next token.
     * @throws IllegalStateException If called without setting a string to
     *         be tokenized.
     */
    public String nextToken() throws IllegalStateException {
        int currentToken = 0;
        String BnfToken = "NO TOKEN";
        State state = State.INITIAL;
        
        if (tokenizer == null) {
            throw new IllegalStateException("BnfTokenizer was not given anything to tokenize!");
        }
        
        while (true) {
            try { currentToken = tokenizer.nextToken(); }
            catch (IOException e) { throw new Error(e); } // Should never happen
            
            if (currentToken == StreamTokenizer.TT_EOF) return "EOF";
            switch (state) {
                case INITIAL:
                    if (currentToken == '<') {
                        BnfToken = "<";
                        state = State.IN_NONTERMINAL;
                    }
                    else if (currentToken == ':') {
                        state = State.AFTER_COLON;
                    }
                    else {
                        // stay in initial state
                        return toString(currentToken);
                    }
                    break;
                case IN_NONTERMINAL:
                    if (currentToken == '>') {
                        BnfToken += ">";
                        state = State.INITIAL;
                        return BnfToken;
                    }
                    if (BnfToken.length() > 1) BnfToken += " ";
                    BnfToken += toString(currentToken);
                    // stay in this state                        
                    break;
                case AFTER_COLON:
                    if (currentToken == ':') {
                        state = State.AFTER_COLON_COLON;
                        break;
                    }
                    tokenizer.pushBack();
                    return ":";
                case AFTER_COLON_COLON:
                    if (currentToken == '=') {
                        state = State.INITIAL;
                        return "::=";
                    }
                    return "[ERROR]";
            }
        }
    }
    
    private String toString(int currentToken) {
        switch (currentToken) {
            case StreamTokenizer.TT_WORD: return tokenizer.sval;
            case StreamTokenizer.TT_NUMBER: return tokenizer.nval + "";
            case StreamTokenizer.TT_EOL: return "\n";
            case StreamTokenizer.TT_EOF: return "[END OF FILE]";
            default: 
                if (currentToken == '"') return tokenizer.sval;
                return "" + (char)currentToken;
        }
    }
}