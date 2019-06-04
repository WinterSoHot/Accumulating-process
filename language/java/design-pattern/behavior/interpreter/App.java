package gu.java.pattern.behavior.interpreter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

/**
 * @author gudongxian
 * @create 2018-05-03 上午9:25
 **/
public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        //逆波兰式
        String tokenString = "4 3 2 - 1 + *";
        Stack<Expression> stack = new Stack<>();
        String[] tokenList = tokenString.split(" ");
        for (String s : tokenList) {
            if (isOperation(s)) {
                Expression rightExpression = stack.pop();
                Expression leftExpression = stack.pop();
                LOGGER.info("popped from stack left:{} right:{}", leftExpression
                        , rightExpression);
                Expression operator = getOperatorInstance(s, leftExpression, rightExpression);
                LOGGER.info("operator: {}", operator);
                int result = operator.interpret();
                NumberExpression numberExpression = new NumberExpression(result);
                stack.push(numberExpression);
                LOGGER.info("push result to stack： {}", numberExpression.interpret());

            } else {
                Expression i = new NumberExpression(s);
                stack.push(i);
                LOGGER.info("push to stack : {}", i.interpret());
            }
        }
    }

    public static boolean isOperation(String s) {
        return s.equals("+") | s.equals("-") || s.equals("*");
    }

    public static Expression getOperatorInstance(String s, Expression leftExpression, Expression rightExpression) {
        switch (s) {
            case "+":
                return new PlusExpression(leftExpression, rightExpression);
            case "*":
                return new MultiplyExpression(leftExpression, rightExpression);
            case "-":
                return new MinusExpression(leftExpression, rightExpression);
            default:
                return new MultiplyExpression(leftExpression, rightExpression);


        }
    }
}
