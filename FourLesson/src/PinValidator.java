import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PinValidator {
    private final short maxFails = 3;
    int fails = 0;
    long time;

    public boolean getAccess(User user, TerminalServer server) throws AccountIsLockedException, InterruptedException {
        try {
            if ((time + 10000) <= System.currentTimeMillis()) {
                user.unlockAccount();
                System.out.println("Введите пароль");
                while (fails < maxFails && !user.isLocked()) {
                    Scanner s = new Scanner(System.in);
                    int pass = s.nextInt();
                    if (server.checkPassword(pass, user))
                        return true;
                    fails++;
                    System.out.println("Неверный пароль, осталось попыток " + (maxFails - fails));
                    if (fails >= maxFails) {
                        user.lockAccount();
                        time = System.currentTimeMillis();
                        throw new AccountIsLockedException();
                    }
                }
            }
        } catch (InputMismatchException e) {
            fails++;
            System.out.println("Неверный знак,осталось попыток " + (maxFails - fails));
        } catch (AccountIsLockedException e) {
            fails = 0;
//            Проблема с этой часть, программа ожидает ввода символа после окончания счетчика один раз
//            while ((time + 10000) >= System.currentTimeMillis()) {
//                if (new Scanner(System.in).hasNext()) {
//                    getTimeToUnlock();
//                }
//            }
        }
        return false;
    }

    public void getTimeToUnlock() {
        System.out.println("Осталось" +
                ((time + 10000 - System.currentTimeMillis())) / 1000 + "секунд");
    }
}