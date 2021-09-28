package task1.hash;

public class PolyHash implements Hash {
    public PolyHash(int length) {
        this.LENGTH = length;
    }

    private final int LENGTH;
    private static final int p = 53;

    @Override
    public int hash(String name) {

        int hash = 0;
        long pPow = 1;

        for (int i = 0; i < name.length(); i++) {
            hash += (name.charAt(i) - 'a' + 1) * pPow;
            pPow *= p;
        }

        return Math.abs(hash % LENGTH);
    }
}
