# Домашнее задание №3 Обобщенные классы – Generics

## Задача 1
Параметризовать CountMap и реализовать его.
    
    public interface CountMap {
      // добавляет элемент в этот контейнер.
      void add(Object o);
  
      //Возвращает количество добавлений данного элемента
      int getCount(Object o);
  
      //Удаляет элемент и контейнера и возвращает количество его добавлений(до удаления)
      int remove(Object o);
  
      //количество разных элементов
      int size();
  
      //Добавить все элементы из source в текущий контейнер, 
      // при совпадении ключей,     суммировать значения
      void addAll(CountMap source);
  
      //Вернуть java.util.Map. ключ - добавленный элемент, 
      // значение - количество его добавлений
      Map toMap();
  
      //Тот же самый контракт как и toMap(), только всю информацию записать в destination
      void toMap(Map destination);
    }

## Задача 2
Параметризовать методы, используя правило PECS, и реализовать их.

    public class CollectionUtils {
      public static<T> void addAll(List<? extends T> source, List<? super T> destination) {
          destination.addAll(source);
      }
  
      public static List newArrayList() { }
  
      public static int indexOf(List source, Object o) { }
  
      public static List limit(List source, int size) { }
  
      public static void add(List source, Object o) { }
  
      public static void removeAll(List removeFrom, List c2) { }
  
      public static boolean containsAll(List c1, List c2) { }
  
      public static boolean containsAny(List c1, List c2) { }
  
      public static List range(List list, Object min, Object max) { }
  
      public static List range(List list, Object min, Object max, Comparator comparator) { }
    }

## Решения и пояснения
Generics в Java — это механизм, который позволяет создавать классы, интерфейсы и методы, способные работать с различными типами данных, обеспечивая при этом безопасность типов. С использованием generics, код становится более обобщенным и переиспользуемым.

Пример:

    //Пример без использования generics
    class Box {
        private Object value;
    
        public void setValue(Object value) {
            this.value = value;
        }
    
        public Object getValue() {
            return value;
        }
    }
    
    // Использование без generics
    Box integerBox = new Box();
    integerBox.setValue(42);
    int intValue = (int) integerBox.getValue(); // Необходимо явное приведение типов
    
    // Пример с использованием generics
    class GenericBox<T> {
        private T value;
    
        public void setValue(T value) {
            this.value = value;
        }
    
        public T getValue() {
            return value;
        }
    }
    
    // Использование с generics
    GenericBox<Integer> integerBox = new GenericBox<>();
    integerBox.setValue(42);
    int intValue = integerBox.getValue(); // Не требуется явное приведение типов

В примере с generics (GenericBox), мы создаем обобщенный класс, который может работать с любым типом данных (T). Это делает код более безопасным с точки зрения типов, так как компилятор проверяет правильность использования типов на этапе компиляции, и не требует явного приведения типов при использовании объектов этого класса.


### Задача 1
Параметризовал интерфейс CountMap. Далее создал класс CountMapImpl, который имплементирует наш интерфейс CountMap.
Для решения задачи логично выбрать коллекцию Map<T, Integer>. Ключом является объект, а значением кол-во добавлений этого объекта в коллекцию.
Реализовал все методи согласно заданному описанию.

### Задача 2
PECS - это аббревиатура, которая означает "Producer extends, Consumer super". Это правило используется при работе с generics в Java и помогает определить, когда использовать ограничивающие ключевые слова extends и super в объявлении параметров типов.

**Producer extends (? extends T):**
- Используется, когда вы получаете значения из структуры данных (производитель). Например, когда вы читаете из списка.
- Позволяет получать элементы из коллекции, но не добавлять новые элементы.
- Если у нас есть объект коллекции (например, список), содержащий элементы типа T или его подтипы (T или какие-то его наследники), и мы хотим итерироваться по этой коллекции, мы хотим быть уверенными, что элементы, которые мы получаем, являются как минимум типом T.
- С использованием extends мы гарантируем, что полученные элементы будут как минимум типа T (или его подтипы), что обеспечивает безопасное чтение из коллекции.

        public static <T> T copyFirst(List<? extends T> source) {
            if (!source.isEmpty()) {
                return source.get(0);
            }
            return null;
        }

**Consumer super (? super T):**
- Используется, когда вы добавляете значения в структуру данных (потребитель). Например, когда вы добавляете в список.
- Позволяет добавлять элементы в коллекцию, но не гарантирует точный тип элементов.
- Если у нас есть объект коллекции, и мы хотим добавить элемент в эту коллекцию, нам не важно, какого типа точно элементы находятся в коллекции. Мы хотим быть уверенными, что мы можем безопасно добавить элемент любого типа, который является супертипом T.
- С использованием super мы гарантируем, что мы можем безопасно добавить элемент любого типа, который является супертипом T.

        public static <T> void addToLast(List<? super T> destination, T value) {
            destination.add(value);
        }

  Таким образом, extends используется при чтении (producer), чтобы обеспечить безопасность типов при получении элементов из коллекции, а super используется при записи (consumer), чтобы обеспечить безопасность типов при добавлении элементов в коллекцию. Эти правила делают код более устойчивым к ошибкам типов и повышают безопасность в процессе использования обобщенных типов данных. PECS напоминает, что если вы производите значения (читаете), то используете extends, и если вы потребляете значения (записываете), то используете super. Понимание, почему используется extends при чтении (producer) и super при записи (consumer), связано с принципами полиморфизма и безопасности типов в системе типов Java.

Используя правило PECS параметризовал методы класса CollectionUtils и реализовал их.
