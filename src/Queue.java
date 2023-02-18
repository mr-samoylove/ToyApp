import java.util.ArrayDeque;

public class Queue {
    java.util.Queue<Toy> queue = new ArrayDeque<>();
    boolean removeAfterGetting = false;

    public Queue(ToysManager manager, int amount) {
        Toy toy = manager.nextToy(false);
        for (int i = 0; i < amount && toy != null; i++) {
            queue.add(toy);
            toy = manager.nextToy(false);
        }
    }

    public Queue(ToysManager manager, int amount, boolean removeAfterQueueing) {
        this.removeAfterGetting = removeAfterQueueing;
        Toy toy = manager.nextToy(removeAfterQueueing);
        for (int i = 1; i < amount && toy != null; i++) {
            queue.add(toy);
            toy = manager.nextToy(removeAfterQueueing);
        }
    }

    public Toy get() {
        if (queue.isEmpty()) {
            System.out.println("Queue is empty!");
            return null;
        } else {
            return queue.poll();
        }
    }
}
