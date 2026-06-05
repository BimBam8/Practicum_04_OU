package presentatie;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;

import domein.PrikToGo;

public class View extends JFrame{
    private final PrikToGo ptg;
    private JComboBox<String> vestigingenComboBox;
    private JList<String> klantenLijst;
    private DefaultListModel<String> klantenLijstModel;
    private JLabel totaalLabel;


    public View(PrikToGo ptg) {
        this.ptg = ptg;
        initialiseerVenster();
        initialiseerComponenten();
        laadVestigingen();
        setVisible(true);
    }

    private void initialiseerVenster() {
        setTitle("Prik2Go - Klanten per vestiging");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(500, 450);
        setLocationRelativeTo(null);

        // Gebruik beeindigOverzicht bij sluiten van het venster
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                sluitApplicatie();
            }
        });
    }

    private void initialiseerComponenten() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        

        add(mainPanel);
    }

    private void laadVestigingen() {
        String[] vestigingen = ptg.getOverzichtVestigingen();
        vestigingenComboBox.setModel(new DefaultComboBoxModel<>(vestigingen));
        vestigingenComboBox.setSelectedIndex(-1); // Geen selectie bij start
    }

    private void toonKlanten() {
        int geselecteerdeIndex = vestigingenComboBox.getSelectedIndex();
        if (geselecteerdeIndex >= 0) {
            String[] klanten = ptg.selecteerVestiging(geselecteerdeIndex);
            klantenLijstModel.clear();
            for (String klant : klanten) {
                klantenLijstModel.addElement(klant);
            }
            totaalLabel.setText("Totaal klanten: " + klanten.length);
        } else {
            klantenLijstModel.clear();
            totaalLabel.setText("Totaal klanten: 0");
        }
    }

    private void sluitApplicatie() {
        int confirm = JOptionPane.showConfirmDialog(this, "Weet je zeker dat je de applicatie wilt sluiten?", "Bevestiging", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
        }
    }


}