public class OffByN implements CharacterComparator {
    int number;

    public OffByN(int N) {
        number = N;
    }

    public boolean equalChars(char x, char y) {
        int diff = x - y;
        return (diff == number || diff == -number);
    }

}
