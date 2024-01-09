import java.util.ArrayList;

@SuppressWarnings("ReassignedVariable")
public class User {
    private int cash;
    private final int password;
    private boolean status = false;


    public User(int cash, int password) {
        this.cash = cash;
        this.password = password;
    }

    public int getCash() {
        return cash;
    }

    public int getPassword(int base) {
        int pass = password;

        return switch (base) {
            case (0) -> {
                pass = pass / 1000;
                yield pass;
            }
            case (1) -> {
                pass = (pass / 100) % 10;
                yield pass;
            }
            case (2) -> {
                pass = (pass / 10) % 10;
                yield pass;
            }
            case (3) -> {
                pass = pass % 10;
                yield pass;
            }
            default -> pass;
        };
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public boolean isLocked() {
        return status;
    }

    public void lockAccount() {
        status = true;

    }

    public void unlockAccount() {
        status = false;
    }

}
