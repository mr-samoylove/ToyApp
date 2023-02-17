import java.util.Arrays;
import java.util.PriorityQueue;

public class QueueFormer {
    PriorityQueue<Toy> queue = new PriorityQueue<>();

    public QueueFormer(Toy... toys){
        queue.addAll(Arrays.asList(toys));
    }
}
