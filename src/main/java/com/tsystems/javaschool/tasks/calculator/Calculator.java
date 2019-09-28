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
        if(statement == null || statement.isEmpty()){
            return null;
        }else{
            return answerFormatting(getAnswer(conversionToReversePolishNotation(statement)));
        }
    }

    private String conversionToReversePolishNotation(String statement) {
        Stack<Character> characterStack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for(char ch: statement.toCharArray()) {

            if(isNumeralOrDot(ch)){
                sb.append(ch);
            }else if(ch == ','){
                return null;
            }else if(ch == '('){
                sb.append(' ');
                characterStack.push(ch);
            }else if(ch == ')'){
                sb.append(' ');
                if(!characterStack.isEmpty()){
                    char tempCh = characterStack.pop();
                    while(tempCh != '(' && !characterStack.isEmpty()){
                        sb.append(tempCh);
                        sb.append(' ');
                        tempCh = characterStack.pop();
                    }
                    if(tempCh != '(' && characterStack.isEmpty()) return null;
                }else return null;
            }else if(isOperation(ch)){
                sb.append(' ');
                if(characterStack.isEmpty()){
                    characterStack.push(ch);
                }else{
                    char tempCh = characterStack.pop();


                    while (isFirstCharPrioritySecondChar(tempCh, ch)){
                        sb.append(tempCh);
                        sb.append(' ');
                        if(!characterStack.isEmpty()){
                            tempCh = characterStack.pop();
                        }else break;
                    }

                    if(!isFirstCharPrioritySecondChar(tempCh, ch)) characterStack.push(tempCh);

                    characterStack.push(ch);
                }
            }
        }

        char tempCh;
        do{
            tempCh = characterStack.pop();
            if(tempCh == '(') return null;
            sb.append(' ');
            sb.append(tempCh);
        }while(!characterStack.isEmpty());

        return sb.toString();
    }

    private Double getAnswer(String reversePolishNotation){
        if(reversePolishNotation == null) return null;
        Stack<Double> doublesStack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for(char ch: reversePolishNotation.toCharArray()){
            if(isNumeralOrDot(ch)){
                sb.append(ch);
            }else if(ch == ' ' && sb.length() != 0){
                try {
                    doublesStack.push(Double.valueOf(sb.toString()));
                    sb = new StringBuilder();
                }catch (Throwable t){
                    return null;
                }
            }else if(isOperation(ch)){
                if(doublesStack.size() < 2) return null;
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

    private boolean isFirstCharPrioritySecondChar(char first, char second){
        if(first == second) return false;
        if((first == '/') || (first == '*' && second != '/'))return true;
        if(first == '-' && second == '+') return true;
        return false;
    }

    private String answerFormatting(Double value){
        if (value == null)
            return null;
        if (value == value.intValue())
            return String.valueOf(value.intValue());
        return String.valueOf(value);
    }

}










