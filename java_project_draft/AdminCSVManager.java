import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AdminCSVManager {
    public static Admin loadAdmin(String filename) {
        Admin admin = null;
        try (FileReader reader = new FileReader(filename);
             BufferedReader br = new BufferedReader(reader)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String username = parts[0];
                String password = parts[1];
                admin = new Admin(username, password);
                break; // Assuming there's only one admin in the file
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return admin;
    }
}
