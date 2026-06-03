package domein;

import data.Mapper;

public class PrikToGo {
    private final Vestiging[] vestigingen;

    public PrikToGo() {
        vestigingen = Mapper.getVestigingen();
    }

    public String[] selecteerVestiging(int id) {
        return vestigingen[id].getKlantenInfo();
    }

    public String[] getOverzichtVestigingen() {
        String[] reStrings = new String[vestigingen.length];
        for (int i = 0; i < reStrings.length; i++) {
            reStrings[i] = vestigingen[i].getPlaatsNaam();
        }
        return reStrings;
    }
}