package domein;

public class Klant {
    private final int nr;
    private final String postcode;

    public Klant(int nr, String postcode) {
        this.nr = nr;
        this.postcode = postcode;
    }

    public int getNummer() {
        return nr;
    }
}