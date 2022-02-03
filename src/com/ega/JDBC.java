package com.ega;
import java.sql.*; // pakotne, kas satur klases -> metodes, lai varētu izveidot savienojumu ar DB, un manipulēt ar ierakstiem.


public class JDBC extends Main {
    String DB = "jdbc:mysql://localhost:3306/jdbc"; // DB adrese ievietota mainīgajā, ērtākai koda parvaldībai.
    String USR = "root"; // DB lietotāja vārds
    String PASS = ""; // DB root parole
    String info = "SELECT id,teksts FROM todo"; //
    String select = "SELECT id FROM todo";
    String delall = "TRUNCATE todo;";
    String insert = "insert into todo (teksts) VALUE ('" + ievade + "');";
    String delete = "DELETE FROM todo"+" WHERE id = '"+ del +"'";
      int a; // kalpo kā tilts starp lietotāja ievadi un DB ierakstu iekš checkid metodes, lai pārbaudītu vai ir tāda vērtība DB, ko lietotājs norādījis.

//// DATU IEVADE DB

    public void insert() {

        try ( // veic iekavās norādītās darbības. Savā ziņā tests (mēģinājums)
                Connection conn = DriverManager.getConnection(DB, USR, PASS); // tiek izveidots jauns Connection obj ar nosaukumu conn, kurš saturēs ->
                //-> DriverManager klasi ar getConnection metodi un metodes parametriem
                Statement stmt = conn.createStatement()) { // Izveidots paziņojuma klases objekts ar nosaukumu stmt, kurš sevī ietver savienojuma datus un metodi

                stmt.executeUpdate(insert);// izsauc paziņojuma klases objektu, kurš ietver sevī  1.  savienojuma klasi ar metodi un metodes parametriem  ->>"stmt"; ->
            //-> 2. paša paziņojuma klases metodi "executeUpdate ar parametru (insert), insert mainīgais satur vaicājumu, kurš tiek izpildīts sql terminālī.
                System.out.println("Dati saglabāti!");// Paziņojums, ja iepriekšējās darbības ir bijušas veiksmīgas

        } catch (SQLException b) {  // ja iekš meģinājuma "try" izveidojusies kļūda, tad šī funkcija, kļūdu ieraksta mainīgajā "b" un izvada.
            b.printStackTrace();
        }

    }
//// PĀRBAUDA VAI COLLONĀ ID IR  IERAKSTI
    public void selectid() {
        try (
                Connection conn = DriverManager.getConnection(DB, USR, PASS);
                Statement stmt = conn.createStatement();
                ResultSet resultSet = stmt.executeQuery(select)) { // ResultSet objekts, kurš lasa datubāzes -> tabulas -> collonas datus pa rindām
            while (resultSet.next()) {
                ks = resultSet.getInt("id"); // ks tiek ierakstīta pēdējā, lielākā id vērtība, kas nepieciešama uzdevumu nummurācijas veidošanā.

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
//// PĀRBAUDA VAI DB ID SAKRĪT AR IEVADĪTO VĒRTĪBU del mainīgajā.
    public void checkid() {
        try (
                Connection conn = DriverManager.getConnection(DB, USR, PASS);
                Statement stmt = conn.createStatement();
                ResultSet resultSet = stmt.executeQuery(select)) {

                while (resultSet.next()) { // result set kursors pārvietojas no augšas uz leju pa rinda, izmantojot while ciklu ->
                    a = resultSet.getInt("id"); //-> un id collonas vērtība tiek ierakstīta mainīgajā a ->
                   if(del == a) {  //-> kas tiek salīdzināta ar lietotāja ievadi  ->
                      ks=a; // -> ja ievade ar ierakstu sakrīt, while cikls neturpinās, bet ja nesakrīt while cikls iekš main metodes viņu atvedīs atpakaļ šajā ciklā
                   }}

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
//// IZVADA DATUS NO ID UN TEKSTS COLLONĀM
    public void select() {
        try (
                Connection conn = DriverManager.getConnection(DB, USR, PASS);
                Statement stmt = conn.createStatement();
                ResultSet resultSet = stmt.executeQuery(info)) {
            while (resultSet.next()) {
                System.out.print(resultSet.getInt("id"));
                System.out.print(". ");
                System.out.print(resultSet.getString("teksts"));
                System.out.println("");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

//// DZĒŠ ierakstu pēc ID

    public void delete() {
        try (
                Connection conn = DriverManager.getConnection(DB, USR, PASS);
                Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(delete);
             System.out.println(" Uzdevums veiksmīgi dzēsts!");

        } catch (SQLException b) {
            b.printStackTrace();
        }


    }
//// DELETE visu tabulas saturu un ID noresetojas.

    public void deleteall() {
        try (
                Connection conn = DriverManager.getConnection(DB, USR, PASS); // tiek izveidots jauns Connection obj ar nosaukumu conn, kurš saturēs ->
                //-> DriverManager klasi ar getConnection metodi un metodes parametriem
                Statement stmt = conn.createStatement()) { // Izveidots paziņojuma klases objekts ar nosaukumu stmt, kurš sevī ietver savienojuma datus un metodi


            stmt.executeUpdate(delall); // izsauc paziņojuma klases objektu, kurš ietver sevī  1.  savienojuma klasi ar metodi un metodes parametriem  ->>"stmt"; ->
            //-> 2. paša paziņojuma klases metodi "executeUpdate ar parametru (delall), delall mainīgais satur vaicājumu, kurš tiek izpildīts sql terminālī.

            System.out.println(" Visi uzdevumi dzēsti!"); // Paziņojums, ja iepriekšējās darbības ir bijušas veiksmīgas.

        }
        catch (SQLException b) {
            b.printStackTrace();
        }

    }

}
