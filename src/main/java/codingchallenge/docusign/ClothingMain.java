package codingchallenge.docusign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * @author Krieghbaum
 *
 *   Method to run the Clothing Adviser Classes.
 *   Runs a loop until user types a 'q';
 */
public class ClothingMain {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClothingMain.class);

    public static void main(String[] args) {
        String intake;
        ClothingAdviserExe cde = new ClothingAdviserExe();
        Scanner scanner = new Scanner(System.in);
        LOGGER.info("Enter command( (q)uits ):  \n");
        intake = scanner.nextLine();
        while (!intake.toUpperCase().equals("Q")) {
            cde.executeCommand(intake);
            LOGGER.info("Enter command( (q)uits ):  \n");
            intake = scanner.nextLine();
        }

        scanner.close();
        LOGGER.info("Exiting Clothing Adviser.");
    }
}
