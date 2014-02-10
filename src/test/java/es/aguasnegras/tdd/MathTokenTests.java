package es.aguasnegras.tdd;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by aventura on 10/02/14.
 */
public class MathTokenTests {

    @Test
    public void checkIsOperatorTrue() {
        MathToken token = new MathToken("*");
        assertTrue(token.isOperator());
    }

    @Test
    public void checkIsOperatorFalse() {
        MathToken token = new MathToken("22");
        assertFalse(token.isOperator());
    }
}
