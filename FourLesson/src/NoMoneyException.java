public class NoMoneyException extends Throwable {
    public NoMoneyException() {
        System.out.println("Недостаточно средств на счете");
    }
}
