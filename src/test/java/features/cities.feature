# language: ru
@all
Функция: проверка, что новое слово начинается на последнюю букву в предыдущем слове
  @success
  Сценарий: Успешная проверка
    Дано Требуется проверить слова Астрахань, Москва
    Когда Вызывается функция проверки со словами Астархань, Москва
    Тогда возвращается true
