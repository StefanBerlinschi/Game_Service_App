package other;

import static java.lang.Double.parseDouble;

public class Pair<A, B> {
    private A first;
    private B second;

    public Pair(A first, B second) {
        super();
        this.first = first;
        this.second = second;
    }

    public int hashCode() {
        int hashFirst = first != null ? first.hashCode() : 0;
        int hashSecond = second != null ? second.hashCode() : 0;

        return (hashFirst + hashSecond) * hashSecond + hashFirst;
    }

    public boolean equals(Object other) {
        if (other instanceof Pair) {
            Pair otherPair = (Pair) other;
            return
                    ((  this.first == otherPair.first ||
                            ( this.first != null && otherPair.first != null &&
                                    this.first.equals(otherPair.first))) &&
                            (  this.second == otherPair.second ||
                                    ( this.second != null && otherPair.second != null &&
                                            this.second.equals(otherPair.second))) );
        }

        return false;
    }

    public static Pair<String, String> parseStrStrPair(String str) {
        if (str.charAt(0) != '(' || str.charAt(str.length() - 1) != ')')
            return null;

        String[] ls = str.split(" | ");
        if (ls.length == 1) return null;
        String word1 = ls[0].substring(1), word2 = ls[1].substring(0, ls[1].length() - 2);

        Pair<String, String> p = new Pair<>(word1, word2);
        return p;
    }

    public static Pair<String, Double> parseStrDoubPair(String str) {
        if (str.charAt(0) != '(' || str.charAt(str.length() - 1) != ')')
            return null;

        String[] ls = str.split(" | ");
        if (ls.length == 1) return null;
        String word = ls[0].substring(1);
        Double number = parseDouble(ls[1].substring(0, ls[1].length() - 2));

        Pair<String, Double> p = new Pair<>(word, number);
        return p;
    }

    public String toString()
    {
        return "(" + first + " | " + second + ")";
    }

    public A getFirst() {
        return first;
    }

    public void setFirst(A first) {
        this.first = first;
    }

    public B getSecond() {
        return second;
    }

    public void setSecond(B second) {
        this.second = second;
    }
}

