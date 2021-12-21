package Database.UserInfo;

public class Triple<T, U, V> {

    private T word;
    private U arrayIndex;
    private V distance;

    public void add(T word, U arrayIndex, V distance) {
        this.word = word;
        this.arrayIndex = arrayIndex;
        this.distance = distance;
    }

    public T getWord() { return word; }
    public U getArrayIndex() { return arrayIndex; }
    public V getDistance() { return distance; }
}