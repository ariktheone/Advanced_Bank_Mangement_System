import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkerCSVManager {

    public static List<Worker> loadWorkers(String filename) {
        List<Worker> workers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String username = parts[0];
                String password = parts[1];
                workers.add(new Worker(username, password));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workers;
    }
}
