package com.expense_tracker;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;


public class CliParser {
    List<expense> expenseList = new ArrayList<expense>();



   public void handleCommand(String[] args){
    if(args.length < 1){
        showHelp();
        return;
    }
    String cmd = args[0];
    switch (cmd) {
        case "add":
            String title = "";
            String description = "";
            Integer amount = 0;
            for(int i = 1; i < args.length; i+=2){
               switch(args[i]){
                case "--title":
                    title = args[i+1];
                    break;
                case "--description":
                    description = args[i+1];
                    break;
                case "--amount":
                    Integer parsedAmt = Integer.parseInt(args[i+1]);
                    amount = parsedAmt;
                    break;

                default:
                    System.out.printf("ERROR: invalid flag %s\n", args[i]);
                    showHelp();
                    return;
               }
            }

            if(title == "" || description == "" || amount == 0){
                System.out.println("ERROR: provide valid valuesfor flags");
                showHelp();
            }

            expense data = new expense(expenseList.size()+1, title, description, amount);
            try{
                handleAddExpense(data);
            }catch(Exception e){
                System.out.printf("ERROR: failed to add expense: %s", e.getMessage());
            }
            this.handleViewAllExpenses();
            break;
        case "view":
            handleViewAllExpenses();
            break;
        case "delete":
            if(args.length < 2){
                 System.out.println("ERROR: provide id of record to delete");
                 return;
            }
           String id = args[1];
           try{
                Integer parsedId = Integer.parseInt(id);
                List<expense> newList = new ArrayList<>();
                expense recordToDelete = null;
                for(expense exp : expenseList){
                        if(exp.id != parsedId){
                            newList.add(exp);
                        }else{
                            recordToDelete = exp;
                        }
                }
                expenseList = newList;
                System.out.printf("Record Deleted: %s\n", recordToDelete.title);
           }catch(Exception e){
                System.out.printf("ERROR: %s\n", e.getMessage());
                return;
           }
            break;
        case "summary":
            Integer totalExpense = 0;
            for(expense exp : expenseList){
                totalExpense += exp.amount;
            }
            System.out.printf("Total Expense: %s\n", totalExpense);
            break;
        case "help":
            showHelp();
            break;
        default:
            System.out.println("recieved unknown command");
            showHelp();
            break;
    }

   }

   public void showHelp(){
    System.out.println("Expense Tracker CLI\nUsage: mvn exec:java [COMMAND] [...FLAGS]\nflags           description\n--title        title of expense made\n--description    description of expense made\n--amount       amount of expense");
   }


   public void handleAddExpense(expense payload)throws Exception{
        expenseList.add(payload);
   }
   public void handleViewAllExpenses(){
            System.out.println("id  Date            Title           Description             Amount");
        for(expense exp: expenseList){
            System.out.printf("%s   %s        %s          %s              %s\n",exp.id, exp.date, exp.title, exp.description, exp.amount);
        }
   }
   public void handleUpdateExpense(){}
   public void handleDeleteExpense(){}
   public void handleGetExpenseSummary(){}
   public void handleGetMonthExpenseSummary(){}


   class expense {
        public Integer id;
        public String title;
        public String description;
        public Integer amount;
        public LocalDate date;


        public expense(Integer id,String title,String description, Integer amount){
            this.id = id;
            this.title = title;
            this.description = description;
            this.amount = amount;
            this.date = LocalDate.now();
        }

   }
}

