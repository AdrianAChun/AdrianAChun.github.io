package com.company.game;

import com.company.DataRecorder;
import com.company.Main;
import com.company.enums.ElementsData;
import com.company.enums.GameType;

import java.util.Random;

/*
 * Created by Matthew, 23/4/2020 , 17:14
 */
public class Game {

    private final GameType gameType; //The Type of the game, can be PlayerVSPlayer and PlayerVSComputer
    private final ElementsData player1; //The element data of player 1, usually used to get the symbol of the element
    private final ElementsData player2; //The element data of player 2, usually used to get the symbol of the element. If gameType is PlayerVSComputer, then player2 will be computer
    private String winner = ""; //Winner name
    private String player = ""; //The current round of the player, should only be Player 1, Player 2, Computer

    //2D arrays of the Tic Tac Hoe table
    //Put all slot with NOTHING, because it should be NOTHING
    public ElementsData[][] elementsDatas = {
            {ElementsData.NOTHING, ElementsData.NOTHING, ElementsData.NOTHING},
            {ElementsData.NOTHING, ElementsData.NOTHING, ElementsData.NOTHING},
            {ElementsData.NOTHING, ElementsData.NOTHING, ElementsData.NOTHING}
    };

    //Create a game
    public Game(GameType gameType, ElementsData player1, ElementsData player2) {
        this.gameType = gameType; //set gameType to gameType
        this.player1 = player1; //set player1 to player1
        this.player2 = player2; //set player2 to player2

        System.out.println("TIPS: When you choose a spot, just simply type the spot number :D");
        graphTicTacHoeTable();

        Main.sleep(3000); //Make the game stop for 3 seconds, so player can watch the tips maybe!?

        int random = new Random().nextInt(2); //generate a number whoch between 0 and 1
        player = random == 0 ? "Player 1" : gameType == GameType.PlayerVSPlayer ? "Player 2" : "Computer"; //Select
        System.out.println("Rolling dice... It is " + player + "'s turn!"); //Telling the player the random's result

        while (!checkWins() && !checkDraw()) { //if no one wins, or those 9 spot are NOTHING, then loop
            System.out.println(player + ", please select a spot!"); //Tell player to select a spot
            int choice = 0;
            do {
                if (player.equals("Computer") && gameType == GameType.PlayerVSComputer) { //Check if current round player is a computer or not, if yes, then random generate a number, if no, then let current round player to choose a number
                    System.out.println("Computer is thinking..."); //Tell player that computer is thinking
                    Main.sleep(1000); //Stop game for 1 second
                    choice = computerChoice(); //let computer to generate a number
                } else {
                    String select = Main.scanner.next(); //Let player to input a number
                    if (Main.isInt(select)) { //Check if it is an int
                        choice = Integer.parseInt(select); //Convert the string into int
                    } else {
                        System.out.println(select + " is not a number, please try it again."); //Tell player it is not a number
                        System.out.println(" ");
                    }
                }
                if (1 > choice || choice > 9) { //Check if it is less than 1 or more than 9
                    System.out.println("Invalid number, please try it again."); //Tell player that it is not a vaild number
                    System.out.println(" ");
                } else {
                    int slotDummy = choice - 1; //Because of an array starts with 0, so -1 first
                    int xSlot = (int) Math.floor((double) slotDummy / 3); //get x slot with
                    int ySlot = slotDummy % 3; //get y slot

                    if (elementsDatas[xSlot][ySlot] != ElementsData.NOTHING) { //Check if the position is already chosen or not
                        System.out.println("It is already been chosen! Please try again!"); //Tell player that the number is already chosen
                        choice = 0; //reset
                    } else {
                        if (player.equals("Player 1")) { //If current player is player 1
                            elementsDatas[xSlot][ySlot] = player1; //Then set that slot to player 1's element
                        } else if (player.equals("Player 2") || player.equals("Computer")) { //else if current player is player 2 or computer
                            elementsDatas[xSlot][ySlot] = player2; //Then set that slot to player 2's element
                        }

                        System.out.println(player + " chose spot " + choice + "! Current graph:"); //Tell output
                        graphTicTacHoeTable(); //Graph the table, output result table
                        if (!checkWins()) { //If it is not win
                            flipPlayerSide(); //Then flip the side of player
                        }
                    }
                }
            } while (1 > choice || choice > 9); //If choice is lower than 1 or higher than 9, then loop
        }

        if (checkWins()) { //If someone already wins
            System.out.println(winner + " win!"); //Output who wins
            DataRecorder.addPlayTimes(Main.username); //Add played times to that player
        } else if (checkDraw()) { //If all 9 spots is not NOTHING
            System.out.println("Draw! Restarting the game!"); //Output the game is draw
            Main.sleep(1000); //Stop the game for 1 seconds
            new Game(gameType, player1, player2); //Restart the game
        }
        Main.sleep(3000); //Stop 3 seconds
    }

    public GameType getGameType() { //Get the gametype of the game
        return gameType;
    }

    public boolean checkWins() { //Check if someone wins or not
        //The reason why I use ElementsData.match instead of == is because match will also check if those two elements included NOTHING, if yes, the return false
        for (int x = 0; x <= 2; x++) { //loop though 0-2
            if (ElementsData.match(elementsDatas[x][0], elementsDatas[x][1]) && ElementsData.match(elementsDatas[x][0], elementsDatas[x][2])) { //Check x axis position
                winner = elementsDatas[x][0] == player1 ? "Player 1" : getGameType() == GameType.PlayerVSPlayer ? "Player 2" : "Computer"; //set winner
                return true;
            }
            if (ElementsData.match(elementsDatas[0][x], elementsDatas[1][x]) && ElementsData.match(elementsDatas[0][x], elementsDatas[2][x])) { //Check y axis position
                winner = elementsDatas[0][x] == player1 ? "Player 1" : getGameType() == GameType.PlayerVSPlayer ? "Player 2" : "Computer";
                return true;
            }
        }
        if (ElementsData.match(elementsDatas[0][0], elementsDatas[1][1]) && ElementsData.match(elementsDatas[0][0], elementsDatas[2][2])) { //check slash
            winner = elementsDatas[0][0] == player1 ? "Player 1" : getGameType() == GameType.PlayerVSPlayer ? "Player 2" : "Computer";
            return true;
        } else if (ElementsData.match(elementsDatas[0][2], elementsDatas[1][1]) && ElementsData.match(elementsDatas[0][2], elementsDatas[2][0])) { //check slash
            winner = elementsDatas[0][2] == player1 ? "Player 1" : getGameType() == GameType.PlayerVSPlayer ? "Player 2" : "Computer";
            return true;
        }
        return false;
    }

    public boolean checkDraw() { //Check if the game already filled with X or O without NOTHING
        for (int x = 0; x <= 2; x++) { //Loop though x axis
            for (int y = 0; y <= 2; y++) { //Loop though y axis
                if (elementsDatas[x][y] == ElementsData.NOTHING) { //Check if the elements is NOTHING, return false
                    return false;
                }
            }
        }
        return true;
    }

    public void graphTicTacHoeTable() { //Graph the current Tic Tac Hoe table
        System.out.println(
                (elementsDatas[0][0] == ElementsData.NOTHING ? "1" : elementsDatas[0][0].getReadAble) + " | " +
                (elementsDatas[0][1] == ElementsData.NOTHING ? "2" : elementsDatas[0][1].getReadAble) + " | " +
                (elementsDatas[0][2] == ElementsData.NOTHING ? "3" : elementsDatas[0][2].getReadAble)
        );
        System.out.println("----------");
        System.out.println(
                (elementsDatas[1][0] == ElementsData.NOTHING ? "4" : elementsDatas[1][0].getReadAble) + " | " +
                (elementsDatas[1][1] == ElementsData.NOTHING ? "5" : elementsDatas[1][1].getReadAble) + " | " +
                (elementsDatas[1][2] == ElementsData.NOTHING ? "6" : elementsDatas[1][2].getReadAble)
        );
        System.out.println("----------");
        System.out.println(
                (elementsDatas[2][0] == ElementsData.NOTHING ? "7" : elementsDatas[2][0].getReadAble) + " | " +
                (elementsDatas[2][1] == ElementsData.NOTHING ? "8" : elementsDatas[2][1].getReadAble) + " | " +
                (elementsDatas[2][2] == ElementsData.NOTHING ? "9" : elementsDatas[2][2].getReadAble)
        );
    }

    public void flipPlayerSide() { //swap current player side
        if (player.equals("Player 1")) { //If player is equal to 'Player 1'
            player = (getGameType() == GameType.PlayerVSPlayer ? "Player 2" : "Computer"); //if gametype is PlayerVSPlayer, then set current player side to 'Player 2', else 'Computer'
        } else if (player.equals("Player 2") || player.equals("Computer")) { //If player is equal to 'Player 2' or 'Computer'
            player = "Player 1"; //Set current player side to 'Player 1'
        }
    }

    public int computerChoice() { //Generate a number which is computer's choice for the spot of the game
        int randomChoice, slotDummy, xSlot, ySlot; //create var

        do {
            randomChoice = new Random().nextInt(9) + 1; //set randomChoice to 1-9
            slotDummy = randomChoice - 1; //make randomChoice -1
            xSlot = (int) Math.floor((double) slotDummy / 3); //get x axis number
            ySlot = slotDummy % 3; //get y axis number
        } while (elementsDatas[xSlot][ySlot] != ElementsData.NOTHING); //if the number spot that computer generated is not NOTHING, then continue to loop

        return randomChoice; //return the number which computer chose
    }

}
