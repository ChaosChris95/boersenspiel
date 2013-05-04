package boersenspiel;

/**
 * Created with IntelliJ IDEA.
 * User: jan
 * Date: 04.05.13
 * Time: 11:09
 */

import boersenspiel.account.CashAccount;
import static org.junit.Assert.*;
import org.junit.After;

public class CashAccountTest {

    private CashAccount cashAccount1 = new CashAccount(1000);
    private CashAccount cashAccount2 = new CashAccount(2000);

    //@Before
    public void setUp() {}

    //@After
    public void tearDown() {}

    //@BeforeClass
    public static void setUpBeforeClass() {}

    //@AfterClass
    public static void tearDownAfterClass() {}

    //@Test
    public void testGetValue() {
        //assertEquals("1000",new CashAccount(1000), cashAccount1.equals(cashAccount2));
    }

    //@Test
    public void testSubtract() {
        //assertEquals("30-20=10",new Money(10), money1.subtract(money2));
    }

    //@Test(expected=NotEnoughMoneyException)
    public void testSubCash() {

    }

    //@Test
    public void testToString() {

    }

    public void testAddCash(){

    }
}
