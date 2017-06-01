package codingchallenge.docusign;

/**
 *  @author Krieghbaum
 *
 *  Child class of ClothingAdviser specific to the Cold weather.
 */
public class ClothingAdviserCold extends ClothingAdviser {

    //  Child specific articles of clothing.
    protected boolean haveSocks;
    protected boolean haveJacket;

    //  Child specific constants.
    protected static final String FOOTWEAR = "boots";
    protected static final String HEADWEAR = "hat";
    protected static final String PANTS = "pants";
    protected static final String SHIRT = "shirt";
    protected static final String SOCKS = "socks";
    protected static final String JACKET = "jacket";

    public ClothingAdviserCold() {
        this("Cold");
    }

    /**
     *  Constructor that takes in a String,  default calls this one, and sets all but the inPJs boolean to false.
     *  Also starts by splitting the command line into the weather type and its command list.
     * @param in        String to be parsed.
     */
    public ClothingAdviserCold(String in) {
        inPjs = true;
        havePants = false;
        haveSocks = false;
        haveFootware = false;
        haveShirt = false;
        haveJacket = false;
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
                case 3:
                    response = wearSocks();
                    break;
                case 4:
                    response = wearShirt();
                    break;
                case 5:
                    response = wearJacket();
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
        if (!haveFootware && haveSocks) {
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
     *  Method to return the response based on the the correct state for socks.
     * @return  The response String if it was successful in adding a sock item.
     */
    protected String wearSocks() {
        StringBuilder response = new StringBuilder();
        if (!haveSocks && !haveFootware) {
            haveSocks = true;
            response.append(SOCKS);
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
     *  Method to return the response based on the the correct state for jacket.
     * @return  The response String if it was successful in adding a jacket item.
     */
    protected String wearJacket() {
        StringBuilder response = new StringBuilder();
        if (!haveJacket && haveShirt) {
            haveJacket = true;
            response.append(JACKET);
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
        if (!havePants && !haveFootware) {
            havePants = true;
            response.append(PANTS);
        }
        else {
            response.append(FAILURE);
        }
        return response.toString();
    }

    /**
     *  Method to return if the current state is valid for the Cold weather (all clothes are worn).
     * @return  True if the clothing selection is valid.
     */
    @Override
    protected boolean isClothingValid() {
        return haveSocks && haveFootware && havePants &&
                haveShirt && haveHeadware && haveJacket;
    }
}
