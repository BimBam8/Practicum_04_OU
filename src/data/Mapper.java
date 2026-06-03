package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;

import domein.Klant;
import domein.Vestiging;

public class Mapper {

    private static final String DATABASE_URL = "jdbc:firebirdsql://localhost:3052/Prik2Go_res_v3.fdb";
    private static final String DATABASE_USER = "sysdba";
    private static final String DATABASE_PASSWORD = "masterkey";
    private static final String DRIVERNAME = "org.firebirdsql.jdbc.FBDriver";

    public static Vestiging[] getVestigingen() {
        return new Vestiging[1];
    }

    public static Klant[] getKlanten(String vestiging) {
        return new Klant[1];
    }

}