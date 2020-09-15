package com.gyg;

import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Stack<String> stack = new Stack<>();
        stack.push("+");
        stack.push("-");
        stack.push("*");
        stack.push("/");

        System.out.println(stack.peek()=="*"||stack.peek()=="/");

        String x = "*";
        System.out.println((x=="*"||x=="/"));
    }
}
