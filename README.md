## Информация о проекте
Необходимо написать проект для розыгрыша в магазине игрушек.  
Функционал должен содержать добавление новых игрушек и задания веса для выпадения игрушек.

## Реализация
- Каждая игрушка (class `Toy`) содержит id, name и шанс выпадения от 1 до 9 включительно.
- Можно добавлять новые игрушки в общий список (файл `data.csv`). За это отвечает метод `appendToy` класса `ToysManager`.
- С помощью `ArrayDeque` (class `Queue`) формируется очередь заданной длины по принципу случайного выбора с учетом шанса выпадения игрушки.
- Класс `Queue` имеет перегруженный конструктор, позволяющий удалять игрушку из общего списка при ее добавлении в очередь.
- Метод `get` класса `Queue` (это часть задания) возвращает следующую игрушку из сформированной очереди.
- Класс `ToysManager` помимо прочего записывает полученную игрушку в файл `prises.txt`