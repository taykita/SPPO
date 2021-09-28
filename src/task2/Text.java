package task2;

public class Text {
    public Text(String text) {
        this.text = text;
        pos = 0;
    }

    private final char chEOT = '\0';

    private String text;
    private int pos;

    public char nextCh() {
        if (pos < text.length()) {
            char ch = text.charAt(pos);
            pos++;
            return ch;
        } else {
            return chEOT;
        }
    }
}
