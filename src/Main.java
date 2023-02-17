public class Main {
    public static void main(String[] args) {

        toysManager manager = new toysManager();

        // наполняем список призов тестовыми рандомными значениями
        manager._debugGeneratePrises(30);

        // вручную добавляем в список одну игрушку
        Toy toy_test = new Toy(31, "toy_test", 3);
        manager.appendToy(toy_test);

    }
}