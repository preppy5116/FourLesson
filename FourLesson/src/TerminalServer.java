import javax.naming.NoPermissionException;
import java.util.Scanner;

public class TerminalServer {

    int newCash;
    MessageCreator messenger = new MessageCreator();

    public int checkAmount(User user) {
        return user.getCash();
    }

    public boolean checkPassword(int password, User user) {
        return password == user.getPassword();
    }

    private boolean checkCash(int cash) {
        return cash % 100 == 0;
    }

    public void getCash(User user, int cash) throws NoMoneyException, MinCashException {
        synchronized (user) {
            try {
                if (user.getCash() - cash < 0) {
                    throw new NoMoneyException();
                } else if (!checkCash(cash)) {
                    throw new MinCashException();
                } else {
                    user.setCash(user.getCash() - cash);
                }
            } catch (NoMoneyException | MinCashException e) {
                messenger.printNewAmount();
                newCash = new Scanner(System.in).nextInt();
                getCash(user, newCash);
            }
        }
    }

    public void deposit(User user, int cash) {
        synchronized (user) {
            user.setCash(user.getCash() + cash);
        }
    }
}
