package task1.table;

import task1.hash.Hash;
import task1.hash.Rehash;

import java.util.ArrayList;
import java.util.List;

public class SimpleVarTable implements VarTable {
    public SimpleVarTable(int length, Rehash rehash, Hash hashF) {
        data = new ArrayList<>(length);
        initData(length);
        this.rehash = rehash;
        this.hashF = hashF;
    }

    private void initData(int length) {
        for (int i = 0; i < length; i++) {
            data.add(null);
        }
    }

    private final Hash hashF;
    private final Rehash rehash;
    private final List<Object> data;

    @Override
    public void addVar(String name, Object info) {
        int hash = hashF.hash(name);
        addVar(hash, info);
    }

    private void addVar(int hash, Object info) {
        if (existVar(hash)) {
            addVar(rehash.rehash(hash), info);
        } else {
            data.set(hash, info);
        }
    }

    private int count;

    public int getCount() {
        return count;
    }

    @Override
    public Object getVar(String name) {
        count = 0;
        int hash = hashF.hash(name);
        return getVar(hash, name);
    }

    private Object getVar(int hash, String name) {
        if (existVar(hash)) {
            count++;
            Object info = getVar(hash);
            if (info.equals(name)) {
                return info;
            }
            return getVar(rehash.rehash(hash), name);
        } else {
            return null;
        }
    }

    private Object getVar(int hash) {
        return data.get(hash);
    }

    private boolean existVar(int hash) {
        return getVar(hash) != null;
    }
}
