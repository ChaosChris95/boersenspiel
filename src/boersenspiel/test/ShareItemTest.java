package boersenspiel.test;

/**
 * User: jan
 * Date: 06.05.13
 * Time: 13:27
 */

import boersenspiel.stock.Share;
import boersenspiel.stock.ShareItem;
import org.junit.*;
import junit.framework.TestCase;

public class ShareItemTest extends TestCase{

    private Share share1;
    private ShareItem shareItem1;

    @Before
    public void setUp() {
        share1 = new Share("BMW", 100);
        shareItem1 = new ShareItem(share1, 10);
    }

    @Test
    public void testGetShareAmount1() {
        this.assertEquals("Expected value: 10", 10, shareItem1.getShareAmount());
    }

    @Test
    public void testAddShareAmount1() {
        shareItem1.addShareAmount(10);
        this.assertEquals("10+10", 20, shareItem1.getShareAmount());
    }

    @Test
    public void testAddShareAmount2() {
        shareItem1.addShareAmount(-20);
        this.assertEquals("10-20", 0, shareItem1.getShareAmount());
    }

    @Test
    public void testAddShareAmount3() {
        shareItem1.addShareAmount(0);
        this.assertEquals("10+0", 10, shareItem1.getShareAmount());
    }

    //Negative Values in methods addShareAmount, testAddValue and RemoveShareAmount should not be allowed

    @Test
    public void testRemoveShareAmount1() {
        shareItem1.removeShareAmount(5);
        this.assertEquals("10-5", 5, shareItem1.getShareAmount());
    }

    @Test
    public void testGetValue1() {
        this.assertEquals("Expected value: 1000", 1000, shareItem1.getValue());
    }

    @Test
    public void testSetValue1() {
        shareItem1.setValue(2000);
        this.assertEquals("Expected value: 2000", 2000, shareItem1.getValue());
    }

    @Test
    public void testAddValue1() {
        shareItem1.addValue(1000);
        this.assertEquals("Expected value: 3000", 2000, shareItem1.getValue());
    }

    @Test
    public void testToString1() {
        this.assertEquals("ToString", "ShareItem BMW mit einem Gesamtwert von 1000", shareItem1.toString());
    }

}
