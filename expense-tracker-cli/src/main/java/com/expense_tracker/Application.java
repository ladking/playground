package com.expense_tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {
    public static void main(String[] args){
        CliParser parser = new CliParser();
        parser.handleCommand(args);
        Scanner inputReader = new Scanner(System.in);
        boolean shouldProceed = true;
        while(shouldProceed){
            System.out.print("Enter Command >> ");
            String cmd = inputReader.nextLine();
            if(cmd.equals("exit")){
                System.out.println("Exiting expense tracker");
                shouldProceed = false;
                continue;
            }
            String[] arg = parse(cmd);
            parser.handleCommand(arg);
        }
        inputReader.close();
    }

    public static String[] parse(String input) {
        List<String> tokens = new ArrayList<>();
        // Matches double-quoted strings, single-quoted strings, or non-whitespace tokens
        Matcher matcher = Pattern.compile("\"([^\"]*)\"|'([^']*)'|([^\\s]+)").matcher(input);
        
        while (matcher.find()) {
            if (matcher.group(1) != null) {
                tokens.add(matcher.group(1)); // Inside double quotes
            } else if (matcher.group(2) != null) {
                tokens.add(matcher.group(2)); // Inside single quotes
            } else {
                tokens.add(matcher.group(3)); // Unquoted word
            }
        }
        
        return tokens.toArray(new String[0]);
    }
}