import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

public class ToysManager {
    private final NavigableMap<Integer, Toy> allToys = new TreeMap<>();
    private int totalWeight = 0;
    private final String pathToData = "data.csv";
    private final String pathToResults = "prises.txt";
    private final Random r = new Random();

    public ToysManager() {
        setMap();
    }

    private void setMap() {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(this.pathToData), StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(",");
                this.totalWeight += Integer.parseInt(split[2]);
                allToys.put(this.totalWeight, new Toy(Integer.parseInt(split[0]), split[1], Integer.parseInt(split[2])));
            }
        } catch (IOException e) {
            System.out.println("There's been an IO exception while dealing with datafile");
            System.out.println(e.getMessage());
        }
    }

    public Toy nextToy(boolean removeAfterGetting) {
        if (allToys.isEmpty()) return null;

        int randInt = r.nextInt(totalWeight) + 1;
        Toy chosenToy = allToys.ceilingEntry(randInt).getValue();
        if (removeAfterGetting) {
            removeToy(chosenToy);
        }
        return chosenToy;
    }

    public void appendToy(Toy toy) {
        if (toy == null) return;

        this.totalWeight += toy.getChanceValue();
        allToys.put(this.totalWeight, toy);

        append(toy, this.pathToData);
    }

    public void appendResults(Toy toy) {
        if (toy == null) return;

        append(toy, this.pathToResults);
    }

    private void append(Toy toy, String path) {
        try (Writer writer = Files.newBufferedWriter(Paths.get(path), StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
            writer.append(toy.toString());
            writer.append('\n');
        } catch (IOException e) {
            System.out.println("There's been an IO exception in append method");
            e.printStackTrace();
        }
    }

    private void removeToy(Toy toy) {
        try (Writer writer = Files.newBufferedWriter(Paths.get(this.pathToData), StandardCharsets.UTF_8)) {
            for (Toy t : allToys.values()) {
                if (t.equals(toy)) continue;
                writer.write(t.toString() + '\n');
            }
        } catch (IOException e) {
            System.out.println("There's been an IO exception in method removeToy");
            e.printStackTrace();
        }

        allToys.clear();
        totalWeight = 0;
        setMap();
    }

    public static void _debugGeneratePrises(int quantity) {
        String[] data = new String[quantity];
        Random r = new Random();

        for (int i = 1; i <= quantity; i++) {
            data[i - 1] = String.join(",", String.valueOf(i), "Toy" + i, String.valueOf(r.nextInt(9) + 1));
        }

        try (Writer writer = Files.newBufferedWriter(Paths.get("data.csv"), StandardCharsets.UTF_8)) {
            writer.write(String.join("\n", data) + '\n');
        } catch (IOException e) {
            System.out.println("There's been an IO exception in method generatePrises");
            e.printStackTrace();
        }
    }
}
