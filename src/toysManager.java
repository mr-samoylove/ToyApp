import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Random;

public class toysManager {
    ArrayList<Toy> allToys = new ArrayList<>();
    private final String path = "prises.csv";

    public toysManager() {
        try(BufferedReader reader = Files.newBufferedReader(Paths.get(this.path), StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] specs = line.split(",");
                allToys.add(new Toy(Integer.parseInt(specs[0]), specs[1], Integer.parseInt(specs[2])));
            }
        } catch (IOException e) {
            System.out.println("There's been an IO exception in toysManager constructor");
        }
    }

    public void appendToy(Toy toy) {
        try (Writer writer = Files.newBufferedWriter(Paths.get(this.path), StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
            writer.append(String.join(",", String.valueOf(toy.getId()), toy.getName(), String.valueOf(toy.getChanceValue())));
            writer.append('\n');
        } catch (IOException e) {
            System.out.println("There's been an IO exception in method appendPrise");
        }
    }

    public void _debugGeneratePrises(int quantity) {
        String[] data = new String[quantity];
        Random r = new Random();

        for (int i = 1; i <= quantity; i++){
            data[i - 1] = String.join(",", String.valueOf(i), "Toy" + i, String.valueOf(r.nextInt(9) + 1));
        }

        try (Writer writer = Files.newBufferedWriter(Paths.get(this.path), StandardCharsets.UTF_8)) {
            writer.write(String.join("\n", data) + '\n');
        } catch (IOException e) {
            System.out.println("There's been an IO exception in method generatePrises");
        }
    }
}
