package boersenspiel.test;

/**
 * User: jan
 * Date: 04.05.13
 * Time: 11:09
 */

import boersenspiel.account.CashAccount;

import boersenspiel.exceptions.NegativeValueException;
import junit.framework.TestCase;
import org.junit.*;
import boersenspiel.exceptions.NotEnoughMoneyException;

public class CashAccountTest extends TestCase{

    private CashAccount cashAccount1;
    private CashAccount cashAccount2;

    @Before
    public void setUp() {
        cashAccount1 = new CashAccount(1000);
        cashAccount2 = new CashAccount(2000);
    }

    @After
    public void tearDown() {}

    @BeforeClass
    public static void setUpBeforeClass() {}

    @AfterClass
    public static void tearDownAfterClass() {}

    @Test
    public void testGetValue1() {
        this.assertEquals("Expected value: 1000", 1000, cashAccount1.getValue());
    }

    @Test
    public void testSubCash1() throws NotEnoughMoneyException {
        cashAccount1.subCash(800);
        this.assertEquals("1000-800", 200, cashAccount1.getValue());
    }

    @Test(expected=NotEnoughMoneyException.class)         //TODO it does not work! why?
    public void testSubCash2()throws NotEnoughMoneyException{
        cashAccount1.subCash(2000);
    }

    @Test
    public void testSubCash3() throws NotEnoughMoneyException {
        cashAccount1.subCash(1000);
        this.assertEquals("1000-1000", 0, cashAccount1.getValue());
    }

    @Test
    public void testAddCash1() throws NegativeValueException{
        cashAccount1.addCash(1000);
        this.assertEquals("1000+1000", 2000, cashAccount1.getValue());
    }

    @Test
    public void testAddCash2()throws NegativeValueException{
        cashAccount1.addCash(1000000000);
        this.assertEquals("1000+1000000000", 1000001000, cashAccount1.getValue());
    }

    @Test (expected=NegativeValueException.class)   //TODO test
    public void testAddCash3() throws NegativeValueException{
        cashAccount1.addCash(-2000);
        this.assertEquals("1000-2000", 0, cashAccount1.getValue());
    }
}
