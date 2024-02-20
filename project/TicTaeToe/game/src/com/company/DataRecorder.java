package com.company;

import java.io.*;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Created by Adrian Ng, 23/4/2020, 21:18pm
 */

public class DataRecorder {

    private static File file; //the file which record data
    public static String fileName = "data.txt"; //the file name

    public static void loadData() { //a method that load data file
        file = new File(fileName); //create a new file var
        if (!file.exists()) { //if the file is not exists, then generate one, it may throw IOException
            try {
                file.createNewFile(); //create new file, it returns boolean but we are gonna ignore it
            } catch (IOException e) {
                System.out.println("Cannot create file " + fileName + " for reason " + e.getMessage() + ", please try again later."); //error message
                System.exit(0); //quit game
            }
        }
    }

    public static boolean containsUsername(String username) { //a method that check if the username exists in data file or not, if yes, then returns true, else false
        try {
            List<String> dataInString = Files.readAllLines(file.toPath()); //An list that contains all lines of the file
            for (String lines : dataInString) { //loop though all the string
                if (lines.split(";").length != 2) { //if the length of the split string is not equals to 2 then continue the loop without going further down
                    continue;
                }
                if (lines.split(";")[0].equalsIgnoreCase(username)) { //if the first split string contains the username then return true, else false
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void register(String username) { //a method that register the username
        try {
            FileWriter fw = new FileWriter(file, true); //FileWriter to prepare to write the file
            BufferedWriter bw = new BufferedWriter(fw); //BufferedWriter to support FileWriter

            bw.write(username + ";0\r\n"); //write line

            bw.close(); //close the BufferedWriter, save
        } catch (IOException e) { //May cause IOException, so catch it
            e.printStackTrace();
        }
    }

    public static String findPlayedTimes(String username) { //a method that find how many times does the player played
        try {
            FileReader fr = new FileReader(file); //FileReader to prepare to read the file
            BufferedReader br = new BufferedReader(fr); //BufferedReader to support FileReader

            for (String data : br.lines().collect(Collectors.toList())) { //Loop though all lines of the data file
                if (data.split(";")[0].equals(username)) { //if the first split string equals to the username, then return the second split string
                    return data.split(";")[1];
                }
            }

            br.close(); //close the BufferedReader
        } catch (IOException e) { //May cause IOException, so catch it
            e.printStackTrace();
        }

        return "";
    }

    public static void addPlayTimes(String username) { //a method that add play time to a username
        try {
            FileReader fr = new FileReader(file); //FileReader to prepare to read the file
            BufferedReader br = new BufferedReader(fr); //BufferedReader to support FileReader

            List<String> dataList = br.lines().collect(Collectors.toList()); //All lines of the data
            int times = 0; //a var that get how many play times does the username have, then we have just simply +1 to the play times

            Iterator<String> dataIterator = dataList.iterator(); //A iterator because we have to remove something in a list
            while (dataIterator.hasNext()) { //continue to loop if the iterator has next string
                String data = dataIterator.next(); //get next line of dataIterator
                if (data.split(";")[0].equals(username)) { //if the first split string is username, then remove the data from the data file
                    times = Integer.parseInt(data.split(";")[1]);
                    dataIterator.remove();
                }
            }

            dataList.add(username + ";" + (times + 1)); //add back the data with +1 in it

            FileWriter fw = new FileWriter(file); //FileWriter to prepare to write the file
            BufferedWriter bw = new BufferedWriter(fw); //BufferedWriter to support FileWriter

            for (String data : dataList) { //Loop though the dataList
                bw.write(data + "\r\n"); //put those string back into the file
            }

            bw.close(); //Close the BufferedWriter
        } catch (IOException e) { //May cause IOException, so catch it
            e.printStackTrace();
        }
    }
}
