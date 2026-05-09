package conec;

import java.sql.Connection;
import java.sql.DriverManager;

public class CONECTA {

    public static Connection conectar() {
        try {
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://aws-1-us-east-1.pooler.supabase.com:6543/postgres?sslmode=require";
            String user = "postgres.vfxntsuyhzmofgdwyoxg";
            String password = "@HORRAY@!'87";

            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa");
            return con;

        } catch (Exception e) {
            System.err.println("Error de conexión: " + e.getMessage());
            return null;
        }
    }
}
