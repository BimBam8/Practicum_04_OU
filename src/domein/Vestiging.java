package domein;

import domein.Klant;

public class Vestiging {
    private final String plaatsNaam;
    private final String postcode;
    private final Klant[] klanten;

    public Vestiging(String plaatsNaam, String postcode, Klant[] klanten) {
        this.plaatsNaam = plaatsNaam;
        this.postcode = postcode;
        this.klanten = klanten;
    }

    public String[] getKlantenInfo() {
        String[] retString = new String[klanten.length];
        for (int i = 0; i < klanten.length; i++) {
            retString[i] = Integer.toString(klanten[i].getNummer());
        }
        return retString;
    }
    public String getPlaatsNaam() {
        return plaatsNaam;
    }
}