package main.hash;

public class SimpleRehash implements Rehash{
    public SimpleRehash(int n, int length) {
        this.n = n;
        this.length = length;
    }

    private final int n;
    private final int length;

    @Override
    public int rehash(int hash) {
        return hash = (hash + n) % length;
    }
}
