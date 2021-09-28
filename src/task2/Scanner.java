package task2;

public class Scanner {
    public Scanner(Text text) {
        this.text = text;
        this.ch = text.nextCh();
    }

    private final Text text;
    private char ch;

    public Lex scan() throws Exception {
        while (ch == ' ') {
            ch = text.nextCh();
        }

        if (Character.isJavaIdentifierStart(ch)) {
            ch = text.nextCh();
            while (Character.isJavaIdentifierStart(ch) || Character.isDigit(ch)) {
                ch = text.nextCh();
            }
            return Lex.NAME;
        } else if (Character.isDigit(ch)) {
            ch = text.nextCh();
            while (Character.isDigit(ch)) {
                ch = text.nextCh();
            }
            return Lex.NUMBER;
        } else if (ch == '(') {
            ch = text.nextCh();
            return Lex.R_PAR;
        } else if (ch == ')') {
            ch = text.nextCh();
            return Lex.L_PAR;
        } else if (ch == '+') {
            ch = text.nextCh();
            return Lex.PLUS;
        } else if (ch == '-') {
            ch = text.nextCh();
            return Lex.MINUS;
        } else if (ch == '*') {
            ch = text.nextCh();
            return Lex.MUL;
        } else if (ch == '/') {
            ch = text.nextCh();
            return Lex.DIV;
        } else if (ch == ';') {
            ch = text.nextCh();
            return Lex.SEMI;
        } else if (ch == ':'){
            ch = text.nextCh();
            if (ch == '=') {
                return Lex.ASSIGN;
            }
            throw new Exception("Лексическая ошибка! Лексема ':' не существует!");
        } else {
            if (ch == '\0') {
                return Lex.EOT;
            } else {
                throw new Exception("Лексическая ошибка! Неизвестный символ!");
            }
        }

    }
}
