package codingchallenge.docusign;

/**
 * @author Krieghbaum
 *
 *  Enum for Weather types.  Expandable to beyond Hot or Cold
 *
 */
public enum Weather {
    HOT   (0, "Hot")
    , COLD  (1, "Cold")
    ;

    int index;
    String weatherString;
    Weather(int index, String weather) {
        this.index = index;
        this.weatherString = weather;
    }

    public int getIndex() {
        return index;
    }
    public String getWeatherString() {
        return weatherString;
    }

}
