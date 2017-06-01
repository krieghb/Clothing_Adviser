package codingchallenge.docusign;

import org.junit.Before;

import static org.junit.Assert.assertEquals;

/**
 *  @author Krieghbaum
 *
 */
public class TestClothingAdviserHot extends ClothingAdviserHot {

    private static ClothingAdviserHot cdh;

    @Before
    public void TestSetup() {
        cdh = new ClothingAdviserHot();
    }


    @org.junit.Test
    public void TestValidHotAddFootwear() {
        int addSocks = 1;
        String expectedResponse = "sandals";
        cdh.removePJs();
        String actualResponse = cdh.getResponse(addSocks);
        assertEquals(expectedResponse, actualResponse);
    }

    @org.junit.Test
    public void TestFailHotAddFootwear() {
        int addSocks = 1;
        String expectedResponse = "fail";
        cdh.removePJs();
        cdh.wearFootwear();
        String actualResponse = cdh.getResponse(addSocks);
        assertEquals(expectedResponse, actualResponse);
    }

    @org.junit.Test
    public void TestValidHotAddHat() {
        int addHat = 2;
        String expectedResponse = "sun visor";
        cdh.removePJs();
        cdh.wearShirt();
        String actualResponse = cdh.getResponse(addHat);
        assertEquals(expectedResponse, actualResponse);
    }

    @org.junit.Test
    public void TestFailHotAddHat() {
        int addHat = 2;
        String expectedResponse = "fail";
        cdh.removePJs();
        String actualResponse = cdh.getResponse(addHat);
        assertEquals(expectedResponse, actualResponse);
    }

    @org.junit.Test
    public void TestFailHotAddSocks() {
        int addSocks = 3;
        String expectedResponse = "fail";
        cdh.removePJs();
        String actualResponse = cdh.getResponse(addSocks);
        assertEquals(expectedResponse, actualResponse);
    }

    @org.junit.Test
    public void TestValidHotAddShirt() {
        int addShirt = 4;
        String expectedResponse = "t-shirt";
        cdh.removePJs();
        String actualResponse = cdh.getResponse(addShirt);
        assertEquals(expectedResponse, actualResponse);
    }

    @org.junit.Test
    public void TestFailHotAddShirt() {
        int addShirt = 4;
        String expectedResponse = "fail";
        cdh.removePJs();
        cdh.wearShirt();
        String actualResponse = cdh.getResponse(addShirt);
        assertEquals(expectedResponse, actualResponse);
    }

    @org.junit.Test
    public void TestFailHotAddJacket() {
        int addShirt = 5;
        String expectedResponse = "fail";
        cdh.removePJs();
        cdh.wearShirt();
        String actualResponse = cdh.getResponse(addShirt);
        assertEquals(expectedResponse, actualResponse);
    }

    @org.junit.Test
    public void TestValidHotAddPants() {
        int addPants = 6;
        String expectedResponse = "shorts";
        cdh.removePJs();
        String actualResponse = cdh.getResponse(addPants);
        assertEquals(expectedResponse, actualResponse);
    }

    @org.junit.Test
    public void TestFailHotAddPants() {
        int addPants = 6;
        String expectedResponse = "fail";
        cdh.removePJs();
        cdh.wearPants();
        String actualResponse = cdh.getResponse(addPants);
        assertEquals(expectedResponse, actualResponse);
    }

    @org.junit.Test
    public void TestValidHotLeave() {
        int leave = 7;
        String expectedResponse = "leaving house";
        cdh.removePJs();
        cdh.wearPants();
        cdh.wearFootwear();
        cdh.wearShirt();
        cdh.wearHeadwear();
        String actualResponse = cdh.getResponse(leave);
        assertEquals(expectedResponse, actualResponse);
    }

    @org.junit.Test
    public void TestFailHotLeave() {
        int leave = 7;
        String expectedResponse = "fail";
        cdh.removePJs();
        String actualResponse = cdh.getResponse(leave);
        assertEquals(expectedResponse, actualResponse);
    }

    @org.junit.Test
    public void TestValidHotRemovePJs() {
        int removePJS = 8;
        String expectedResponse = "Removing PJs";
        String removeResponse = cdh.getResponse(removePJS);

        assertEquals(expectedResponse, removeResponse);
    }

    @org.junit.Test
    public void TestFailHotRemovePJs() {
        int removePJS = 8;
        String expectedResponse = "fail";
        cdh.removePJs();
        String removeResponse = cdh.getResponse(removePJS);

        assertEquals(expectedResponse, removeResponse);
    }

}
