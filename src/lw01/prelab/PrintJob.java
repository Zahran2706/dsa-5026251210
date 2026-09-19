public abstract class PrintJob implements Chargeable {

    private String id;
    private int pages;

    protected PrintJob(String id, int pages) {
        if (pages <= 0) {
            throw new IllegalArgumentException("pages must be positive");
        }
        this.id = id;
        this.pages = pages;
    }

    public String getId() {
        return id;
    }

    public int getPages() {
        return pages;
    }

    @Override
    public abstract int calculateCharge();

    // Overload: dihitung sekali di sini, tidak diduplikasi di subclass
    public int calculateCharge(int copies) {
        if (copies <= 0) {
            throw new IllegalArgumentException("copies must be positive");
        }
        return copies * calculateCharge();
    }

    public String label() {
        return "Print";
    }

    // Tidak boleh di-override oleh subclass
    public final String summary() {
        return id + " | " + label() + " | " + calculateCharge();
    }
}
