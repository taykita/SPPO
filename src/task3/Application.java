package task3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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

class LexDataObject {
    public LexDataObject(Lex lex, int data) {
        this.data = data;
        this.lex = lex;
    }

    private Lex lex;
    private int data;

    public Lex getLex() {
        return lex;
    }

    public void setLex(Lex lex) {
        this.lex = lex;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
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
            LinkedList<LexDataObject> lexList = new LinkedList<>();
            while (lex != Lex.SEMI) {
                String value = scanner.getTmpValue();
                int intValue = 0;
                if (Lex.NUMBER == lex) {
                    int k = 1;
                    for (int i = 0; i < value.length(); i++) {
                        if (value.charAt(i) == 'X') {
                            intValue += 10;
                        } else if (value.charAt(i) == 'V') {
                            intValue += 5;
                        } else if (value.charAt(i) == 'I') {
                            intValue += 1;
                        }
                    }
                } else if (lex == Lex.NAME) {
                    System.out.println("Введите значение - " + value);
                    intValue = read.nextInt();
                }

                lexList.add(new LexDataObject(lex, intValue));

                lex = scanner.scan();
            }



            boolean check = true;
            while (check) {
                Iterator<LexDataObject> iterator = lexList.iterator();
                while (iterator.hasNext()) {
                    LexDataObject lexDT = iterator.next();
                    if (!iterator.hasNext()) {
                        check = false;
                        break;
                    }
                    LexDataObject lexDT3 = iterator.next();
                    if (lexDT3.getLex() == Lex.MINUS) {
                        LexDataObject lexDT2 = iterator.next();
                        lexDT2.setData(lexDT2.getData() * -1);
                        lexDT3.setLex(Lex.PLUS);
                        break;
                    }
                }
            }

            check = true;
            while (check) {
                Iterator<LexDataObject> iterator = lexList.iterator();
                while (iterator.hasNext()) {
                    LexDataObject lexDT = iterator.next();
                    if (!iterator.hasNext()) {
                        check = false;
                        break;
                    }
                    LexDataObject lexDT3 = iterator.next();
                    if (lexDT3.getLex() == Lex.MUL) {
                        LexDataObject lexDT2 = iterator.next();
                        lexDT2.setData(lexDT.getData() * lexDT2.getData());
                        lexDT.setData(0);
                        lexDT3.setLex(Lex.PLUS);
                        break;
                    } else if (lexDT3.getLex() == Lex.DIV) {
                        LexDataObject lexDT2 = iterator.next();
                        lexDT2.setData(lexDT.getData() / lexDT2.getData());
                        lexDT.setData(0);
                        lexDT3.setLex(Lex.PLUS);
                        break;
                    }
                }
            }



            int answer = 0;
            Iterator<LexDataObject> iterator = lexList.iterator();
            while (iterator.hasNext()) {
                LexDataObject next = iterator.next();
                if (next.getLex() != Lex.PLUS) {
                    answer += next.getData();
                }
            }

            System.out.println(answer);
            lex = scanner.scan();

        }

//V + V * V - V / V;
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
