public class RabinKarpAlgorithm {


    /**
     * This algorithm returns the starting index of the matching substring.
     * This method will return -1 if no matching substring is found, or if the input is invalid.
     */
    public static int rabinKarp(String input, String pattern) {
        int inputSize = input.length();
        int patternSize = pattern.length();
        int hashpattern = RabinKarpAlgorithm.hashString(pattern);
        RollingString rlPattern = new RollingString(pattern, patternSize);
        RollingString rl = new RollingString(input.substring(0, patternSize), patternSize);

        for (int i = 0; i <= inputSize - patternSize; i++) {
            if (rl.hashCode() != hashpattern) {
                rl.addChar(input.charAt(i + patternSize));
            } else if (rl.equals(rlPattern)) {
                return i;
            }
        }
        return -1;
    }

    private static int hashString(String s) {
        int hash = 0;
        for (int i = 0; i < s.length(); i++) {
            hash = (hash + s.charAt(i)) * RollingString.UNIQUECHARS;
        }
        return hash % RollingString.PRIMEBASE;
    }

}
