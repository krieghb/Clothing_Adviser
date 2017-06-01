package codingchallenge.docusign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  @author Krieghbaum
 *
 *  Child class of ClothingAdviser specific to the Cold weather.
 *
 */
public class ClothingAdviserHot extends ClothingAdviser {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClothingAdviserHot.class);

    //  Child specific constants.
    private static final String FOOTWEAR = "sandals";
    private static final String HEADWEAR = "sun visor";
    private static final String SHIRT = "t-shirt";
    private static final String PANTS = "shorts";

    public ClothingAdviserHot() {
        this("Hot");
    }


    /**
     *  Constructor that takes in a String,  default calls this one, and sets all but the inPJs boolean to false.
     *  Also starts by splitting the command line into the weather type and its command list.
     * @param in        String to be parsed.
     */
    public ClothingAdviserHot(String in) {
        inPjs = true;
        havePants = false;
        haveFootware = false;
        haveShirt = false;
        haveHeadware = false;
        splitCommands(in);
    }

    /**
     *
     * @param command   Command number corresponding to addition of clothing, removal of PJs or leaving.
     * @return          String indicating failure, or corresponding command.
     */
    @Override
    protected String getResponse(int command) {
        String response;
        if ((!inPjs && command != 8 ) || (inPjs && command == 8)) {
            switch (command) {
                case 1:
                    response = wearFootwear();
                    break;
                case 2:
                    response = wearHeadwear();
                    break;
                case 4:
                    response = wearShirt();
                    break;
                case 6:
                    response = wearPants();
                    break;
                case 7:
                    response = isClothingValid() ? LEAVING : FAILURE;
                    break;
                case 8:
                    response = removePJs();
                    break;
                default:
                    response = FAILURE;
                    break;
            }
        }
        else {
            response = FAILURE;
        }
        return response;
    }

    /**
     *  Method to return the response based on the the correct state for footwear.
     * @return  The response String if it was successful in adding a footwear item.
     */
    protected String wearFootwear() {
        StringBuilder response = new StringBuilder();
        if (!haveFootware) {
            haveFootware = true;
            response.append(FOOTWEAR);
        }
        else {
            response.append(FAILURE);
        }
        return response.toString();
    }


    /**
     *  Method to return the response based on the the correct state for headwear.
     * @return  The response String if it was successful in adding a headwear item.
     */
    protected String wearHeadwear() {
        StringBuilder response = new StringBuilder();
        if (!haveHeadware && haveShirt) {
            haveHeadware = true;
            response.append(HEADWEAR);
        }
        else {
            response.append(FAILURE);
        }
        return response.toString();
    }

    /**
     *  Method to return the response based on the the correct state for shirt.
     * @return  The response String if it was successful in adding a shirt item.
     */
    protected String wearShirt() {
        StringBuilder response = new StringBuilder();
        if (!haveShirt) {
            haveShirt = true;
            response.append(SHIRT);
        }
        else {
            response.append(FAILURE);
        }
        return response.toString();
    }

    /**
     *  Method to return the response based on the the correct state for pants.
     * @return  The response String if it was successful in adding a pant item.
     */
    protected String wearPants() {
        StringBuilder response = new StringBuilder();
        if (!havePants) {
            havePants = true;
            response.append(PANTS);
        }
        else {
            response.append(FAILURE);
        }
        return response.toString();
    }

    /**
     *  Method to return if the current state is valid for the Hot weather (all clothes are worn).
     * @return  True if the clothing selection is valid.
     */
    @Override
    protected boolean isClothingValid() {
        return haveFootware && havePants &&
                haveShirt && haveHeadware;
    }
}
