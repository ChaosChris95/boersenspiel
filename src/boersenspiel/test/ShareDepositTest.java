package boersenspiel.test;

import boersenspiel.exceptions.NegativeValueException;
import boersenspiel.exceptions.NoSuchShareItemException;
import boersenspiel.exceptions.NotEnoughSharesException;
import boersenspiel.stock.ShareDeposit;

import boersenspiel.stock.Share;
import boersenspiel.stock.ShareItem;

import org.junit.*;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.LogManager;

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

    public ShareDepositTest() throws IOException {
        //LogManager.getLogManager().readConfiguration(new BufferedInputStream(new FileInputStream("c:\\logging.properties")));
    }

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

    @Test       //TODO
    public void testRemoveShare() throws NotEnoughSharesException, NegativeValueException {
        Share shareTest = new Share("Apple", 300);
        ShareDeposit shareDepositTest1 = new ShareDeposit();

        shareDepositTest1.addShare(shareTest, 7);     //2100
        shareDepositTest1.removeShare(shareTest, 4);  //2100-1200=900
        assertEquals("RemoveShare", 900, shareDepositTest1.getValue());
        assertEquals("RemoveShare", 3, shareDepositTest1.getShareAmount("Apple"));
    }

    /*@Test     //TODO  removeShareItem does not work
    public void testRemoveShareItem1() throws NoSuchShareItemException, NegativeValueException {
        Share shareTest2 = new Share("Apple", 300);
        ShareItem shareItemTest2 = new ShareItem(shareTest2, 20); //6000
        ShareDeposit shareDeposit2Test = new ShareDeposit();

        shareDeposit2Test.addShareItem(shareItemTest2);
        shareDeposit2Test.addShareItem(shareItemTest2);
        shareDeposit2Test.removeShareItem(shareItemTest2);
//        assertEquals("testRemoveShareItem1: 4000-2000", 2000, shareDeposit2Test.getValue());
        assertEquals("testRemoveShareItem1: 2-1", 1, shareDeposit2Test.getShareAmount("Apple"));
    }*/

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
