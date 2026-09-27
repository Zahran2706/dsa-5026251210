public class ColourPrint extends PrintJob {

    private static final int RATE_FIRST_10 = 1500;
    private static final int RATE_BEYOND_10 = 1000;
    private static final int SETUP_FEE = 2000;
    private static final int TIER_THRESHOLD = 10;

    public ColourPrint(String id, int pages) {
        super(id, pages);
    }

    @Override
    public int calculateCharge() {
        int pages = getPages();
        int charge;
        if (pages <= TIER_THRESHOLD) {
            charge = pages * RATE_FIRST_10;
        } else {
            charge = (TIER_THRESHOLD * RATE_FIRST_10)
                    + ((pages - TIER_THRESHOLD) * RATE_BEYOND_10);
        }
        return charge + SETUP_FEE;
    }

    @Override
    public String label() {
        return "Colour";
    }
}
