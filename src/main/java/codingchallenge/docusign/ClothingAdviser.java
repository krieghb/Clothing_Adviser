package codingchallenge.docusign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  @author Krieghbaum
 *
 * Parent abstract class for displaying whether a successful leaving of the house is achieved.
 *  Two possibilities in the weatherString at this point are possible:  HOT or COLD.
 *  What is worn and in what order is decided by the weatherString.
 *
 *  Temperature Type (one of the following)
 *  HOT
 *  COLD
 *  Comma separated list of numeric commands
 *  Command	Description	HOT Response	COLD Response
 *  1	Put on footwear	“sandals”	“boots”
 *  2	Put on headwear	“sun visor”	“hat”
 *  3	Put on socks	fail	“socks”
 *  4	Put on shirt	“t-shirt”	“shirt”
 *  5	Put on jacket	fail	“jacket”
 *  6	Put on pants	“shorts”	“pants”
 *  7	Leave house	“leaving house”	“leaving house”
 *  8	Take off pajamas	“Removing PJs”	“Removing PJs”
 *
 *  Rules:
 *    Initial state is in your house with your pajamas on
 *    Pajamas must be taken off before anything else can be put on
 *    Only 1 piece of each type of clothing may be put on
 *    You cannot put on socks when it is hot
 *    You cannot put on a jacket when it is hot
 *    Socks must be put on before shoes
 *    Pants must be put on before shoes
 *    The shirt must be put on before the headwear or jacket
 *    You cannot leave the house until all items of clothing are on (except socks and a jacket when it’s hot)
 *    If an invalid command is issued, respond with “fail” and stop processing commands
 *
 *
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
    public String getResponse() {
        StringBuilder sb = new StringBuilder();

        //  Making sure the list is not null and that it does begin with the remove pj command and ends with leaving
        //  the house.
        if (!isValidCommandList()) {
            sb.append(FAILURE);
        }
        else {
            //  Getting response list.
            sb.append(iterateCommands());
        }

        LOGGER.info("{}", sb);

        return sb.toString();
    }

    private String iterateCommands() {
        StringBuilder sb = new StringBuilder();
        int currentCommand;
        String currentResponse;
        boolean first = true;
        boolean shouldBreak = false;

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
                    shouldBreak = true;
                }
            }
            catch (NumberFormatException e) {
                LOGGER.error("List contains a non-integer", e);
                sb.append(FAILURE);
                shouldBreak = true;
            }
            if (shouldBreak) {
                break;
            }
        }

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
