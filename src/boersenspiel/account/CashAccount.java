package boersenspiel.account;

import boersenspiel.exceptions.NegativeValueException;
import boersenspiel.exceptions.NotEnoughMoneyException;

import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * User: Jan
 * Date: 09.04.13
 * Time: 17:34
 */

public class CashAccount extends Asset{

    private long accountValue;
    private static Logger logger = Logger.getLogger("CashAccount");
    private ResourceBundle rs = ResourceBundle.getBundle("boersenspiel");

    public CashAccount(long accountValue){
        super("CashAccount");
        this.accountValue=accountValue;
    }

    public long getValue(){
        logger.finer("CashAccount.getValue()");
        return accountValue;
    }

    /**
     * add cash to selected playeraccount
     * @param cash  number from type long
     * @exception   NegativeValueException
     * @return      new accountValue
     */

    public void addCash(long cash) throws NegativeValueException{
        if (cash<0){
            throw new NegativeValueException();
        }
        accountValue += cash;
        logger.finer("CashAccount.addCash(" + cash + ")");
    }

    public String toString(){
        logger.finer("CashAccount.toString()");
        return (rs.getString("CashValue") +" "+ name +" "+ rs.getString("CashIs") +" "+ getValue());
    }

    public void subCash(long cash) throws NotEnoughMoneyException {

        if (cash > accountValue) {
            throw new NotEnoughMoneyException(rs.getString("CashNo"));
        }
        accountValue -= cash;
        logger.finer("CashAccount.subCash(" + cash + ")");
    }

}
