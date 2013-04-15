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

    public AccountManagerImpl(ShareManagement shareManagement) {
        userManagement = new UserManagement();
        this.shareManagement = shareManagement;
    }

    @Override
    public void createPlayer(String name, long cash) {
        userManagement.addPlayer(name, cash);
    }

    @Override
    public void buy(String playerName, String shareName, int amount) throws Exception{
        long shareValue = shareManagement.getSpecificRate(shareName);
        shareValue *= amount;
        userManagement.getPlayer(playerName).subCash(shareValue);
        userManagement.getPlayer(playerName).addShareToDeposit(shareManagement.getShare(shareName), amount);
    }

    @Override
    public void sell(String playerName, String shareName, int amount) {
        long shareValue = shareManagement.getSpecificRate(shareName);
        shareValue *= amount;
        userManagement.getPlayer(playerName).addCash(shareValue);
        userManagement.getPlayer(playerName).subShareFromDeposit(shareManagement.getShare(shareName), amount);
    }

    @Override
    public long getCashAccountValue(String playerName) {
        return userManagement.getPlayer(playerName).getCashAccountValue();
    }

    @Override
    public long getShareItemValue(String playerName, String shareItemName) {
        return userManagement.getPlayer(playerName).getShareItemValue(shareItemName);
    }

    @Override
    public long getShareDepositValue(String playerName) {
        return userManagement.getPlayer(playerName).getShareDepositValue();
    }

    @Override
    public long getAssetValue(String name) {
        return (getCashAccountValue(name) + getShareDepositValue(name));
    }

    @Override
    public long getRate(String shareName) {
        return shareManagement.getSpecificRate(shareName);
    }

    @Override
    public String getList() {
        return shareManagement.listAll();
    }
}
