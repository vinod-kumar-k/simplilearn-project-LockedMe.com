package com.simplilearn.services;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class FileManagerService {

    private static final String FILE_PATH = "/var/www/html/files/";
    private String userInput;
    private Scanner scanner;

    public void startTheApp(){
        welcomeScreen();
        initScanner();
        initMainMenu();
    }

    private void initMainMenu(){
        showMainMenu();
        handleMainMenu();
    }

    private void welcomeScreen(){
        System.out.println("Welcome to The File Manager!");
        System.out.println("Developer: Vinod Kumar K");
    }

    private void initScanner(){
        scanner = new Scanner(System.in);
    }

    private void showMainMenu(){
        System.out.println();
        System.out.println("Please enter an option:");
        System.out.println("1 - Display Files (Ascending order)");
        System.out.println("2 - Manage Files");
        System.out.println("3 - Exit");
    }

    private void handleMainMenu(){
        do {
            userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    listFilesInAscendingOrder();
                    break;
                case "2":
                    showManageFileOptions();
                    break;
                case "3":
                    exitApp();
                    break;
                default:
                    System.out.println("Please enter a valid option");
                    handleMainMenu();
            }
        } while (!userInput.equals("3"));
    }

    private void listFilesInAscendingOrder(){
        try {
            File fileDir = new File(FILE_PATH);
            String[] files = fileDir.list();

            if (files == null || files.length == 0) {
                System.out.println("The directory is empty");
            } else {
                System.out.println("Files & Folders:");
                List<String> listFiles = Arrays.asList(files);
                Collections.sort(listFiles);
                for (String fileName : listFiles) {
                    System.out.println(fileName);
                }
            }
        }catch (Exception e){
            System.out.println("Something went wrong! Please see the error details below");
            System.out.println(e.getMessage());
        }

        showMainMenu();
    }

    private void showManageFileOptions(){
        System.out.println();
        System.out.println("Manage File Options:");
        System.out.println("1 - Add a file");
        System.out.println("2 - Delete a file");
        System.out.println("3 - Search a file");
        System.out.println("4 - Back to main menu");
        handleSubMenuOption();
    }

    private void handleSubMenuOption(){
        do {
            userInput = scanner.nextLine();
            switch (userInput){
                case "1":
                    addFile();
                    break;
                case "2":
                    deleteFile();
                    break;
                case "3":
                    searchFile();
                    break;
                case "4":
                    System.out.println("Back to main menu");
                    showMainMenu();
                    break;
                default:
                    System.out.println("Please enter a valid option");
            }
        } while (!userInput.equals("4"));
    }

    private void addFile(){
        System.out.println("Enter a file name to add: ");
        userInput = scanner.nextLine();
        File file = new File(FILE_PATH, userInput);

        if(file.exists()){
            System.out.println("It is an existing file or folder");
        }else{
            try{
                if(file.createNewFile()){
                    System.out.println("Added the file successfully");
                }else{
                    System.out.println("Failed to add the file");
                }
            }catch (Exception e){
                System.out.println("Something went wrong while adding the file. Please find the error details below.");
                System.out.println(e.getMessage());
            }
        }

        showManageFileOptions();
    }

    private void deleteFile(){
        System.out.println("Enter a file name to delete");
        userInput = scanner.nextLine();
        File file = new File(FILE_PATH, userInput);
        if(file.exists()){
            try {
                if (file.delete()) {
                    System.out.println("File has been deleted successfully");
                } else {
                    System.out.println("Failed to delete the file");
                }
            }catch (Exception e){
                System.out.println("Something went wrong while deleting the file. Please find the error details below:");
                System.out.println(e.getMessage());
            }
        }else{
            System.out.println("Entered file does not exist");
        }

        showManageFileOptions();
    }

    private void searchFile(){
        System.out.println("Enter the file to search: ");
        userInput = scanner.nextLine();

        try {
            File fileDir = new File(FILE_PATH);
            File[] files = fileDir.listFiles();
            String filePath = null;

            for (File file : files) {
                if (file.getName().equals(userInput)) {
                    filePath = file.getAbsolutePath();
                    break;
                }
            }

            if (filePath != null) {
                System.out.println("Found the file: " + filePath);
            } else {
                System.out.println("File could not be found");
            }
        }catch (Exception e){
            System.out.println("Something went wrong while searching the file. Please find the error details below.");
            System.out.println(e.getMessage());
        }

        showManageFileOptions();
    }

    private void exitApp(){
        System.out.println("Good bye :)");
        scanner.close();
    }
}
