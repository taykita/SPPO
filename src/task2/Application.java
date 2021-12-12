package task2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class LexData {
    private int count;
    private String name;


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

public class Application {
    public static void main(String[] args) throws Exception {

        String str = null;

        java.util.Scanner read = new java.util.Scanner(System.in);

        str = read.nextLine();

        //String fileName = "text.txt";
        //str = getString(fileName);

        Text text = new Text(str);
        Scanner scanner = new Scanner(text);
        Lex lex = scanner.scan();

        Map<Lex, LexData> lexMap = new HashMap<>();

        System.out.println(lex.name());
        int lexCount = 0;
        while (lex != Lex.EOT) {

            LexData data;
            if (lexMap.containsKey(lex)) {
                data = lexMap.get(lex);
                data.setCount(data.getCount() + 1);
            } else {
                data = new LexData();
                data.setCount(1);
                data.setName(lex.name());
            }
            System.out.println(lex.name() + " - " + scanner.getPos());
            lexMap.put(lex, data);
            lexCount++;
            lex = scanner.scan();
        }

        showLexMapData(lexMap);
        showLexCount(lexCount);

    }

    private static void showLexMapData(Map<Lex, LexData> lexMap) {
        for (LexData value : lexMap.values()) {
            System.out.printf("%-20s %-10s%n", value.getName(), value.getCount());
        }
    }

    private static void showLexCount(int lexCount) {
        System.out.printf("%-20s %-10s%n", "Число лексем:", lexCount);
    }

    private static String getString(String fileName) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            return sb.toString();
        } catch (IOException e) {
            throw new Exception("Ошибка обработки файла!");
        }
    }
}
