package es.aguasnegras.tdd.calculator.lexer;

import java.util.List;

/**
 * Created by aventura on 10/02/14.
 */
public interface Lexer {
    List<MathToken> getTokens(String expression);

    List<String> getExpressions(String s);
}
