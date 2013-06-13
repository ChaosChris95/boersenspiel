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

    /**
     * Constructor for CashAccount will set account value and inherits from Asset
     * @param accountValue  from type long
     */

    public CashAccount(long accountValue){
        super("CashAccount");
        this.accountValue=accountValue;
    }

    /**
     *
     * @return
     */

    public long getValue(){
        logger.finer("CashAccount.getValue()");
        return accountValue;
    }

    /**
     * add cash to selected player account
     * @param cash  number from type long
     * @exception   NegativeValueException if given cash value is a negative number
     */

    public void addCash(long cash) throws NegativeValueException{
        if (cash<0){
            throw new NegativeValueException();
        }
        accountValue += cash;
        logger.finer("CashAccount.addCash(" + cash + ")");
    }

    /**
     * The string representation is "name=NAME, getValue()=VALUE"
     * Where NAME is the player name,VALUE is the value of cash account
     */

    public String toString(){
        logger.finer("CashAccount.toString()");
        return (rs.getString("CashValue") +" "+ name +" "+ rs.getString("CashIs") +" "+ getValue());
    }

    /**
     * subtract cash to selected player account
     * @param cash  number from type long
     * @exception   NotEnoughMoneyException if player did not have enough value to subtract of
     */


    public void subCash(long cash) throws NotEnoughMoneyException {

        if (cash > accountValue) {
            throw new NotEnoughMoneyException(rs.getString("CashNo"));
        }
        accountValue -= cash;
        logger.finer("CashAccount.subCash(" + cash + ")");
    }

}
