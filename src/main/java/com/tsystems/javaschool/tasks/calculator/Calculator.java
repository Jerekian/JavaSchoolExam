package com.tsystems.javaschool.tasks.calculator;

import java.util.Scanner;
import java.util.Stack;

public class Calculator {

    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */
    public String evaluate(String statement) {
        // TODO: Implement the logic here

        if(statement.isEmpty() || statement == null){
            return null;
        }else{
            return answerFormatting(getAnswer(conversionToReversePolishNotation(statement)));
        }
    }

    private String conversionToReversePolishNotation(String statement){
        Stack<Character> characterStack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for(char ch: statement.toCharArray()){
            if(isNumeralOrDot(ch)){
                sb.append(ch);
            }else if(isOperation(ch) || ch == '('){
                sb.append(' ');
                characterStack.push(ch);
            }else if(ch == ')'){
                sb.append(' ');
                char tempCh = characterStack.pop();
                while(tempCh != '(' && !characterStack.isEmpty()){
                    sb.append(tempCh);
                    sb.append(' ');
                    tempCh = characterStack.pop();
                }
                if(characterStack.isEmpty()) return null;
            }
        }

        char tempCh;
        do{
            tempCh = characterStack.pop();
            if(tempCh == '(') return null;
            sb.append(' ');
            sb.append(tempCh);
        }while(!characterStack.isEmpty());
        /*
        char tempCh = characterStack.pop();
        while(!characterStack.isEmpty()){
            if(tempCh == '(') return null;
            sb.append(tempCh);
            sb.append(' ');
            tempCh = characterStack.pop();
        }*/
        return sb.toString();
    }

    private Double getAnswer(String reversePolishNotation){
        Stack<Double> doublesStack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for(char ch: reversePolishNotation.toCharArray()){
            if(isNumeralOrDot(ch)){
                sb.append(ch);
            }else if(ch == ' ' && sb.length() != 0){
                doublesStack.push(Double.valueOf(sb.toString()));
                sb = new StringBuilder();
            }else if(isOperation(ch)){
                Double result = calculate(doublesStack.pop(), doublesStack.pop(), ch);
                if(result == null) return  null;
                doublesStack.push(result);
            }
        }
        return doublesStack.pop();
    }

    private Double calculate(double b, double a, char operation){
        switch (operation){
            case '+': return a+b;
            case '-': return a-b;
            case '*': return a*b;
            case '/': return b == 0 ? null : a/b;
        }
        return null;
    }

    private boolean isNumeralOrDot(char ch){
        return ch == '.' || (ch >= '0' && ch <= '9');
    }

    private boolean isOperation(char ch){
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    private String answerFormatting(Double value){
        if (value == null)
            return null;
        if (value == value.intValue())
            return String.valueOf(value.intValue());
        return null;
    }

}










