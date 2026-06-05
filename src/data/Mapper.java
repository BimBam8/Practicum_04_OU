package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;

import domein.Klant;
import domein.Vestiging;

public class Mapper {

    private static final String DATABASE_URL = "jdbc:firebirdsql://localhost:3052//var/lib/firebird/data/Prik2Go_res_v3.fdb";
    private static final String DATABASE_USER = "sysdba";
    private static final String DATABASE_PASSWORD = "masterkey";
    private static final String DRIVERNAME = "org.firebirdsql.jdbc.FBDriver";

    public static Vestiging[] getVestigingen() {
        try {

            String sqlString = "SELECT v.plaats, v.postcode, COUNT(*) OVER() AS len_vestiging\n" +
                    "FROM vestiging AS v";
            String lenVestiginen = "len_vestiging";
            String naamKey = "plaats";
            String pcKey = "postcode";
            Class.forName(DRIVERNAME);

            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return new Vestiging[0];
            }
            int lenVes = resultSet.getInt(lenVestiginen);
            String vesNaam = resultSet.getString(naamKey);
            String vesPostCode = resultSet.getString(pcKey);
            Vestiging[] vestigingen = new Vestiging[lenVes];

            vestigingen[0] = new Vestiging(vesNaam, vesPostCode, getKlanten(vesNaam, connection));
            // System.out.println(vesNaam + vesPostCode);
            printarr(vestigingen);
            for (int i = 1; resultSet.next(); i++) {
                System.out.println("hallojkahlefikjh");
                vesNaam = resultSet.getString(naamKey);
                vesPostCode = resultSet.getString(pcKey);
                
                vestigingen[i] = new Vestiging(vesNaam, vesPostCode, getKlanten(vesNaam, connection));
            }
            connection.close();
            return vestigingen;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    private static Klant[] getKlanten(String vestiging, Connection connection) {
        // Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        String sqlString = "SELECT k.nr, k.postcode, COUNT(*) OVER() AS len_klant\n" +
                "FROM klant AS k\n" +
                "LEFT JOIN bezoek AS b ON k.nr = b.klant\n" +
                "WHERE b.vestiging = ?;";
        String lenKey = "len_klant";
        String nrKey = "nr";
        String pcKey = "postcode";
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
            preparedStatement.setString(1, vestiging);
            ResultSet resultSet = preparedStatement.executeQuery();
            Klant[] klanten = new Klant[0];
            if (!resultSet.next()) {
                return klanten;
            }
            int lenKlanten = resultSet.getInt(lenKey);
            klanten = new Klant[lenKlanten];

            int klantNr = resultSet.getInt(nrKey);
            String klantPostcode = resultSet.getString(pcKey);
            klanten[0] = new Klant(klantNr, klantPostcode);

            for (int i = 1; resultSet.next(); i++) {
                klantNr = resultSet.getInt(nrKey);
                klantPostcode = resultSet.getString(pcKey);
                klanten[i] = new Klant(klantNr, klantPostcode);
            }
            // printarr(klanten);
            return klanten;
        } catch (Exception e) {
            System.err.println("klanten error | " + e.getMessage());
        }
        return new Klant[0];
    }
    static private void printarr(Object[] arr){
        for (Object object : arr) {
            System.out.println(object);
        }
    }
}