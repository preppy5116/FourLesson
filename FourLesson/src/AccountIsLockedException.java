public class AccountIsLockedException extends Throwable{
    public AccountIsLockedException() {
        System.out.println("Слишком много попыток, Аккаунт заблокирован на 10 секунд");
    }

    public AccountIsLockedException(String message) {
        super(message);
    }
}
