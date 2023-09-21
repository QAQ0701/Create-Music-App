package ui;

import model.AllScore;
import model.Score;
import model.NoteOrRest;
import persistence.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// This CreateMusic references code from CPSC210/TellerApp and JsonSerializationDemo
// Link: https://github.students.cs.ubc.ca/CPSC210/TellerApp
//Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Create Your Own Music application
public class CreateMusicApp {

    private static final String JSON_STORE = "./data/score.json";
    private Scanner input;
    private Score score;
    private AllScore allScore;
    private NoteOrRest getNote;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the CreateMusic application
    public CreateMusicApp() {
        runCreate();
    }


    // MODIFIES: this
    // EFFECTS: processes user input
    private void runCreate() {
        boolean toContinue = true;
        String command = null;

        System.out.println();
        init();

        while (toContinue) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("f")) {
                toContinue = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nSee you next time!");
    }


    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("n")) {
            doAddNote();
        } else if (command.equals("r")) {
            doAddRest();
        } else if (command.equals("i")) {
            doInsertNoteOrRest();
        } else if (command.equals("re")) {
            doRemove();
        } else if (command.equals("g")) {
            doGetNoteOrRest();
        } else if (command.equals("s")) {
            saveScore();
        } else if (command.equals("l")) {
            loadScore();
        } else {
            System.out.println("Selection not valid, please try again");
        }
    }


    // MODIFIES: this
    // EFFECTS: initializes all fields except for getNote
    private void init() {
        score = new Score();
        allScore = new AllScore();
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nHi! What do you want to do?");
        System.out.println("\tn for Add Note");
        System.out.println("\tr for Add Rest");
        System.out.println("\ti for Insert Note or Rest");
        System.out.println("\tre for Remove Note or Rest");
        System.out.println("\tg for get a note of rest with given position");
        System.out.println("\ts to save the current score");
        System.out.println("\tl to load score from file");
        System.out.println("\tf for Finished\n");
    }


    // MODIFIES: this
    // EFFECTS: adds NoteOrRest Object from user input to score
    private void doAddNote() {
        String pitch;
        String noteTime;
        input = new Scanner(System.in);

        System.out.print("Enter the pitch of your note within E4-F5 (ex. C4):\n");
        pitch = input.nextLine();
        System.out.print("Enter the time Value (1, 1/2, 1/4, 1/8, 1/16) for your note:\n");
        noteTime = input.nextLine();
        NoteOrRest newNote;

        if (checkNote(noteTime, pitch)) {
            newNote = new NoteOrRest(pitch, noteTime);
            score.addNoteOrRest(newNote);
        } else {
            System.out.println("Cannot create note with invalid values\n");
        }

        printScore();
    }


    // MODIFIES: this
    // EFFECTS: adds rest to score
    private void doAddRest() {
        NoteOrRest newNote;
        input = new Scanner(System.in);

        System.out.print("What time value do you want for your rest?\n");
        String restTimeVal = input.nextLine();

        if (checkTimeVal(restTimeVal)) {
            newNote = new NoteOrRest("0", restTimeVal);
            score.addNoteOrRest(newNote);
        } else {
            System.out.println("Cannot create rest with invalid values\n");
        }

        printScore();
    }

    // MODIFIES: this
    // EFFECTS: Checks selection and calls insertRest and insert Note
    private void doInsertNoteOrRest() {
        String choice;
        input = new Scanner(System.in);

        System.out.println("\nDo You want to insert a Note or Rest?");
        System.out.println("\tn for Note");
        System.out.println("\tr for Rest");
        choice = input.nextLine();

        if (checkInsert(choice)) {
            if (choice.equals("n")) { //note
                insertNote();

            } else { //rest
                insertRest();
            }
        } else {
            System.out.println("Invalid choice, please try again\n");
        }

        printScore();
    }

    // MODIFIES: this
    // EFFECTS: inserts rest into given int index
    private void insertRest() {
        String pitch = "0";
        String timeVal;
        Integer index;
        input = new Scanner(System.in);
        NoteOrRest newElement;

        System.out.println("\nWhat is the time value of the Rest you want to insert?");
        timeVal = input.nextLine();
        System.out.println("\nGive the index of the position you want to insert your Rest.");
        index = input.nextInt();
        if (checkTimeVal(timeVal) && checkIndexValidity(index)) {
            newElement = new NoteOrRest(pitch, timeVal);
            score.insertNoteOrRest(newElement, index);
        } else {
            System.out.println("Invalid time value or index, please try again\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: inserts note into given int index
    private void insertNote() {
        Integer index;
        String pitch;
        String timeVal;
        input = new Scanner(System.in);
        NoteOrRest newElement;

        System.out.println("\nWhat is the pitch of the Note you want to insert?");
        pitch = input.nextLine();
        System.out.println("\nWhat is the time value of the Note you want to insert?");
        timeVal = input.nextLine();
        System.out.println("\nGive the index of the position you want to insert your note.");
        index = input.nextInt();
        if (checkNote(timeVal, pitch) && checkIndexValidity(index)) {
            newElement = new NoteOrRest(pitch, timeVal);
            score.insertNoteOrRest(newElement, index);
        } else {
            System.out.println("Invalid note or index, please try again\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: Removes element in Score corresponding to the given index num
    private void doRemove() {
        System.out.println("\nWhat is the index number of the element you want to remove?");
        Integer index = input.nextInt();

        if (checkIndexValidity(index)) {
            score.removeNoteOrRest(index);
        } else {
            System.out.println("Cannot remove non-existence elements\n");
        }

        printScore();
    }

    //MODIFIES: this
    //EFFECT: Check index validity and gets in the given index of score and prints it
    private void doGetNoteOrRest() {
        String pitch;
        String noteTime;
        NoteOrRest element;
        Integer index;
        input = new Scanner(System.in);

        System.out.print("What is the index of note you want to get? :\n");
        index = input.nextInt();

        if (checkIndexValidity(index)) {
            element = score.getNoteFromList(index);
            noteTime = element.getTimeVal();
            pitch = element.getPitch();

            if (checkNote(noteTime, pitch) || score.isRest(index)) {
                getNote = score.getNoteFromList(index);
                printGetNote();
            } else {
                System.out.println("Element in List is not valid\n");
            }
        } else {
            System.out.println("Given index out of bounds\n");
        }
        printScore();
    }

    // EFFECTS: saves the score to file
    private void saveScore() {
        try {
            jsonWriter.open();
            jsonWriter.write(allScore);
            jsonWriter.close();
            System.out.println("Saved Score to" + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads score from file
    private void loadScore() {
        try {
            allScore = jsonReader.read();
            System.out.println("Loaded Score from " + JSON_STORE);
            printScore();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECT: prints out the Note retrieved
    private void printGetNote() {
        System.out.println("Get Note:");
        System.out.println("Pitch: " + getNote.getPitch() + "\tTime Value: " + getNote.getTimeVal() + "\n");
    }

    // EFFECTS: prompts user to select Note or Rest to insert
    private Boolean checkInsert(String choice) {
        if (choice.equals("n")) {
            return true;
        } else if (choice.equals("r")) {
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: prints current Score to screen
    private void printScore() {
        System.out.printf("Current Music Score:\n");
        //for (int n = 0; n < allScore.getSize(); n++) {
        // System.out.print("Score" + n + "\t");
        for (int i = 0; i < score.getSizeOfScore(); i++) {
            NoteOrRest element = score.getNoteFromList(i);
            System.out.print("Note: Pitch: " + element.getPitch() + "\t Time Value: " + element.getTimeVal() + "\n");
        }
        // }

    }

    //EFFECT: Returns true is the given string is equal to one of "1", "1/2",
    //        "1/4", "1/8, or "1/16".
    //        Else return false.
    private Boolean checkTimeVal(String timeval) {
        if (timeval.equals("1")) {
            return true;
        } else if (timeval.equals("1/2")) {
            return true;
        } else if (timeval.equals("1/4")) {
            return true;
        } else if (timeval.equals("1/8")) {
            return true;
        } else if (timeval.equals("1/16")) {
            return true;
        } else {
            return false;
        }
    }

    //EFFECT: returns true only if the pitch is a combination of letter from a-g
    //        not case-sensitive and follows with number 4 or 5, no space in between.
    //        else, return false.
    private Boolean checkPitch(String pitch) {
        if ((pitch.length() == 2)) {
            char letter = pitch.charAt(0);
            return (checkPitchNum(pitch) && checkPitchLetter(letter));
        } else {
            return false;
        }
    }

    //EFFECT: return true only if the given number is 4 or 5.
    //        else, return false.
    private Boolean checkPitchNum(String pitch) {
        char num = pitch.charAt(1);
        char four = '4';
        char five = '5';
        int min = (int) four;
        int max = (int) five;
        return (Character.isDigit(num) && (num == four || num == five));
    }

    //EFFECT: return true only if the given letter is between a-g, inclusive and not case-sensitive.
    //        else, return false.
    private Boolean checkPitchLetter(char letter) {
        //return ((letter >= 'A' && letter <= 'G') || (letter >= 'a' && letter <= 'g'));
        return true;
    }

    //EFFECT: check if the index is smaller or equal to the size of the score
    private Boolean checkIndexValidity(Integer index) {
        return (index < score.getSizeOfScore());
    }

    //EFFECT: check if the given strings of timevalue and pitch adheres to the REQUIRES of the Note constructor
    private Boolean checkNote(String noteTime, String pitch) {
        return (checkTimeVal(noteTime) && checkPitch(pitch));
    }

}
