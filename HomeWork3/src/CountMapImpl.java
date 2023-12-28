import java.util.HashMap;
import java.util.Map;

public class CountMapImpl<T> implements CountMap<T>{

    private final Map<T, Integer> map = new HashMap<>();

    @Override
    public void add(T o) {
        map.put(o, map.getOrDefault(o, 0) + 1);
    }

    @Override
    public int getCount(T o) {
        return map.getOrDefault(o, 0);
    }

    @Override
    public int remove(T o) {
        int count = getCount(o);
        map.remove(o);
        return count;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void addAll(CountMap<T> source) {
        Map<T, Integer> newMap = source.toMap();
        for (Map.Entry<T, Integer> entry : newMap.entrySet())
            map.put(entry.getKey(), map.getOrDefault(entry.getKey(), 0) + entry.getValue());
    }

    @Override
    public Map<T, Integer> toMap() {
        return new HashMap<>(map);
    }

    @Override
    public void toMap(Map<T, Integer> destination) {
        destination.putAll(map);
    }
}