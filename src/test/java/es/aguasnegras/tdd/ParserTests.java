package es.aguasnegras.tdd;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by aventura on 5/02/14.
 */
public class ParserTests {

    @Test
    public void getTokens() {
        MathParser mathParser = new MathParser();
        List<MathToken> tokens = mathParser.getTokens("2 + 2");
        assertEquals(3, tokens.size());
        assertEquals("2", tokens.get(0).getValue());
        assertEquals("+", tokens.get(1).getValue());
        assertEquals("2", tokens.get(2).getValue());
    }
}
