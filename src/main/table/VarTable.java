package main.table;

public interface VarTable {
    void addVar(String name, Object info);

    Object getVar(String name);

    int getCount();

}
