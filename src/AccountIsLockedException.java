public class AccountIsLockedException extends Throwable {
    public AccountIsLockedException() {
        System.out.println("Слишком много попыток, Аккаунт заблокирован на 10 секунд\n" +
                "По окончанию блокировки введите любой символ ");
    }
}
