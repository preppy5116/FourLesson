import javax.naming.NoPermissionException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


@SuppressWarnings("ALL")
public class Main {
    public static void main(String[] args) throws IOException, AccountIsLockedException, InterruptedException, NoAccessException, NoMoneyException, MinCashException, NoPermissionException {
        //Task 1
        User Ann = new User(10000, 1000);
        Terminal terminal = new TerminalImpl(new TerminalServer(), new PinValidator());
        terminal.startATM(Ann);

        //Task 2
//        InputUrl();

    }

    private static void readContent(String url) throws IOException {
        try {
            URL ur = new URL(url);
            Scanner sc = new Scanner(ur.openStream()).useDelimiter("\\A");
            String result = sc.hasNext() ? sc.next() : "";
            System.out.println(result);
        } catch (MalformedURLException e) {
            System.out.println("Неверно введен адрес:" + url);
            InputUrl();

        } catch (IOException e) {
            System.out.println("нет доступа к " + url);
            InputUrl();
        }
    }

    private static void InputUrl() throws IOException {
        String s;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите адрес или exit для выхода");
        s = scanner.next();
        if (s.equals("exit")) return;
        System.out.println(s);
        readContent(s);


    }
}