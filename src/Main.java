public class Main {
    public static void main(String[] args) {

        /* наполняем список призов тестовыми рандомными значениями */
        // ToysManager._debugGeneratePrises(30);

        ToysManager manager = new ToysManager();

        /* вручную добавляем в список одну игрушку */
        // Toy toy_test = new Toy(31, "toy_test", 9);
        // manager.appendToy(toy_test);

        /* Создаем очередь заданной длины из игрушек.
           Доп параметр конструктора позволит удалять игрушку из базы при ее добавлении в очередь */
        // Queue queue2 = new Queue(manager, 10, true);
        Queue queue1 = new Queue(manager, 10);
        for (int i = 0; i < 10; i++)
            manager.appendResults(queue1.get());
    }
}