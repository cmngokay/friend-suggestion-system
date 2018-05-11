package gokaycimen.friendsuggestionsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

    private String user_name = "root";
    private String password = "";

    private String db_name = "arkadasbulmaprojesi";
    private String host = "localhost";

    private int port = 3306;

    private Connection con = null;

    public Connect() {
        //jdbc:mysql://localhost/3306/arkadasbulmaprojesi;
        String url = "jdbc:mysql://" + host + ":" + port + "/" + db_name + "?UseUnicode=true&characterEncoding=utf8";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver Bulunamadı :( !");
        }

        try {
            con = (Connection) DriverManager.getConnection(url, user_name, password);
            System.out.println("Bağlantı Başarılı :)");
        } catch (SQLException ex) {
            System.out.println("Bağlantı Başarısız :( !");
        }
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

}
