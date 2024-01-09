import java.util.InputMismatchException;
import java.util.Scanner;

public class PinValidator {
    private final short maxFails = 3;
    int fails = 0;
    long time = 0;
    MessageCreator messenger = new MessageCreator();

    public boolean getPin(User user, TerminalServer server) {

        Scanner s = new Scanner(System.in);
        int pass = s.nextInt();
        if ((time + 10000) >= System.currentTimeMillis()) {
            getTimeToUnlock();
        }
        return server.checkPassword(pass, user);
    }

    public boolean getAccess(User user, TerminalServer server) {
        try {
            if ((time + 10000) <= System.currentTimeMillis()) {
                user.unlockAccount();
                messenger.printMessageGetPin();
            }
            if (getPin(user, server)) {
                return true;
            } else if (!user.isLocked()) {
                fails++;
                messenger.printMessage("Неверный пароль, осталось попыток " + (maxFails - fails));
            }
            if (fails >= maxFails) {
                user.lockAccount();
                time = System.currentTimeMillis();
                throw new AccountIsLockedException();
            }
        } catch (InputMismatchException e) {
            messenger.printWrongSymbol();
        } catch (AccountIsLockedException e) {
            fails = 0;
        }
        return false;
    }

    public void getTimeToUnlock() {
        messenger.printMessage("Осталось " +
                ((time + 10000 - System.currentTimeMillis())) / 1000 + " секунд");
    }
}