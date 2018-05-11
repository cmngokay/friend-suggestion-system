package gokaycimen.friendsuggestionsystem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class createStudentNetworkDatabase {

    private PreparedStatement preparedStatement = null;
    Connect connect;
    private String networkPath;

    public createStudentNetworkDatabase() {
        connect = new Connect();
        //createNetworkDB();
    }

    public void createNetworkDB() {

        String line = "";
        String blankLine = "";
        BufferedReader br;
        int j = 2;//sorgu(query) (?) parametreleri

        try {
            br = new BufferedReader(new FileReader(networkPath));

            while ((line = br.readLine()) != null) {
                String query = "insert into ogrenciNetwork values (?,?,?,?,?,?,?,?,?,?,?)";
                preparedStatement = connect.getCon().prepareStatement(query);

                String[] studentFriends = line.split(",");
                preparedStatement.setString(1, studentFriends[0]);
                j = 2;

                for (int k = 1; k <= 10; k++) {
                    if (k < studentFriends.length) {
                        if ((!studentFriends[k].equals(""))) {
                            preparedStatement.setString(j, studentFriends[k]);
                            j++;

                        }
                    }
                }
                int deger = 11 - j + 1;//sorgu(?) parametrelerinin boş kısımlarına ""(boş string) yükleniyor.
                while (deger != 0) {
                    preparedStatement.setString(j, blankLine);
                    j++;
                    deger--;
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

       // listele();

    }

    private void listele() {
        //Öğrenci ad-soyadlarının olduğu dosya veritabanına aktarılıyor.
        String line = "";       
        BufferedReader br;
        String[] liste;

        try {
            br = new BufferedReader(new FileReader("ogrencilistesi.txt"));

            while ((line = br.readLine()) != null) {
                String query = "insert into ogrencilistesi values (?,?)";
                preparedStatement = connect.getCon().prepareStatement(query);

                liste = line.split(",");
                preparedStatement.setString(1, liste[0]);
                preparedStatement.setString(2, liste[1]);

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

    public void setNetworkpath(String networkPath) {
        this.networkPath = networkPath;
    }

}
