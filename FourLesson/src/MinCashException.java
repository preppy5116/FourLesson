public class MinCashException extends Throwable{
    public MinCashException() {
        System.out.println("Сумма должна быть кратна 100, введите другую сумму");
    }
}
