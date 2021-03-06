package codingchallenge.docusign;

/**
 *  @author Krieghbaum
 *
 *  Executing of the ClothingAdviser.  Chooses which child based on the String's parse of beginning.
 */
public class ClothingAdviserExe {

    //  Parent class to be extended.
    private String command;

    public ClothingAdviserExe() {
        command = "HOT";
    }

    public ClothingAdviserExe(String command) {
        this.command = command;
    }

    public void newCommand(String command) {
        this.command = command;
    }

    /**
     *  Method to execute the command in the argument by calling no argument version.
     * @param in    String to be parsed and executed on.
     */
    public void executeCommand(String in) {
        this.command = in;
        executeCommand();
    }

    /**
     *  Method to execute the command set in the command variable.
     */
    private void executeCommand() {
        ClothingAdviser hotCold;
        if (command.startsWith("COLD")) {
            hotCold = new ClothingAdviserCold(command);
        }
        else {
            hotCold = new ClothingAdviserHot(command);
        }

        hotCold.getResponse();
    }


}
