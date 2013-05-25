package boersenspiel.test;

/**
 * User: jan
 * Date: 06.05.13
 * Time: 13:27
 */

import boersenspiel.exceptions.NegativeValueException;
import boersenspiel.exceptions.NotEnoughSharesException;
import boersenspiel.stock.Share;
import boersenspiel.stock.ShareItem;
import org.junit.*;
import static org.junit.Assert.*;

public class ShareItemTest{

    private Share share1;
    private ShareItem shareItem1;

    @Before
    public void setUp() {
        share1 = new Share("BMW", 100);
        shareItem1 = new ShareItem(share1, 10);
    }

    @Test
    public void testGetShareAmount1() {
        assertEquals("testGetShareAmount1: 10*", 10, shareItem1.getShareAmount());
    }

    @Test
    public void testAddShareAmount1() throws NegativeValueException {
        shareItem1.addShareAmount(10);
        assertEquals("testAddShareAmount1: 10+10", 20, shareItem1.getShareAmount());
    }

    @Test (expected=NegativeValueException.class)
    public void testAddShareAmount2() throws NegativeValueException{
        shareItem1.addShareAmount(-20);
    }

    @Test
    public void testAddShareAmount3() throws NegativeValueException{
        shareItem1.addShareAmount(0);
        assertEquals("testAddShareAmount3: 10+0", 10, shareItem1.getShareAmount());
    }

    @Test
    public void testRemoveShareAmount1() throws NotEnoughSharesException {
        shareItem1.removeShareAmount(5);
        assertEquals("testRemoveShareAmount1: 10-5", 5, shareItem1.getShareAmount());
    }

    @Test
    public void testGetValue1() {
        assertEquals("testGetValue1: 1000", 1000, shareItem1.getValue());
    }

    @Test
    public void testAddValue1() {
        shareItem1.addValue(1000);
        assertEquals("testAddValue: 1000 + 1000", 2000, shareItem1.getValue());
    }
}
