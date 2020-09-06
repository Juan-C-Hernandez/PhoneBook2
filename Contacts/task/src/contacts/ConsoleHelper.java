package contacts;

import java.util.Scanner;

public class ConsoleHelper {
    private static final ConsoleHelper instance = new ConsoleHelper();
    private final Scanner scanner = new Scanner(System.in);

    private ConsoleHelper() {

    }

    public String readString(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    public int readInt(String msg) {
        System.out.print(msg);
        return Integer.parseInt(scanner.nextLine());
    }

    public char readChar(String msg) {
        System.out.print(msg);
        String ch = scanner.nextLine();
        return ch.length() == 0 ? '\u0000' : ch.charAt(0);
    }

    public static ConsoleHelper getInstance() {
        return instance;
    }

    public void close() {
        scanner.close();
    }
}
