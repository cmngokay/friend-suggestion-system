package gokaycimen.friendsuggestionsystem;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class FriendSuggestionSystem extends javax.swing.JFrame {

    private PreparedStatement preparedStatement = null;
    private PreparedStatement preparedStatement2 = null;
    Connect connect = new Connect();
    createStudentNetworkDatabase studentNetwork = new createStudentNetworkDatabase();
    createStudentProfilDatabase studentProfil = new createStudentProfilDatabase();
    Object[][] trainingTest;//Lojistik regresyon hesabının yapılacağı matris
    Object[][] testSet;
    ArrayList<String> notStudentFriends;//textbox'tan girilen öğrencinin arkadaş olmadığı kişilerin tutulduğu liste
    ArrayList<String> studentFriends = new ArrayList<String>();//textbox'tan girilen öğrencinin arkadaşlarının tutulduğu liste
    ArrayList<String> allStudentList = new ArrayList<String>();//Tüm öğrencilerin tutulduğu liste
    double[] B1 = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    double[] B = new double[16];
    Object[][] friendPossibility;//Arkadaş olması muhtemel 10 kişinin tutulduğu matris

    public FriendSuggestionSystem() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        girilenOgrenciNo = new javax.swing.JTextField();
        arkadasBulButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        ogrenciTablo = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tavsiyeTablosu = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        networkFileButton = new javax.swing.JButton();
        profilFileButton = new javax.swing.JButton();
        studentListButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Arkadaş Öneri Sistemi ");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        arkadasBulButton.setText("Arkadaş Bul");
        arkadasBulButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arkadasBulButtonActionPerformed(evt);
            }
        });

        ogrenciTablo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {""},
                {""},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Öğrenci Numarası"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(ogrenciTablo);

        tavsiyeTablosu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Öğrenci Numarası", "Ad", "Soyad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tavsiyeTablosu);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel1.setText("Arkadaşlık için tavsiye edilen kişiler :");

        networkFileButton.setText("NetworkFile");
        networkFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                networkFileButtonActionPerformed(evt);
            }
        });

        profilFileButton.setText("ProfilFile");
        profilFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profilFileButtonActionPerformed(evt);
            }
        });

        studentListButton.setText("Öğrencileri Listele");
        studentListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentListButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(girilenOgrenciNo, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(arkadasBulButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(studentListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(76, 76, 76)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(profilFileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(networkFileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(girilenOgrenciNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(arkadasBulButton, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(profilFileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(networkFileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(studentListButton)
                        .addGap(15, 15, 15))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void studentList() {
        //ogrenciNetwork tablosundaki öğrenciler table üzerinden listeleniyor.
        String query = "select ogrenciNo from ogrenciNetwork";
        String[][] studentList = new String[90][2];

        int i = 0;
        try {
            preparedStatement = connect.getCon().prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String studentNumber = rs.getString("ogrenciNo");
                allStudentList.add(studentNumber);
                studentList[i][1] = studentNumber;
                studentList[i][0] = String.valueOf(i + 1);
                i++;
            }

            TableModel tabloModeli = new DefaultTableModel(
                    studentList,
                    new String[]{"", "Öğrenci Numarası"}
            );

            ogrenciTablo.setModel(tabloModeli);

        } catch (SQLException ex) {
            System.out.println("Listeleme İşlemi Başarısız :( ");
            Logger.getLogger(FriendSuggestionSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void findFriend() {
        //Textbox tan numarası girilen öğrencinin arkadaşları ve arkadaş olmadığı kişiler (network üzerinden) listeye atılıyor.
        String enteredStudent = girilenOgrenciNo.getText();

        try {
            String query = "select arkadas1,arkadas2,arkadas3,arkadas4,arkadas5,arkadas6,arkadas7,"
                    + "arkadas8,arkadas9,arkadas10 from ogrencinetwork where ogrenciNo = ?";
            preparedStatement = connect.getCon().prepareStatement(query);
            preparedStatement.setString(1, enteredStudent);
            String bos = "";
            ResultSet rs = preparedStatement.executeQuery();
            int i = 1;
            while (rs.next()) {
                while (i <= 10) {
                    if (!rs.getString(i).equals(bos)) {
                        studentFriends.add(rs.getString(i));
                    }
                    i++;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(FriendSuggestionSystem.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Textbox'tan numarası girilen öğrencinin arkadaş olmadığı kişiler notStudentFriends listesine atıldı.
        notStudentFriends = new ArrayList<String>(allStudentList);
        notStudentFriends.removeAll(studentFriends);
        notStudentFriends.remove(enteredStudent);

        /* for (int i = 0; i < studentFriends.size(); i++) {
            System.out.println(studentFriends.get(i));
        }*/
        createMatrix();

    }

    private void createMatrix() {
        int i = 0;
        int rows = 0;
        int matrixSize = (notStudentFriends.size() / 2) + studentFriends.size();
        System.out.println("matris boyutu : " + matrixSize);
        trainingTest = new Object[matrixSize][17];
        try {

            while (i < studentFriends.size()) {
                String query = "select A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15 from ogrenciprofil where ogrenciNo = ?";
                preparedStatement = connect.getCon().prepareStatement(query);
                preparedStatement.setString(1, studentFriends.get(i));
                trainingTest[rows][0] = studentFriends.get(i);
                ResultSet rs = preparedStatement.executeQuery();
                int columns = 1;
                while (rs.next()) {
                    while (columns <= 15) {
                        trainingTest[rows][columns] = rs.getInt(columns);
                        trainingTest[rows][16] = 1;
                        columns++;
                    }
                }
                i++;
                rows++;

            }

        } catch (SQLException ex) {
            Logger.getLogger(FriendSuggestionSystem.class.getName()).log(Level.SEVERE, null, ex);
        }

        int j = 0;
        try {
            System.out.println("not friends size :" + notStudentFriends.size());
            System.out.println("not friends size/2  ->" + (notStudentFriends.size() / 2));
            while (j < (notStudentFriends.size() / 2)) {
                String query2 = "select A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15 from ogrenciprofil where ogrenciNo = ?";
                preparedStatement2 = connect.getCon().prepareStatement(query2);
                preparedStatement2.setString(1, notStudentFriends.get(j));
                trainingTest[rows][0] = notStudentFriends.get(j);
                ResultSet rs2 = preparedStatement2.executeQuery();
                int column = 1;
                while (rs2.next()) {
                    while (column <= 15) {
                        trainingTest[rows][column] = rs2.getInt(column);
                        trainingTest[rows][16] = 0;
                        column++;
                    }
                }
                j++;
                rows++;

            }

        } catch (SQLException ex) {
            Logger.getLogger(FriendSuggestionSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("row:" + rows);
        int newRows = notStudentFriends.size() / 2 + 1;
        int size = notStudentFriends.size() - newRows;

        System.out.println("size:" + size);
        testSet = new Object[size][17];
        int k = 0;
        try {
            while (newRows < (notStudentFriends.size())) {
                String query3 = "select A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15 from ogrenciprofil where ogrenciNo = ?";
                preparedStatement2 = connect.getCon().prepareStatement(query3);
                preparedStatement2.setString(1, notStudentFriends.get(newRows));
                testSet[k][0] = notStudentFriends.get(newRows);
                ResultSet rs3 = preparedStatement2.executeQuery();
                int column = 1;
                while (rs3.next()) {
                    while (column <= 15) {
                        testSet[k][column] = rs3.getInt(column);
                        testSet[k][16] = 0;
                        column++;
                    }
                }

                k++;
                newRows++;

            }
        } catch (SQLException ex) {
            Logger.getLogger(FriendSuggestionSystem.class.getName()).log(Level.SEVERE, null, ex);
        }

        studentFriends.clear();

        logisticRegression();
    }

    private double sigmoid(int index, boolean b) {
        double count = 0;

        if (b == true) {
            for (int j = 1; j <= 15; j++) {
                count += ((double) ((int) trainingTest[index][j]) * B1[j]);
            }
            count += B1[0];
        } else {
            for (int j = 1; j <= 15; j++) {
                count += ((double) ((int) testSet[index][j]) * B1[j]);
            }
            count += B1[0];
        }

        return 1.0 / (1.0 + Math.exp(-count));

    }

    private double operation() {
        int N = trainingTest.length;
        double count = 0;
        for (int i = 0; i < N; i++) {
            count += (sigmoid(i, true) - (double) ((int) trainingTest[i][16]));
        }

        return (1.0 / N) * (count);
    }

    private double operation2() {
        int N = trainingTest.length;
        double count = 0;

        for (int i = 0; i < N; i++) {
            double temp = (sigmoid(i, true) - (double) ((int) trainingTest[i][16]));
            for (int j = 1; j <= 15; j++) {
                count += (temp * (double) ((int) trainingTest[i][j]));
            }

        }
        return (1.0 / N) * (count);
    }

    private void logisticRegression() {
        int maxIteration = 100;
        double stepSize = 0.001;
        for (int i = 0; i < 16; i++) {
            B1[i] = 1;
        }

        for (int i = 0; i < maxIteration; i++) {
            B[0] = B1[0] - stepSize * operation();
            for (int j = 1; j <= 15; j++) {
                B[j] = B1[j] - stepSize * operation2();
            }
            for (int h = 0; h < 16; h++) {
                B1[h] = B[h];
                //  System.out.println("B1["+h+"] :"+B1[h]);
            }
        }

        //Arkadaş olmayanların sigmoid değerleri ve numaraları ; friendPossibility[][] dizisine atılıyor.
        friendPossibility = new Object[testSet.length][2];
        for (int i = 0; i < testSet.length; i++) {
            friendPossibility[i][0] = testSet[i][0];
            friendPossibility[i][1] = sigmoid(i, false);
        }

        recommendFriend();

    }

    private void recommendFriend() {
        double temp;
        Object temp2;
        String[][] suggestedList = new String[10][2];
        //friendPossibility[][] dizisindeki sigmoid değerleri büyükten-küçüğe sıralanıyor !(bubble sort)
        for (int i = 0; i < friendPossibility.length - 1; i++) {

            for (int j = 0; j < friendPossibility.length - i - 1; j++) {

                if ((double) friendPossibility[j][1] < (double) friendPossibility[j + 1][1]) {

                    temp = (double) friendPossibility[j][1];
                    temp2 = friendPossibility[j][0];
                    friendPossibility[j][1] = friendPossibility[j + 1][1];
                    friendPossibility[j][0] = friendPossibility[j + 1][0];

                    friendPossibility[j + 1][1] = temp;
                    friendPossibility[j + 1][0] = temp2;
                }

            }

        }

        String query = "select * from ogrencilistesi where ogrenciNumarasi = ?";

        int i = 0;
        try {
            preparedStatement = connect.getCon().prepareStatement(query);

            for (int j = 0; j < 10; j++) {
                preparedStatement.setString(1, (String) friendPossibility[j][0]);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    suggestedList[i][0] = rs.getString("ogrenciNumarasi");
                    suggestedList[i][1] = rs.getString("ogrenciAdSoyad");
                    i++;
                }
            }

            TableModel tabloModeli = new DefaultTableModel(
                    suggestedList,
                    new String[]{"Numarası", "Ad Soyad"}
            );

            tavsiyeTablosu.setModel(tabloModeli);

        } catch (SQLException ex) {
            System.out.println("Listeleme İşlemi Başarısız :( ");
            Logger.getLogger(FriendSuggestionSystem.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private void arkadasBulButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arkadasBulButtonActionPerformed
        int value = allStudentList.indexOf(girilenOgrenciNo.getText());
        if (value != -1) {
            findFriend();
        } else {
            JOptionPane.showMessageDialog(null, "Girilen Numara Listede Yok !");
        }

    }//GEN-LAST:event_arkadasBulButtonActionPerformed

    private void networkFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_networkFileButtonActionPerformed
        //ogrenciNetwork.csv dosyası alınıyor ve dosya yolu set metoduyla bildiriliyor.
        //cvsReader\datafile -> dosya konumu
        JFileChooser fileChooser = new JFileChooser("datafile");    
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String csvpath = selectedFile.getAbsolutePath();

            studentNetwork.setNetworkpath(csvpath);
            studentNetwork.createNetworkDB();
        }

    }//GEN-LAST:event_networkFileButtonActionPerformed

    private void profilFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profilFileButtonActionPerformed
        //ogrenciProfil.csv dosyası alınıyor ve dosya yolu set metoduyla bildiriliyor.
        JFileChooser fileChooser = new JFileChooser("datafile");
       
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String csvpath = selectedFile.getAbsolutePath();

            studentProfil.setProfilpath(csvpath);
            studentProfil.createProfilDB();
        }
    }//GEN-LAST:event_profilFileButtonActionPerformed

    private void studentListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentListButtonActionPerformed

        studentList();
    }//GEN-LAST:event_studentListButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        //Program ekranı kapatıldığında veritabanındaki tablolar boşaltılıyor.
        String query = "delete from ogrencinetwork";
        String query2 = "delete from ogrenciprofil";
        try {
            preparedStatement = connect.getCon().prepareStatement(query);
            preparedStatement2 = connect.getCon().prepareStatement(query2);
            preparedStatement.executeUpdate();
            preparedStatement2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FriendSuggestionSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosing

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FriendSuggestionSystem().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton arkadasBulButton;
    private javax.swing.JTextField girilenOgrenciNo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton networkFileButton;
    private javax.swing.JTable ogrenciTablo;
    private javax.swing.JButton profilFileButton;
    private javax.swing.JButton studentListButton;
    private javax.swing.JTable tavsiyeTablosu;
    // End of variables declaration//GEN-END:variables
}
