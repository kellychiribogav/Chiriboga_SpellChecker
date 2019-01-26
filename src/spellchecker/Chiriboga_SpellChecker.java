//Author Name: Kelly Chiriboga
//Date: 01/26/2019
//Program Name: SpellChecker
//Purpose: Spell checks and prints out unknown words
package spellchecker;

import java.io.*;
import java.util.*;

/**
 * @author Kelly Chiriboga
 */
public class Chiriboga_SpellChecker {

    private static Scanner input; // keyboard input
    private static ArrayList<String> testFileList = new ArrayList<String>();   //lists words in file to check
    private static ArrayList<String> dictionaryList = new ArrayList<String>(); // lists words in dictionary file

    /**
     * Main class. Executes program.
     *
     * @param args
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {

        input = new Scanner(System.in); // user input

        String test = getTestFile(); // test file is read into string variable
        String dictionary = getDictionary(); // dictionary is read into string variable
        spellCheck(test, dictionary);
    }

    /**
     * Gets a valid file name for file to check
     *
     * @return name for test file
     */
    public static String getTestFile() {
        String validName = null;
        boolean isValidName = false;

        // Loops until a valid input value is entered
        do {
            System.out.println("Enter name of the file to check (include extension): ");
            validName = input.nextLine();
            File f = new File(validName);
            // checks that file name is valid and that file exists
            if (validName != null && validName.length() > 0 && f.exists()) {
                isValidName = true;
            } // error message for invalid input
            else {
                System.out.println("ERROR: You must enter a valid file name.");
            }
        } while (!isValidName);

        return validName;
    }

    /**
     * Gets a valid file name for dictionary
     *
     * @return dictionary file name
     */
    public static String getDictionary() {
        String validName = null;
        boolean isValidName = false;

        // Loops until a valid input value is entered
        do {
            System.out.println("Enter name of the dictionary file (include extension): ");
            validName = input.nextLine();
            File f = new File(validName);

            // checks that file name is valid and that file exists
            if (validName != null && validName.length() > 0 && f.exists()) {
                isValidName = true;
            } // error message for invalid input
            else {
                System.out.println("ERROR: You must enter a valid file name.");
            }
        } while (!isValidName);

        return validName;
    }

    /**
     * Compares both files for spell checking of words from the first file and
     * then lists unknown words.
     *
     * @param testFile file to check
     * @param dictionaryFile dictionary file
     * @throws IOException
     */
    private static void spellCheck(String testFile, String dictionaryFile) throws IOException {

        String wordLine;

        // Reads words from file to test and adds them to a list
        try (BufferedReader reader1 = new BufferedReader(new FileReader(testFile))) {
            while ((wordLine = reader1.readLine()) != null) {
                testFileList.add(wordLine);
            }
        }

        // Reads words from dictionary and adds them to a list
        try (BufferedReader reader2 = new BufferedReader(new FileReader(dictionaryFile))) {
            while ((wordLine = reader2.readLine()) != null) {
                dictionaryList.add(wordLine);
            }
        }

        // Iteration of words to check in test file. Main Loop.
        Iterator<String> mainLoop = testFileList.iterator();
        int i = 1;
        while (mainLoop.hasNext()) {
            String word = mainLoop.next();
            // checks for unknown words and prints them
            if (!dictionaryList.contains(word)) {
                System.out.println("Unknown word #" + i + ": " + word);
                i++;
            }

        }

    }
}
