package boersenspiel.test;

/**
 * User: jan
 * Date: 04.05.13
 * Time: 11:09
 */

import boersenspiel.account.CashAccount;
import boersenspiel.exceptions.NegativeValueException;
import boersenspiel.exceptions.NotEnoughMoneyException;
import org.junit.*;
import static org.junit.Assert.*;

public class CashAccountTest {

    private CashAccount cashAccount1;

    @Before
    public void setUp() {
        cashAccount1 = new CashAccount(1000);
    }

    @After
    public void tearDown() {}

    @BeforeClass
    public static void setUpBeforeClass() {}

    @AfterClass
    public static void tearDownAfterClass() {}

    @Test
    public void testGetValue1() {
        assertEquals("Expected value: 1000", 1000, cashAccount1.getValue());
    }

    @Test
    public void testSubCash1() throws NotEnoughMoneyException {
        cashAccount1.subCash(800);
        assertEquals("1000-800", 200, cashAccount1.getValue());
    }

    @Test(expected=NotEnoughMoneyException.class)
    public void testSubCash2() throws NotEnoughMoneyException{
            cashAccount1.subCash(2000);
    }

    @Test
    public void testSubCash3() throws NotEnoughMoneyException {
        cashAccount1.subCash(1000);
        assertEquals("1000-1000", 0, cashAccount1.getValue());
    }

    @Test
    public void testAddCash1() throws NegativeValueException{
        cashAccount1.addCash(1000);
        assertEquals("1000+1000", 2000, cashAccount1.getValue());
    }

    @Test
    public void testAddCash2()throws NegativeValueException{
        cashAccount1.addCash(1000000000);
        assertEquals("1000+1000000000", 1000001000, cashAccount1.getValue());
    }

    @Test (expected=NegativeValueException.class)
    public void testAddCash3() throws NegativeValueException{
            cashAccount1.addCash(-2000);
    }
}
