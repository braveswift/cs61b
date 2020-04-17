package byow.Core;

public class StringInput implements Input {
    private String input;
    private int index;

    public StringInput(String s) {
        index = 0;
        input = s;
    }

    public char getNextKey() {
        char returnChar = Character.toUpperCase(input.charAt(index));
        index += 1;
        return returnChar;
    }

    public boolean hasNextInput() {
        return index < input.length();
    }
}
