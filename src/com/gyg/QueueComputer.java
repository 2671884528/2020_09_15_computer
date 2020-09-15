package com.gyg;

import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @auther 郭永钢
 * @data 2020/9/15 9:39
 * @desc: 队列实现计算器
 * 32+7-5*2
 */

public class QueueComputer {

    public static void main(String[] args) {
        System.out.println(copmputer("32+71-5*2-4/1"));
    }

    /*只负责计算，不负责验证表达式是否正确，默认就是正确的。验证由其他方法完成*/
    static Float copmputer(String s) {
        String patten = "\\d+";
        String pattenSymbol = "[/+-/*\\/]";

        Pattern compile = Pattern.compile(patten);
        Pattern compileSymbol = Pattern.compile(pattenSymbol);

        Matcher matcher = compile.matcher(s);
        Matcher matcherSymbol = compileSymbol.matcher(s);

        ArrayList<String> resultList = new ArrayList<>();

        while (matcher.find()) {
            resultList.add(matcher.group());
            if (matcherSymbol.find()) {
                resultList.add(matcherSymbol.group());
            }
        }
//        resultList.forEach(System.out::println);
        Stack<Float> stackNum = new Stack<>();
        Stack<String> stackSymbol = new Stack<>();
        Float result = null;
        for (String str : resultList) {

            try {
                stackNum.push(Float.parseFloat(str));

            } catch (Exception e) {
                if (stackSymbol.isEmpty()) {
                    stackSymbol.push(str);
                } else {
                    String peek = stackSymbol.peek();
                    while (!((peek.equals("+") || peek.equals("-")) && (str.equals("*") || str.equals("/")))) {
                        stackNum.push(computerSymbool(stackNum, stackSymbol, result));
                        if (stackSymbol.isEmpty()) {
                            break;
                        }
                        peek = stackSymbol.peek();
                    }
                    stackSymbol.push(str);
//                    String peek = stackSymbol.peek();
//                    if ((peek.equals("+") || peek.equals("-")) && (str.equals("*") || str.equals("/"))) {
//                        stackSymbol.push(str);
//                    } else {
//                        peek = stackSymbol.peek();
//                        while (!(peek.equals("+") || peek.equals("-")) && (str.equals("*") || str.equals("/"))) {
//                            stackNum.push(computerSymbool(stackNum, stackSymbol, result));
//                        }
//                        stackSymbol.push(str);
//                    }
                }
//                System.out.println(e.getMessage());
//                System.out.println("wdwadw");

            }
            if (str == resultList.get(resultList.size() - 1)) {

                while (!stackSymbol.isEmpty()) {
                    result = computerSymbool(stackNum, stackSymbol, result);
                    stackNum.push(result);
                }
            }
        }
        return result;
    }

    /*操作符运算*/
    static Float computerSymbool(Stack<Float> stackNum, Stack<String> stackSymbol, Float result) {
        Float sonFloat = stackNum.pop();
        Float parentFloat = stackNum.pop();
        String symbol = stackSymbol.pop();
        switch (symbol) {
            case "+":
                result = parentFloat + sonFloat;
                break;
            case "-":
                result = parentFloat - sonFloat;
                break;
            case "*":
                result = parentFloat * sonFloat;
                break;
            case "/":
                result = parentFloat / sonFloat;
                break;
        }
        return result;
    }
}
