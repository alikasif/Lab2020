package google2022;

import java.util.Stack;

public class SimplifyFilePath {

    public static void main(String[] args) {

//        String path = "/a/./b/../../c/";
        String path = "/a/./b/../..";

        Stack<Character> stack = new Stack<>();

        StringBuilder sbl = new StringBuilder();

        Character prevChar = Character.valueOf(path.charAt(0));
        sbl.append(prevChar);
        stack.push(prevChar);

        for(int i=1;i<path.length();i++) {

            Character character = Character.valueOf(path.charAt(i));

            if (stack.isEmpty()) {
                stack.push(character);
            }
            else if (i == path.length()-1 && character.charValue() == '/')
                continue;
            else if (character.charValue() == '/' && stack.peek().charValue() == '/') {
                continue;
            }
            else if (character.charValue() == '/' && stack.peek().charValue() == '.') {
                stack.pop();
            }
            else if (character.charValue() == '.' && stack.peek().charValue() == '.') {
                stack.pop();
                popUntilParent(stack);
            }
            else if (character.charValue() == '.' && stack.peek().charValue() == '/') {
                stack.push(character);
            }
            else {
                stack.push(character);
            }
        }
        System.out.println(stack);
    }

    private static void popUntilParent(Stack<Character> stack) {
        System.out.println("input: "+ stack);
        int i=0;

        while ( !stack.isEmpty()) {

            if(stack.peek().charValue() == '/' || stack.peek().charValue() == '.') {
                stack.pop();
            }
            else {
                stack.pop();
                break;
            }
        }

        while ( !stack.isEmpty()) {

            if(stack.peek().charValue() == '/' || stack.peek().charValue() == '.') {
                stack.pop();
            }
            else {
                break;
            }
        }
        System.out.println("output: "+ stack);
    }

    private static boolean ispathseperator(Character character) {
        return character.charValue() == '/' || character.charValue() == '.';
    }
}
