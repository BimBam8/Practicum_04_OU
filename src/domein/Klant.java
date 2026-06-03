package domein;

public class Klant {
    private final String nr;
    private final String postcode;

    public Klant(String nr, String postcode) {
        this.nr = nr;
        this.postcode = postcode;
    }

    public String getNummer() {
        return nr;
    }
}