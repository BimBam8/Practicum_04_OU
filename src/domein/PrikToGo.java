package domein;

import domein.Vestiging;

import javax.swing.SizeRequirements;

import data.Mapper;

public class PrikToGo {
    private Vestiging[] vestigingen;

    public PrikToGo() {
        vestigingen = Mapper.getVestigingen();
    }

    public String[] selecteerVestiging(int id) {
        return new String[1];
    }

    public String[] getOverzichtVestigingen() {
        return new String[1];
    }
}