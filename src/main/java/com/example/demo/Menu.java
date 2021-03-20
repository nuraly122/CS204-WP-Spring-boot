package com.example.demo;

import com.example.demo.model.Room;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    Scanner input = new Scanner(System.in);
    Room[] myHotel = new Room[11];

    static String choice;
    static String roomName;
    static int roomNum = 1;
    static String answer;

    Queue queueObj = new Queue(); //Creating an object of the Queue class

    public void menu() {
        System.out.println("======================================================");
        System.out.println("*            Hotel Management System                 *");
        System.out.println("======================================================");
        System.out.println("* V. View all the rooms                              *");
        System.out.println("* A. Add customer to room                            *");
        System.out.println("* E. Display Empty rooms                             *");
        System.out.println("* D. Delete customer from room                       *");
        System.out.println("* F. Find room from customer name                    *");
        System.out.println("* S. Store program array data into a text file       *");
        System.out.println("* Q. Quit Program                                    *");
        System.out.println("======================================================");
        System.out.println("");

        System.out.println("Choose one of the options from above. (E.g: Type 'V' to view all the rooms)");

        do {
            System.out.println();
            System.out.print("Choice : ");
            choice = input.next();
            String selection = choice.toLowerCase(); //This will convert the input value to lowercase. this will help avoid case sensitive issues.

            switch (selection) {
                case "v":
                    viewRooms();
                    break;
                case "a":
                    addCustomer();
                    break;
                case "e":
                    displayEmptyRooms();
                    break;
                case "d":
                    deleteCustomer();
                    break;
                case "f":
                    findRoom();
                    break;
                case "s":
                    storeData();
                    break;
                case "q":
                    System.out.println("Thanks");
                    break;
                default:
                    System.out.println("Invalid input! Please Enter one of these letters: V,A,E,D,F,S,Q");
            }
        }
        while (!(choice.equalsIgnoreCase("v") || choice.equalsIgnoreCase("a") || choice.equalsIgnoreCase("e") || choice.equalsIgnoreCase("d") ||
                choice.equalsIgnoreCase("f") || choice.equalsIgnoreCase("s") || choice.equalsIgnoreCase("q"))); //condition to only let valid range of inputs through.
    }

    public void initialize() {
        for (int x = 1; x < 11; x++) {
            myHotel[x] = new Room(); //Creating 10 Room Objects
        }
    }

    public void viewRooms() {
        for (int x = 1; x < 11; x++) {
            //This will display the room number and the current owner's name
            if (!myHotel[x].getName().equals("e")) {
                System.out.println("Room No. " + x + " occupied by " + myHotel[x].getName());
                //This will display the rooms which are currently Empty
            } else {
                System.out.println("Room No. " + x + " is empty");
            }
        }
        menu();
    }

    public void addCustomer() {
        boolean invalidRoomNumber; //Declaration of a boolean variable.
        do {
            try {
                System.out.println("Enter room number (1-10)");
                roomNum = input.nextInt();
                //checks whether the room is already occupied or not
                if (!myHotel[roomNum].getName().equals("e")) {
                    invalidRoomNumber = true;
                    System.out.println("This room is occupied by: Mr. " + myHotel[roomNum].getName());
                    System.out.println("");
                    //checks whether the input is within the proper range
                } else if (roomNum >= 1 && roomNum < 11) {
                    invalidRoomNumber = false;
                    //Error message to be displayed
                } else {
                    invalidRoomNumber = true;
                    System.out.println("Invalid input! Please Enter a value between 1-10");
                    System.out.println("");
                }
                //if the input is out of the range of the hotel array this will catch it
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                invalidRoomNumber = true;
                System.out.println("Invalid input! Please Enter a value between 1-10");
                System.out.println("");
                //to deal with exceptions regarding null values
            } //to deal with inputs other than integers
            catch (InputMismatchException e) {
                invalidRoomNumber = true;
                System.out.println("Invalid input! Please Enter a value between 1-10");
                System.out.println("");
                input.next();
            }
        } while (invalidRoomNumber);
        System.out.println("Enter the name of the customer :");
        //Getting the customer's name
        roomName = input.next();
        //Setting the customer name
        myHotel[roomNum].setName(roomName);
        queueObj.addToQueue(roomName);

        //this will let you choose whether to add more data or not
        try {
            do {
                System.out.println("Do you want to continue adding records?(Y/N)");
                answer = input.next();
                String selection = answer.toLowerCase();
                switch (selection) {
                    case "y":
                        addCustomer();
                    case "n":
                        System.out.println("");
                        menu();
                }
            } while (!(answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n")));
        } catch (InputMismatchException e) {
            System.out.println("");
        }
    }

    public void displayEmptyRooms() {
        //this method will display all the empty rooms
        for (int x = 1; x < 11; x++) {
            if (myHotel[x].getName().equals("e")) {
                System.out.println("room " + x + " is empty");
            }
        }
        System.out.println("");
        menu();
    }

    private void deleteCustomer() {
        boolean invalidInput;

        do {
            invalidInput = false;
            try {
                System.out.println("please enter the Room's number which you want to vacate");
                roomNum = input.nextInt();
                //if the hotel room is not empty then this will delete the customer from that room
                if (!(myHotel[roomNum].getName().equals("e"))) {
                    invalidInput = false;
                    myHotel[roomNum].setName("e");
                    //if the room is already empty then this message will be displayed
                } else {
                    invalidInput = true;
                    System.out.println("Room " + roomNum + " is already Empty");
                    System.out.println("");
                }
                //if the input is not an integer value then this will catch it
            } catch (InputMismatchException e) {
                invalidInput = true;
                System.out.println("Invalid input! Please Enter a value between 1-10");
                System.out.println("");
                input.next();
                //if the input is out of the range of the hotel array this will catch it
            } catch (IndexOutOfBoundsException e) {
                invalidInput = true;
                System.out.println("Invalid room number. Please enter a value between 1-10");
                input.next();
            }
        } while (invalidInput);//This will print the room's number which has been successfully vacated
        System.out.println("Room " + roomNum + " has successfully been vacated");
        System.out.println("");
        menu();
    }

    public void findRoom() {
        System.out.println("Please enter the name of the customer");
        boolean found = false;
        String find = input.next();

        for (int n = 1; n < 11; n++) {
            //used equalsIgnoreCase to avoid case sensitive issues while searching for a customer
            //this method will find the room's number which is currently being occupied by the mentioned customer
            if (myHotel[n].getName().equalsIgnoreCase(find)) {
                found = true;
                System.out.println("Mr. " + find + " is staying in room No. " + n);
                System.out.println("");
                menu();
            }
        }
        //this will let ou know if the customer is not there in the database
        if (found == false) {
            System.out.println(find + " doesn't exist on our database");
            System.out.println("");
            menu();
        }
    }

    public void storeData() {
        //
    }
}
