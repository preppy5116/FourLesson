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

    public int getPassword() {
        return password;
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
