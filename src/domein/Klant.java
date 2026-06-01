package domein;

public class Klant {
    private String nr;
    private String postcode;

    public Klant(String nr, String postcode) {
        this.nr = nr;
        this.postcode = postcode;
    }

    public String getNummer() {
        return nr;
    }
}