package boersenspiel.test;

import boersenspiel.exceptions.NoSuchShareItemException;
import boersenspiel.exceptions.ShareNameAlreadyExistsException;
import boersenspiel.manager.ShareManagement;
import boersenspiel.stock.ShareDeposit;

import boersenspiel.exceptions.NotEnoughSharesException;
import boersenspiel.stock.Share;
import boersenspiel.stock.ShareItem;

import org.junit.*;
import junit.framework.TestCase;

/**
 * User: jan
 * Date: 06.05.13
 * Time: 13:56
 */

public class ShareDepositTest extends TestCase{

    private ShareDeposit shareDeposit1;
    private Share share1;
    private ShareItem shareItem1;

    @Before
    public void setUp() {
        share1 = new Share("BMW", 200);
        shareItem1 = new ShareItem(share1, 10);
        shareDeposit1 = new ShareDeposit();
    }

    @Test
    public void testAddShareItem() {
        shareDeposit1.addShareItem(shareItem1);
        this.assertEquals("addShareItem", 2000, shareDeposit1.getValue());
        this.assertEquals("addShareItem", 10, shareDeposit1.getShareAmount("BMW"));
    }

    @Test
    public void testGetName() {
        this.assertEquals("ShareDeposit", "ShareDeposit", shareDeposit1.getName());
    }

    @Test
    public void testAddShare() {
        shareDeposit1.addShare(share1, 7);
        this.assertEquals("AddShare", 1400, shareDeposit1.getValue());
        this.assertEquals("AddShare", 7, shareDeposit1.getShareAmount("BMW"));
    }

    @Test       //TODO
    public void testRemoveShare() throws NotEnoughSharesException {
        shareDeposit1.addShare(share1, 7);
        //shareDeposit1.removeShare(share1, 4);
        this.assertEquals("AddShare", 600, shareDeposit1.getValue());
        this.assertEquals("AddShare", 3, shareDeposit1.getShareAmount("BMW"));
    }

    @Test     //TODO
    public void testRemoveShareItem1() throws NoSuchShareItemException {
        shareDeposit1.addShareItem(shareItem1);
        shareDeposit1.removeShareItem(shareItem1);
        this.assertEquals("addShareItem", 0, shareDeposit1.getValue());
        this.assertEquals("addShareItem", 0, shareDeposit1.getShareAmount("BMW"));
    }

    @Test(expected = NoSuchShareItemException.class)
    public void testRemoveShareItem2() throws NoSuchShareItemException {
        try{
            shareDeposit1.removeShareItem(shareItem1);
        } catch (NoSuchShareItemException e){}
    }

    @Test
    public void testGetValue() {
        shareDeposit1.addShareItem(shareItem1);
        this.assertEquals("addShareItem", 2000, shareDeposit1.getValue());
    }

    @Test
    public void testGetShareItemValue() {
        shareDeposit1.addShareItem(shareItem1);
        this.assertEquals("addShareItem", 10, shareDeposit1.getShareAmount("BMW"));
        this.assertEquals("addShareItem", 2000, shareDeposit1.getShareItemValue("BMW"));
    }

    @Test
    public void testGetShareAmount() {
        shareDeposit1.addShareItem(shareItem1);
        this.assertEquals("addShareItem", 2000, shareDeposit1.getShareItemValue("BMW"));
        this.assertEquals("AddShare", 10, shareDeposit1.getShareAmount("BMW"));
    }
}
