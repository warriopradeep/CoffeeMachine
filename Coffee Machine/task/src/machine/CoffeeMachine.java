package machine;

import java.util.Scanner;

public class CoffeeMachine {

    private int waterAva, milkAva, coffeeBeanAva, money, disposableCups;

    CoffeeMachine() {
        setInitialState();
        action();
    }

    private int getInput() {
        Scanner in = new Scanner(System.in);

        System.out.println("Write how many ml of water the coffee machine has:");
        waterAva = in.nextInt();
        System.out.println("Write how many ml of milk the coffee machine has:");
        milkAva = in.nextInt();
        System.out.println("Write how many grams of coffee beans the coffee machine has:");
        coffeeBeanAva = in.nextInt();
        System.out.println("Write how many cups of coffee you will need:");
        return in.nextInt();
    }

    private void setInitialState() {
        waterAva = 400;
        milkAva = 540;
        coffeeBeanAva = 120;
        disposableCups = 9;
        money = 550;
    }

    private void printMachineState() {
        System.out.println();
        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water%n", waterAva);
        System.out.printf("%d ml of milk%n", milkAva);
        System.out.printf("%d g of coffee beans%n", coffeeBeanAva);
        System.out.printf("%d disposable cups%n", disposableCups);
        System.out.printf("$%d of money%n%n", money);
    }

    private void action() {

        Scanner in = new Scanner(System.in);

        while (true){
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String action = in.nextLine();

            switch (action) {
                case "buy" -> buy();
                case "fill" -> fill();
                case "take" -> take();
                case "remaining" -> printMachineState();
                case "exit" -> {
                    return;
                }
            }
        }
    }

    private void buy() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        Scanner in = new Scanner(System.in);
        String option = in.nextLine();

        switch (option) {
            case "1" -> {
                if (checkEnoughResources(250, 0, 16, 1)) {
                    System.out.println("I have enough resources, making you a coffee!\n");
                    updateAvailableResources(250, 0, 16, 4);
                } else {
                    System.out.printf("Sorry, not enough %s!%n%n", notEnough(250, 0, 16));
                }
            }
            case "2" -> {
                if (checkEnoughResources(350, 75, 20, 1)) {
                    System.out.println("I have enough resources, making you a coffee!\n");
                    updateAvailableResources(350, 75, 20, 7);
                } else {
                    System.out.printf("Sorry, not enough %s!%n%n", notEnough(350, 75, 20));
                }

            }
            case "3" -> {
                if (checkEnoughResources(200, 100, 12, 1)) {
                    System.out.println("I have enough resources, making you a coffee!\n");
                    updateAvailableResources(200, 100, 12, 6);
                } else {
                    System.out.printf("Sorry, not enough %s!%n%n", notEnough(200, 100, 12));
                }

            }
            case "back" -> {return;}
        }
    }

    private void fill() {
        Scanner in = new Scanner(System.in);

        System.out.println("Write how many ml of water you want to add:");
        waterAva += in.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        milkAva += in.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        coffeeBeanAva += in.nextInt();
        System.out.println("Write how many disposable cups you want to add:");
        disposableCups += in.nextInt();
    }

    private void take() {
        System.out.printf("I gave you $%d%n%n", money);
        money = 0;
    }

    private void calculateIngredientAmounts(int c) {

        int nCups = maxCupsPossible();

        if (nCups < c) {
            System.out.printf("No, I can make only %d cup(s) of coffee%n", maxCupsPossible());
        } else {
            if (nCups - c == 0) System.out.println("Yes, I can make that amount of coffee");
            else System.out.printf("Yes, I can make that amount of coffee (and even %d more than that)", maxCupsPossible() - c);
        }
    }

    private int maxCupsPossible() {

        int maxCupsW = waterAva / 200;
        int maxCupsM = milkAva / 50;
        int maxCupsC = coffeeBeanAva / 15;

        return Math.min(maxCupsW, Math.min(maxCupsM, maxCupsC));
    }

    private boolean checkEnoughResources(int water, int milk, int coffee, int dispCups) {
        return waterAva >= water && milkAva >= milk && coffeeBeanAva >= coffee && disposableCups >= dispCups;
    }

    private void updateAvailableResources(int water, int milk, int coffee, int cost) {
        waterAva -= water;
        milkAva -= milk;
        coffeeBeanAva -= coffee;
        money += cost;
        disposableCups -= 1;
    }

    private String notEnough(int water, int milk, int coffee) {
        if (waterAva < water) {
            return "water";
        } else if (milkAva < milk) {
            return "milk";
        } else if (coffeeBeanAva < coffee) {
            return "coffee";
        } else return "cups";
    }
    public static void main(String[] args) {
        CoffeeMachine c = new CoffeeMachine();
    }

}
