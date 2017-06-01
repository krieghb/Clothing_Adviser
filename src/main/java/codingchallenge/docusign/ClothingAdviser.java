package codingchallenge.docusign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  @author Krieghbaum
 *
 * Parent abstract class for displaying whether a successful leaving of the house is achieved.
 *  Two possibilities in the weatherString at this point are possible:  HOT or COLD.
 *  What is worn and in what order is decided by the weatherString.
 */
public abstract class ClothingAdviser {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClothingAdviser.class);

    //  String constants for display or checks
    protected static final String FAILURE = "fail";
    protected static final String LEAVING = "leaving house";
    protected static final String REMOVE_PJS = "Removing PJs";
    protected static final String PJ_COMMAND = "8";
    protected static final String LEAVE_COMMAND = "7";

    //  Whether or not the current user is in their PJs or not.
    protected boolean inPjs;

    // Whether or not they are wearing parent required clothes.
    protected boolean havePants;
    protected boolean haveHeadware;
    protected boolean haveFootware;
    protected boolean haveShirt;

    //  Enum for the weatherString and command list for the dressing order.
    protected Weather weatherType;
    protected String[] commandList;

    /**
     *  Method to split the command given into it's weatherString type and the list of commands.
     * @param commands  String to split in format:  HOT/COLD followed by comma separated list of commands.
     *                      ex:  HOT 8, 4, 5, 3, 7
     *                      Should always start with 8 and end with 7
     * @return          The weatherString split from the command.
     */
    public Weather splitCommands(String commands) {
        //  Splitting based on only a space (not the comma space from the command list).
        String[] commandOrder = commands.trim().split("(?!,)\\s");

        //  Attempting to parse, setting to HOT and null if it fails.
        try {
            weatherType = Weather.valueOf(commandOrder[0].toUpperCase());
            commandList = commands.substring(weatherType.weatherString.length()).trim().split(", ");
        }
        catch (IllegalArgumentException e) {
//            LOGGER.info("Exception parsing:  {}", e);
            weatherType = Weather.HOT;
            commandList = null;
        }

        return weatherType;
    }

    /**
     *  Method to iterate through the command list and get the corresponding response based on the child's getResponse
     *      method.  if anything fails, breaks and prints out the current string.
     *
     * @return  The string containing the command list response including failure if appropriate.
     */
    public String decideWeather() {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        int currentCommand;
        String currentResponse;

        //  Making sure the list is not null and that it does begin with the remove pj command and ends with leaving
        //  the house.
        if (!isValidCommandList()) {
            sb.append(FAILURE);
        }
        else {
            //  Looping through each command and getting response from child.
            for (String str : commandList) {
                try {
                    currentCommand = Integer.parseInt(str.trim());
                    currentResponse = getResponse(currentCommand);
                    if (!first) {
                        sb.append(", ");
                    }
                    else {
                        first = false;
                    }

                    sb.append(currentResponse);

                    //  If the response failed, break out by throwing exception (appends fail).
                    if (currentResponse.equals(FAILURE)) {
                        break;
                    }
                }
                catch (NumberFormatException e) {
                    LOGGER.error("List contains a non-integer", e);
                    sb.append(FAILURE);
                    break;
                }
            }
        }

        LOGGER.info("{}", sb);

        return sb.toString();
    }

    /**
     *  Method to return if the command list is valid, which includes being non-null, starting with the
     *      Remove PJ command and ending with the Leave command.
     * @return  Whether the list is valid or not.
     */
    private boolean isValidCommandList() {
        return commandList != null && commandList[0].equals(PJ_COMMAND);
    }

    /**
     *  Method to return the response from trying the remove pjs command.
     * @return  'fail' if already removed pjs, otherwise valid string.
     */
    protected String removePJs() {
        StringBuilder response = new StringBuilder();
        if (!inPjs) {
            response.append(FAILURE);
        }
        else {
            response.append(REMOVE_PJS);
            inPjs = false;
        }
        return response.toString();
    }


    /**
     *  Method to get the corresponding reponse to the numbered command.
     * @param command   and int representing a command from removing pjs to putting on an article to leaving.
     * @return          the response from attempting command.
     */
    protected abstract String getResponse(int command);

    /**
     *  Method to return whether the current clothing command list is valid (ready to leave the house)/
     * @return  True if order and all articles are on correctly, false if not.
     */
    protected abstract boolean isClothingValid();

}
