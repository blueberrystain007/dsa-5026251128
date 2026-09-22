package lw01.unguided;

public class MotorcycleWash extends WashService {
 
    private static final int RATE_PER_DAY = 15_000;
    private static final int SETUP_FEE = 5_000;
 
    public MotorcycleWash(String id, int days, int units) {
        super(id, days, units);
    }
 
    @Override
    public int calculateCharge() {
        return getDays() * RATE_PER_DAY + SETUP_FEE;
    }
 
    @Override
    public String label() {
        return "Motorcycle";
    }
}
