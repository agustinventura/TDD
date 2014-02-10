package es.aguasnegras.tdd;

import java.util.List;

/**
 * Created by aventura on 10/02/14.
 */
public interface Lexer {
    List<MathToken> getTokens(String expression);
}
