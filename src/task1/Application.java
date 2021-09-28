package task1;

import task1.hash.Hash;
import task1.hash.PolyHash;
import task1.hash.RandomRehash;
import task1.hash.SimpleRehash;
import task1.table.SimpleVarTable;
import task1.table.VarTable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {

    public static final int FIRST_TEST = 100;
    public static final int LENGTH = 200;
    public static final int SIMPLE_REHASH_N = 3;

    public static final Hash HASH = new PolyHash(LENGTH);

    public static void main(String[] args) {
        File file = new File("text.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            List<String> names = new ArrayList<>();
            String st;
            VarTable varSimpleRehashTable = new SimpleVarTable(LENGTH, new SimpleRehash(SIMPLE_REHASH_N, LENGTH), HASH);
            VarTable varRandomRehashTable = new SimpleVarTable(LENGTH, new RandomRehash(LENGTH), HASH);
            for (int i = 0; i < FIRST_TEST; i++) {
                st = br.readLine();
                varSimpleRehashTable.addVar(st, st);
                varRandomRehashTable.addVar(st, st);
                names.add(st);
            }
            int simpleHashKF = 0;
            int randomHashKF = 0;

            for (String name: names) {
                varSimpleRehashTable.getVar(name);
                simpleHashKF += varSimpleRehashTable.getCount();
                varRandomRehashTable.getVar(name);
                randomHashKF += varRandomRehashTable.getCount();
            }
//            simpleHashKF /= names.size();
//            randomHashKF /= names.size();

            System.out.println(simpleHashKF);
            System.out.println(randomHashKF);

        } catch (IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }



    }
}
