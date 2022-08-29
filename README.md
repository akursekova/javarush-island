# javarush-island  

## Краткое описание  

Приложение симулирует жизнь животных и растений на острове размером 100х20 клеток. 
Каждая локация заполнена растительностью, хищниками и травоядными. Жизнь на острове длится 10 дней. 
Ниже более подробно описаны действия, которые совершают животные каждый день жизни на острове. 

Животные могут:  
- передвигаться: каждый тип животного имеет свою скорость передвижения по острову. 
- есть растения и/или других животных (если в их локации есть подходящая еда),
- размножаться (при наличии пары в текущей локации),
- умирать от голода или быть съеденными.

Растения могут:
- восстанавливаться за ночь перед новым днем. 

Запуск проекта:  

>java -jar ./javarush-island.jar 

# Краткое описание классов

В корневом пакете проекта `ru.javarush.akursekova.islandtask` находится класс Application, содержащий в себе точку входа в приложение.  

В классе Application вызывается метод run() объекта класса ProcessManager, который управляет основными процессами жизни на острове:
- движение животных
- уменьшение их сытости после передвижения
- употребление пищи
- размножение
- восстановление растительности

В пакете `animals` содержатся пакеты, описывающие иерархию классов животных:

- `abstracts`: пакет с абстрактными классами: 
    - `Animal`: описывает общие поля и методы, принадлежащие всем животным, наследующим этот класс.
      - метод `reduceFullness`: уменьшает сытость животного на четверть от максимальной сытости после того, как животное сделало ход.
      - метод `increaseFullness`: увеличивает сытость животного на количество килограмм, сколько весило съеденное животное или увеличивает сытость животного дл максимума, если вес съеденного животного велик.
      - метод `generateDirectionsToMove`: генерирует направления движения для будущего хода животного.
    - `Carnivore`: класс, наследуемый всеми хищниками. 
    - `Herbivore`: класс, наследуемый всеми травоядными.
- `carnivore`: пакет с классами, описывающих хищных животных. 
- `herbivore`: пакет с классами, описывающих травоядных животных.
- `plants`: пакет с классом, описывающий растительность острова.
- `Viable`: интерфейс, используемый как маркер, с целью объединить в общий список растения и животных.

В пакете `settings` содержатся два класса:
- `FoodAndProbabilityRules`: класс, который хранит информацию: каких животных (или растения) может съесть каждое животное и вероятность.
- `GameSettings`: класс, который хранит настройки острова: длину, ширину, количество дней жизни на острове.

Также в корневом пакете проекта `ru.javarush.akursekova.islandtask` находится класс `Island`, хранящий в себе:
- внутренний класс `Location`, который хранит в себе:
  - Позицию на острове
  - Список всех животных, находящихся в текущей локации
  - Map, в которой хранится количество животных по классам
- настройки острова: его длину и ширину
- информацию о том, какое максимальное количество животных каждого вида может находиться в каждой локации
- информацию о максимуме растений в каждой локации

В классе `Island` описаны следующие методы: 
- `moveAnimals`: метод, двигающий животных по локациям острова.  

Каждое животное за ход проходит количество клеток, равное значению поля `speed`.  
Далее рандомом генерируются числа по количеству, равному скорости животного в периоде от 1 до 4 включительно.  
Каждому числу соответствует определенное направление, куда будет перемещаться животное: 
- 1 = лево
- 2 = право
- 3 = вверх
- 4 = вниз  

>**Например:**  
>Скорость Волка = 3.  
>Рандомом сгенерируется 3 числа, например: 3, 4, 1.  
>Это означает, что за один ход волк из своей текущей локации перейдет на локацию 
>- вверх
>- вниз (вернется на исходную локацию) и 
>- влево.

Метод `moveAnimals` использует вспомогательные методы: 

- `buildBorderAroundIsland`: выстраивает по периметру острова "границу" и заполняет ее значением null. Если животное для следующего хода выбрало локацию, которая является границей, ход не будет совершен животным и оно останется на своей текущей локации.
- `locationIsOnBorder`: метод проверяет, является ли локация граничной или нет.
- `animalAmountExceedsLimit`: метод проверяет количество животных в локации, в которую хочет переместиться животное. Если лимит будет превышен, то животное не совершит ход и останется на своей текущей локации.


- `feedAnimals`: метод, описывающий как питаются животные.
В методе проверяется, может ли одно животное съесть другое животное, находящееся на той же локации. Будет ли животное съедено или нет определяет генератор случайных чисел.  

>**Например:**  
>Вероятность, что Волк съест Козу = 60. Генерируется случайное число в периоде от 0 до 100 включительно.  
>- Если сгенерированное число меньше или равно вероятности быть съеденным (например, 50), то Волк съедает Козу. 
>- Если сгенерированное число больше вероятности быть съеденным (например, 80), то Волк не съедает Козу.

Если одно животное съедает другое животное, то съеденное животное умирает (удаляется из локации), а животному, которое поело, увеличивают сытость. 
Не зависимо от того удалось ли животному поесть или нет, этому животному проставляется флаг TriedToEat = true. Животное не может повторно поесть до следующего дня.


Также в корневом пакете проекта `ru.javarush.akursekova.islandtask` находится класс  
- `IslandInitialization`, который создает животных (хищников и травоядных) во всех локациях на острове.  
Сколько животных каждого типа будет создано на каждой локации определяется генератором случайных чисел. Рандом  не превышает максимум, установленный для каждого класса животного.  
- `RandomNumberGenerator`: генерирует рандомные числа  в указанном периоде
- `Population`: описывает Map, которая хранит пары класс - максимальное количество животных в локации.  

***Замечание***  
_На данный момент класс создан, но нигде не используется. Подобная Map также хранится в классе `Island`._

- `PopulationCounter`: класс, который калькулирует количество животных на острове, для последующего использования этой информации в отображении статистики. 
- `Position`: класс, описывающий координаты на острове.
- `ConsoleWriter`: класс, предназначенный для вывода в консоль информации о количестве животных, находящихся а острове. _Некоторые метода данного класса находятся на стадии разработки._ 
