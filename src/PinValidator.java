import java.util.InputMismatchException;
import java.util.Scanner;

public class PinValidator {
    int fails = 0;
    long time = 0;
    int count = 0;
    MessageCreator messenger = new MessageCreator();

    public boolean getPin(User user) {

        Scanner s = new Scanner(System.in);
        if ((time + 10000) <= System.currentTimeMillis()) {
            messenger.printMessage("Введите " + (count + 1) + " цифру пин-кода:");
        }
        int pass = s.nextInt();
        if ((time + 10000) >= System.currentTimeMillis()) {
            count = 0;
            getTimeToUnlock();
        }
        if (pass == user.getPassword(count))
        {
            count++;
            if(count!=4){
                getPin(user);
            }

            return count == 4;
        }
        return false;
    }

    public boolean getAccess(User user, TerminalServer server) {
        try {
            if ((time + 10000) <= System.currentTimeMillis()) {
                user.unlockAccount();
            }
            short maxFails = 3;
            if (getPin(user)) {
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