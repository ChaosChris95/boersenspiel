package boersenspiel.test;

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

    //Problem: Methode, die den Asset-Wert zurückliefert
    //TODO AccountManagerImpl gemeint? Was ist ShareDepositAccount? ShareManagment?
    //TODO vererbte Methoden

    private ShareDeposit shareDeposit1;
    private Share share1;
    private ShareItem shareItem1;

    @Before
    public void setUp() {
        share1 = new Share("BMW", 200);
        shareItem1 = new ShareItem(share1, 10);
        shareDeposit1 = new ShareDeposit();
    }

    public void testAddShareItem() {
        shareDeposit1.addShareItem(shareItem1);
        this.assertEquals("addShareItem", 2000, shareDeposit1.getValue());
        this.assertEquals("addShareItem", 10, shareDeposit1.getShareAmount("BMW"));
    }

    public void testGetName() {
        this.assertEquals("ShareDeposit", "ShareDeposit", shareDeposit1.getName());
    }

    public void testAddShare() {
        shareDeposit1.addShare(share1, 7);
        this.assertEquals("AddShare", 1400, shareDeposit1.getValue());
        this.assertEquals("AddShare", 7, shareDeposit1.getShareAmount("BMW"));
    }

    public void testRemoveShare() {
        shareDeposit1.addShare(share1, 7);
        shareDeposit1.removeShare(share1, 4);
        this.assertEquals("AddShare", 600, shareDeposit1.getValue());
        this.assertEquals("AddShare", 3, shareDeposit1.getShareAmount("BMW"));
    }

    public void testRemoveShareItem() {
        shareDeposit1.addShareItem(shareItem1);
        shareDeposit1.removeShareItem(shareItem1);
        this.assertEquals("addShareItem", 0, shareDeposit1.getValue());
        this.assertEquals("addShareItem", 0, shareDeposit1.getShareAmount("BMW"));
    }

    public void testGetValue() {
        shareDeposit1.addShareItem(shareItem1);
        this.assertEquals("addShareItem", 2000, shareDeposit1.getValue());
    }

    public void testGetShareItemValue() {
        shareDeposit1.addShareItem(shareItem1);
        this.assertEquals("addShareItem", 2000, shareDeposit1.getShareAmount("BMW"));
    }

    public void testGetShareAmount() {
        shareDeposit1.addShareItem(shareItem1);
        this.assertEquals("addShareItem", 2000, shareDeposit1.getShareAmount("BMW"));
        this.assertEquals("AddShare", 10, shareDeposit1.getShareAmount("BMW"));
    }
}
