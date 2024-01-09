public class MessageCreator implements MessagePrinter {
    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void printMessageGetPin() {
        System.out.println("Введите пароль");
    }

    @Override
    public void printWrongSymbol() {
        System.out.println("Неверный знак");
    }

    @Override
    public void printMenu() {
        System.out.println("""
                Выберите действие:
                1 Проверить счет\s
                2 Снять  деньги\s
                3 Положить деньги\s
                4 Выход
                """);
    }

    @Override
    public void printCheckAmount() {
        System.out.println("Проверить счет");
    }

    @Override
    public void printGetCash() {
        System.out.println("Снять  деньги\n Введите сумму:");
    }

    @Override
    public void printDeposit() {
        System.out.println("Положить деньги \n Укажите количество");
    }

    @Override
    public void printExit() {
        System.out.println("Выход");
    }

    @Override
    public void printNewAmount() {
        System.out.println("Введите новую сумму:");
    }


}
