import javax.naming.NoPermissionException;
import java.awt.*;

public interface Terminal {
    public void startATM(User user) throws AccountIsLockedException, InterruptedException, NoMoneyException, MinCashException;
    public void check (User user) throws AccountIsLockedException, InterruptedException, NoAccessException;
    public void getCash (User user, int cash) throws  AccountIsLockedException, InterruptedException, NoMoneyException, MinCashException;
    public void depositCash (User user, int cash) throws AccountIsLockedException, InterruptedException;

}
