package codingchallenge.docusign;

import org.junit.Before;

import static org.junit.Assert.assertEquals;

/**
 *  @author Krieghbaum
 */

public class TestClothingAdviserCold {
    private static ClothingAdviserCold cdh;

    @Before
    public void TestSetup() {
        cdh = new ClothingAdviserCold();
    }


    @org.junit.Test
    public void TestValidColdAddFootwear() {
        int addSocks = 1;
        String expectedResponse = "boots";
        cdh.removePJs();
        cdh.wearSocks();
        String actualResponse = cdh.getResponse(addSocks);
        assertEquals(expectedResponse, actualResponse);
    }

    @org.junit.Test
    public void TestFailColdAddFootwear() {
        int addSocks = 1;
        String expectedResponse = "fail";
        cdh.removePJs();
        cdh.wearFootwear();
        String actualResponse = cdh.getResponse(addSocks);
        assertEquals(expectedResponse, actualResponse);
    }

    @org.junit.Test
    public void TestValidColdAddHat() {
        int addHat = 2;
        String expectedResponse = "hat";
        cdh.removePJs();
        cdh.wearShirt();
        String actualResponse = cdh.getResponse(addHat);
        assertEquals(expectedResponse, actualResponse);
    }

    @org.junit.Test
    public void TestFailColdAddHat() {
        int addHat = 2;
        String expectedResponse = "fail";
        cdh.removePJs();
        String actualResponse = cdh.getResponse(addHat);
        assertEquals(expectedResponse, actualResponse);
    }

    @org.junit.Test
    public void TestValidColdAddSocks() {
        int addSocks = 3;
        String expectedResponse = "socks";
        cdh.removePJs();
        String actualResponse = cdh.getResponse(addSocks);
        assertEquals(expectedResponse, actualResponse);
    }


    @org.junit.Test
    public void TestFailColdAddSocks() {
        int addSocks = 3;
        String expectedResponse = "fail";
        cdh.removePJs();
        cdh.wearSocks();
        String actualResponse = cdh.getResponse(addSocks);
        assertEquals(expectedResponse, actualResponse);
    }

    @org.junit.Test
    public void TestValidColdAddShirt() {
        int addShirt = 4;
        String expectedResponse = "shirt";
        cdh.removePJs();
        String actualResponse = cdh.getResponse(addShirt);
        assertEquals(expectedResponse, actualResponse);
    }

    @org.junit.Test
    public void TestFailColdAddShirt() {
        int addShirt = 4;
        String expectedResponse = "fail";
        cdh.removePJs();
        cdh.wearShirt();
        String actualResponse = cdh.getResponse(addShirt);
        assertEquals(expectedResponse, actualResponse);
    }

    @org.junit.Test
    public void TestValidColdAddJacket() {
        int addShirt = 5;
        String expectedResponse = "jacket";
        cdh.removePJs();
        cdh.wearShirt();
        String actualResponse = cdh.getResponse(addShirt);
        assertEquals(expectedResponse, actualResponse);
    }

    @org.junit.Test
    public void TestFailColdAddJacket() {
        int addShirt = 5;
        String expectedResponse = "fail";
        cdh.removePJs();
        cdh.wearShirt();
        cdh.wearJacket();
        String actualResponse = cdh.getResponse(addShirt);
        assertEquals(expectedResponse, actualResponse);
    }

    @org.junit.Test
    public void TestValidColdAddPants() {
        int addPants = 6;
        String expectedResponse = "pants";
        cdh.removePJs();
        String actualResponse = cdh.getResponse(addPants);
        assertEquals(expectedResponse, actualResponse);
    }

    @org.junit.Test
    public void TestFailColdAddPants() {
        int addPants = 6;
        String expectedResponse = "fail";
        cdh.removePJs();
        cdh.wearPants();
        String actualResponse = cdh.getResponse(addPants);
        assertEquals(expectedResponse, actualResponse);
    }

    @org.junit.Test
    public void TestValidColdLeave() {
        int leave = 7;
        String expectedResponse = "leaving house";
        cdh.removePJs();
        cdh.wearPants();
        cdh.wearSocks();
        cdh.wearFootwear();
        cdh.wearShirt();
        cdh.wearJacket();
        cdh.wearHeadwear();
        String actualResponse = cdh.getResponse(leave);
        assertEquals(expectedResponse, actualResponse);
    }

    @org.junit.Test
    public void TestFailColdLeave() {
        int leave = 7;
        String expectedResponse = "fail";
        cdh.removePJs();
        String actualResponse = cdh.getResponse(leave);
        assertEquals(expectedResponse, actualResponse);
    }

    @org.junit.Test
    public void TestValidColdRemovePJs() {
        int removePJS = 8;
        String expectedResponse = "Removing PJs";
        String removeResponse = cdh.getResponse(removePJS);

        assertEquals(expectedResponse, removeResponse);
    }

    @org.junit.Test
    public void TestFailColdRemovePJs() {
        int removePJS = 8;
        String expectedResponse = "fail";
        cdh.removePJs();
        String removeResponse = cdh.getResponse(removePJS);

        assertEquals(expectedResponse, removeResponse);
    }

}
