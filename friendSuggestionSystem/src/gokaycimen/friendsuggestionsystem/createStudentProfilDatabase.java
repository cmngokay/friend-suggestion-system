package gokaycimen.friendsuggestionsystem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class createStudentProfilDatabase {

    private PreparedStatement preparedStatement = null;
    Connect connect;
    private String profilPath;

    public createStudentProfilDatabase() {
        connect = new Connect();
        // createProfilDB();
    }

    public void createProfilDB() {

        String line = "";
        BufferedReader br;
        int j = 2;//sorgu(query) (?) parametreleri

        try {
            br = new BufferedReader(new FileReader(profilPath));

            while ((line = br.readLine()) != null) {
                String query = "insert into ogrenciprofil values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                preparedStatement = connect.getCon().prepareStatement(query);

                String[] studentProfilValues = line.split(",");
                preparedStatement.setString(1, studentProfilValues[0]);
                j = 2;

                for (int k = 1; k <= 15; k++) {
                    preparedStatement.setInt(j, Integer.parseInt(studentProfilValues[k]));
                    j++;
                }

                preparedStatement.executeUpdate();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(createStudentNetworkDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(createStudentNetworkDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(createStudentNetworkDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setProfilpath(String profilpath) {
        this.profilPath = profilpath;
    }
}
