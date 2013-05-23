package boersenspiel.account;

import boersenspiel.exceptions.NegativeValueException;
import boersenspiel.exceptions.NotEnoughMoneyException;

import java.util.logging.Logger;

/**
 * User: Jan
 * Date: 09.04.13
 * Time: 17:34
 */

public class CashAccount extends Asset{

    private long accountValue;
    private static Logger logger = Logger.getLogger("CashAccount");

    public CashAccount(long accountValue){
        super("CashAccount");
        this.accountValue=accountValue;
    }

    public long getValue(){
        logger.finer("CashAccount.getValue()");
        return accountValue;
    }

    public void addCash(long cash) throws NegativeValueException{
        if (cash<0){
            throw new NegativeValueException();
        }
        accountValue += cash;
        logger.finer("CashAccount.addCash(" + cash + ")");
    }

    public String toString(){
        logger.finer("CashAccount.toString()");
        return ("Der Kontostand des Accounts " + name + " betraegt: " + getValue());
    }

    public void subCash(long cash) throws NotEnoughMoneyException {

        if (cash > accountValue) {
            throw new NotEnoughMoneyException("Sie besitzen nicht genügend Geld!");
        }
        accountValue -= cash;
        logger.finer("CashAccount.subCash(" + cash + ")");
    }

}
