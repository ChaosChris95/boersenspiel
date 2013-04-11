package boersenspiel;

/**
 * Created with IntelliJ IDEA.
 * User: Peach
 * Date: 09.04.13
 * Time: 17:34
 * To change this template use File | Settings | File Templates.
 */
public class AccountManagerImpl implements AccountManager{

    private UserManagement userManagement;
    private ShareManagement shareManagement;

    public AccountManagerImpl() {
        userManagement = new UserManagement();
        shareManagement = new ShareManagement();
    }

    @Override
    public void createPlayer(String name, long cash) {
        userManagement.addPlayer(name, cash);
    }

    @Override
    public void buy(String name, String shareName, int value) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void sell(String name, String shareName, int value) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long getCashAccountValue(String name) {
        return userManagement.getPlayer(name).getCashAccountValue();
    }

    @Override
    public long getShareItemValue(String name) {
        return 0;
    }

    @Override
    public long getShareDepositValue(String name) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long getAssetValue(String name) {
    	return 0;
    }

    @Override
    public long getRate(String shareName) {
        return shareManagement.getSpecificRate(shareName);
    }

    @Override
    public void getList() {
        shareManagement.listAll();
    }
}
