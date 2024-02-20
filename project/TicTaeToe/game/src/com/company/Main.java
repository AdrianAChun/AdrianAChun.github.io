/*
 * Matthew, Adrian
 * 22/4/2020
 * Tic Tac Hoe
 * The main class of the game, Tic Tac Hoe
 */
package com.company;

import com.company.enums.ElementsData;
import com.company.enums.GameType;
import com.company.game.Game;

import java.util.Scanner;

/*
 * Created by Matthew Yu, 22/4/2020, 14:38
 */
 
public class Main {

    public static String username = ""; //The player's username, will be used to record data and the welcome message
    public static int gameMode = 0;
    /* Gamemode with int
     * 1 = player vs computer
     * 2 = player vs player
     * 3 = check how many games the player played (Used FileIO here)
     * 4 = Exit the game
     */
    public static Scanner scanner = new Scanner(System.in); //Read what user typed in console

    //Program starts here
    public static void main(String[] args) {
        System.out.println("Welcome to Tic Tac Hoe!"); //Welcome message
        System.out.println("By Adrian and Matthew"); //Welcome message again
        DataRecorder.loadData(); //Load data file
        sleep(1000); //1 second delay

        boolean logged = false; //check log in, if player has username typed, then true, else false
        do {
            System.out.println(" ");
            System.out.println("What's your username?"); //Ask for username
            username = scanner.next(); //Let user type his username
            if (username.isEmpty()) { //Check if username is empty or not
                System.out.println("Username cannot be empty!");
            } else if (username.contains(";")) { //Check if username has ';', because we will use ';' to split the String to Arrays
                System.out.println("Invalid username! Please try again!");
            } else {
                logged = true; //set logged in equals to true so the loop can be quited
            }
        } while (!logged); //If logged is false, continue to loop

        if (!DataRecorder.containsUsername(username)) { //If the data file didn't have that username, register that user
            DataRecorder.register(username);
        }

        while (gameMode != 4) { //if gamemode is not equals to 4 then continue to loop, it is 4 because 4 will exit the program
            do {
                sendSelect(); //Ask fo what gamemode does the user wants to play/do
                String select = scanner.next(); //Let player to type what gamemode he wants
                if (isInt(select)) { //Check if it is int or not
                    gameMode = Integer.parseInt(select); //Set the gamemode
                } else {
                    //Will do this part when it is not a int aka number
                    System.out.println(select + " is not a number, please try it again.");
                    System.out.println(" ");
                }
                if (1 > gameMode || gameMode > 4) { //check if it is less than 1 or bigger than 4
                    System.out.println("Invalid number, please try it again.");
                    System.out.println(" ");
                }
            } while (1 > gameMode || gameMode > 4); //Continue to loop when it is less than 1 or bigger than 4

            if (gameMode == 1) { //If player chose gamemode 1, which is player vs computer
                ElementsData elementsData = ElementsData.NOTHING; //Create var, which will be player's symbol (X/O)
                do {
                    System.out.println("Select a symbol: (X/O)"); //Tell player to select a symbol
                    String selected = ""; //new String var which will store what the player selected
                    try {
                        selected = scanner.next(); //Let player to choose a symbol
                        elementsData = ElementsData.valueOf(selected); //Try to convert what player typed from String to ElementsData, if cannot, it throws IllegalArgumentException
                        if (elementsData == ElementsData.NOTHING) { //Check if the user typed 'NOTHING'
                            System.out.println("It cannot be 'NOTHING'! Try again!"); //Tell user that cannot use 'NOTHING' as the symbol
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("There's no symbol named " + selected + ", please try again."); //Tell player there's no symbol called 'selected'
                    }
                } while (elementsData == ElementsData.NOTHING); //Continue to loop when elementsData is NOTHING, which means it doesn't have a symbol

                new Game(GameType.PlayerVSComputer, elementsData, ElementsData.getFlipSide(elementsData)); //Start a game
            } else if (gameMode == 2) {
                ElementsData elementsData = ElementsData.NOTHING;
                do {
                    System.out.println("Player 1, Select a symbol: (X/O)"); //Create var, which will be player 1's symbol (X/O)
                    String selected = ""; //new String var which will store what the player selected
                    try {
                        selected = scanner.next(); //Let player to choose a symbol
                        elementsData = ElementsData.valueOf(selected); //Try to convert what player typed from String to ElementsData, if cannot, it throws IllegalArgumentException
                        if (elementsData == ElementsData.NOTHING) { //Check if the user typed 'NOTHING'
                            System.out.println("It cannot be 'NOTHING'! Try again!"); //Tell user that cannot use 'NOTHING' as the symbol
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("There's no symbol named " + selected + ", please try again."); //Tell player there's no symbol called 'selected'
                    }
                } while (elementsData == ElementsData.NOTHING); //Continue to loop when elementsData is NOTHING, which means it doesn't have a symbol

                new Game(GameType.PlayerVSPlayer, elementsData, ElementsData.getFlipSide(elementsData)); //Start a game
            } else if (gameMode == 3) {
                System.out.println("You played " + DataRecorder.findPlayedTimes(username) + " times in this game, good job!"); //Tell the player how
                sleep(1000); //Sleep for 1 second, and then back to the 1-4 thing
            } else { //If it become here, then it must be '4'
                System.out.println("Bye"); //Tell player 'bye' lol
                System.exit(0); //Exit the program
            }
        }
    }

    //This method will cause the game sleep for 'delay' long, which 1000 is 1 second
    public static void sleep(long delay) {
        try {
            Thread.sleep(delay); //make the current thread sleep, it may cause InterruptedException.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Send instructions message to player
    public static void sendSelect() {
        gameMode = 0; //reset gamemode
        System.out.println("Hello, " + username + "!");
        System.out.println("Type '1' and 'Enter' to enter a game with computer");
        System.out.println("Type '2' and 'Enter' to enter a game with a friend");
        System.out.println("Type '3' and 'Enter' to check your how many games you played!");
        System.out.println("Type '4' and 'Enter' exit the game");
    }

    //A method to check if the String is an int, if yes, return true, else false
    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s); //Try to convert the string into int, it will throws NumberFormatException if it is not an int.
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}