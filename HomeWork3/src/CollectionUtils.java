import java.util.*;

public class CollectionUtils {
    public static<T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static <T> List<T> newArrayList() {
        return new ArrayList<>();
    }

    public static <T> int indexOf(List<? extends T> source, Object o) {
        return source.indexOf(o);
    }

    public static <T> List<T> limit(List<? extends T> source, int size) {
        return new ArrayList<>(source.subList(0, Math.min(size, source.size())));
    }

    public static <T> void add(List<? super T> source, T o) {
        source.add(o);
    }

    public static <T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
        removeFrom.removeAll(c2);
    }

    public static <T> boolean containsAll(List<? extends T> c1, List<? extends T> c2) {
        return new HashSet<>(c1).containsAll(c2);
    }

    public static <T> boolean containsAny(List<? extends T> c1, List<? extends T> c2) {
        for (T element : c2) {
            if (c1.contains(element)) {
                return true;
            }
        }
        return false;
    }

    public static <T> List range(List<? extends T> list, T min, T max) {
        List<? super T> copyList = new ArrayList<>(list);
        copyList.sort(Comparator.comparingInt((Object o) -> o.hashCode()));
        if (copyList.indexOf(min) < copyList.indexOf(max)) {
            return copyList.subList(copyList.indexOf(min), copyList.indexOf(max));
        } else {
            return newArrayList();
        }
    }

    public static <T> List range(List<? extends T> list, T min, T max, Comparator<? super T> comparator) {
        List<? super T> copyList = new ArrayList<T>(list);
        list.sort(comparator);
        if (copyList.indexOf(min) < copyList.indexOf(max)) {
            return copyList.subList(copyList.indexOf(min), copyList.indexOf(max));
        } else {
            return newArrayList();
        }
    }
}
