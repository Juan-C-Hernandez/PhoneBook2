package contacts;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ContactManager contactManager;
        if (args.length == 0) {
            contactManager = new ContactManager();
        } else {
            contactManager = new ContactManager(args[0]);
        }
        new Menu(contactManager).run();
    }
}


