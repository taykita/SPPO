package task1.hash;

import java.util.ArrayList;
import java.util.List;

public class RandomRehash implements Rehash {
    public RandomRehash(int length) {
        this.randomArr = new ArrayList<>();
        this.LENGTH = length;
        fillRandomArr(this.randomArr, length);
    }

    private void fillRandomArr(List<Integer> randomArr, int length) {
        for (int i = 0; i < length; i++) {
            int random = (int) (Math.random() * (length - 1));
            if (randomArr.size() == length - 1) {
                randomArr.add(random);
                break;
            }
            while (randomArr.contains(random)) {
                random = (int) (Math.random() * (length - 1));
            }
            randomArr.add(random);
        }
    }

    private final List<Integer> randomArr;
    private final int LENGTH;

    @Override
    public int rehash(int hash) {
//        return hash = (hash + randomArr.get(hash)) % LENGTH;
        return randomArr.get(hash);
    }
}
