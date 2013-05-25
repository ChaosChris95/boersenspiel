package boersenspiel.test;

import boersenspiel.exceptions.NegativeValueException;
import boersenspiel.exceptions.NoSuchShareItemException;
import boersenspiel.stock.ShareDeposit;

import boersenspiel.stock.Share;
import boersenspiel.stock.ShareItem;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * User: jan
 * Date: 06.05.13
 * Time: 13:56
 */

public class ShareDepositTest{

    private ShareDeposit shareDeposit1;
    private Share share1;
    private ShareItem shareItem1;

    private Share share2;
    private ShareItem shareItem2;

    @Before
    public void setUp() {
        share1 = new Share("BMW", 200);
        shareItem1 = new ShareItem(share1, 10);
        shareDeposit1 = new ShareDeposit();

        share2 = new Share("Siemens", 100);
        shareItem2 = new ShareItem(share2, 20);
    }

    @Test
    public void testAddShareItem() throws NegativeValueException {
        shareDeposit1.addShareItem(shareItem1);
        assertEquals("addShareItem: addShareItem", 2000, shareDeposit1.getValue());
        assertEquals("addShareItem: addShareItem", 10, shareDeposit1.getShareAmount("BMW"));
    }

    @Test
    public void testGetName() {
        assertEquals("testGetName: ShareDeposit", "ShareDeposit", shareDeposit1.getName());
    }

    @Test
    public void testAddShare() throws NegativeValueException{
        shareDeposit1.addShare(share1, 7);
        assertEquals("testAddShare: 7 * 200", 1400, shareDeposit1.getValue());
        assertEquals("AddShare: 7 *", 7, shareDeposit1.getShareAmount("BMW"));
    }

    /*@Test       //TODO
    public void testRemoveShare() throws NotEnoughSharesException, NegativeValueException {
        shareDeposit1.addShare(share1, 7);
        shareDeposit1.removeShare(share1, 4);
        assertEquals("AddShare", 600, shareDeposit1.getValue());
        assertEquals("AddShare", 3, shareDeposit1.getShareAmount("BMW"));
    }*/

    @Test     //TODO  removeShareItem does not work
    public void testRemoveShareItem1() throws NoSuchShareItemException, NegativeValueException {
        shareDeposit1.addShareItem(shareItem1);
        shareDeposit1.addShareItem(shareItem2);
        shareDeposit1.removeShareItem(shareItem1);
        assertEquals("testRemoveShareItem1: 2-1", 0, shareDeposit1.getValue());
        assertEquals("testRemoveShareItem1: 2-1", 0, shareDeposit1.getShareAmount("BMW"));
    }

    @Test(expected = NoSuchShareItemException.class)
    public void testRemoveShareItem2() throws NoSuchShareItemException {
            shareDeposit1.removeShareItem(shareItem1);
    }

    @Test
    public void testGetValue() throws NegativeValueException{
        shareDeposit1.addShareItem(shareItem1);
        assertEquals("testGetValue: shareItem1", 2000, shareDeposit1.getValue());
    }

    @Test
    public void testGetShareItemValue() throws NegativeValueException{
        shareDeposit1.addShareItem(shareItem1);
        assertEquals("testGetShareItemValue: 10 *", 10, shareDeposit1.getShareAmount("BMW"));
        assertEquals("testGetShareItemValue: 10 * 200", 2000, shareDeposit1.getShareItemValue("BMW"));
    }

    @Test
    public void testGetShareAmount() throws NegativeValueException{
        shareDeposit1.addShareItem(shareItem1);
        assertEquals("testGetShareAmount: 10 * 200", 2000, shareDeposit1.getShareItemValue("BMW"));
        assertEquals("testGetShareAmount: 10 *", 10, shareDeposit1.getShareAmount("BMW"));
    }
}
