import javax.naming.NoPermissionException;
import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TerminalImpl implements Terminal {


    private final TerminalServer server;
    private final PinValidator pinValidator;
    private boolean access;

    public TerminalImpl(TerminalServer server, PinValidator pinValidator) {
        this.server = server;
        this.pinValidator = pinValidator;
    }

    public void getAccess(User user) throws AccountIsLockedException, InterruptedException {
        access = pinValidator.getAccess(user, server);
    }

    private boolean checkCash(int cash) {
        return cash % 100 == 0;
    }

    @Override
    public void startATM(User user) throws AccountIsLockedException, InterruptedException, NoMoneyException, MinCashException {

        while (true) {
            try {
                if (access) {
                    System.out.println("Выберите действие:\n" +
                            "1 Проверить счет \n" +
                            "2 Снять  деньги \n" +
                            "3 Положить деньги \n" +
                            "4 Выход\n");
                    int choice = new Scanner(System.in).nextInt();
                    switch (choice) {
                        case 1:
                            System.out.println("Проверить счет");
                            check(user);
                            break;
                        case 2:
                            System.out.println("Снять  деньги\n Введите сумму:");
                            int money = new Scanner(System.in).nextInt();
                            getCash(user, money);
                            check(user);
                            break;
                        case 3:
                            System.out.println("Положить деньги \n Укажите количество");
                            int depositMoney = new Scanner(System.in).nextInt();
                            depositCash(user, depositMoney);
                            check(user);
                            break;
                        case 4:
                            System.out.println("Выход");

                            return;
                        default:
                            throw new InputMismatchException("Unexpected value: " + choice);
                    }

                } else {
                    getAccess(user);

                }
            } catch (InputMismatchException e) {
                System.out.println("Неверный символ");
            }
        }

    }

    @Override
    public void check(User user) throws AccountIsLockedException, InterruptedException {
        if (access) {
            System.out.println("Остаток на счете:" + server.checkAmount(user));
        } else {
            try {
                throw new NoAccessException();
            } catch (NoAccessException e) {
                getAccess(user);
            }
        }
    }

    @Override
    public void getCash(User user, int cash) throws AccountIsLockedException, InterruptedException, NoMoneyException, MinCashException {
        try {
            if (access) {
                server.getCash(user, cash);
            } else {
                throw new NoAccessException();
            }
        } catch (NoAccessException e) {
            getAccess(user);
        }
    }

    @Override
    public void depositCash(User user, int cash) throws AccountIsLockedException, InterruptedException{
        try {
            if (access) {
                server.deposit(user, cash);
            } else {
                throw new NoAccessException();
            }
        } catch (NoAccessException e) {
            getAccess(user);
        }
    }
}