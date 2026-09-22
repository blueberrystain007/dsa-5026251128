package lw01.unguided;


public class CarWash extends WashService {

    private static final int BASE_DAYS = 3;
    private static final int BASE_RATE = 35_000;
    private static final int EXTRA_RATE = 25_000;
    private static final int SETUP_FEE = 15_000;

    public CarWash(String id, int days, int units) {
        super(id, days, units);
    }

    @Override
    public int calculateCharge() {
        int days = getDays();
        int baseDays = Math.min(days, BASE_DAYS);
        int extraDays = Math.max(days - BASE_DAYS, 0);
        return (baseDays * BASE_RATE) + (extraDays * EXTRA_RATE) + SETUP_FEE;
    }

    @Override
    public String label() {
        return "Car";
    }
}
