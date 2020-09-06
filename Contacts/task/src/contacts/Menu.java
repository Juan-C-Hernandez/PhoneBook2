package contacts;

public class Menu {
    private final ContactManager contactManager;
    private final ConsoleHelper consoleHelper = ConsoleHelper.getInstance();
    private int index = -1;
    private int[] searchContactsIndex;


    public Menu(ContactManager contactManager) {
        this.contactManager = contactManager;
    }

    public void run() {
        Menus nextMenu = Menus.MAIN;
        while (true) {
            switch (nextMenu) {
                case MAIN:
                    nextMenu = mainMenu();
                    break;
                case RECORD:
                    nextMenu = record();
                    break;
                case SEARCH:
                    nextMenu = search();
                    break;
                case LIST:
                    nextMenu = list();
                    break;
                case EXIT:
                    consoleHelper.close();
                    return;
            }
            System.out.println();
        }
    }

    public Menus mainMenu() {
        String choice = consoleHelper.readString("[menu] Enter action (add, list, search, count, exit): ");
        switch (choice) {
            case "add":
                contactManager.addContact();
                return Menus.MAIN;
            case "list":
                contactManager.listContacts();
                return Menus.LIST;
            case "search":
                searchContactsIndex = contactManager.searchContact();
                return Menus.SEARCH;
            case "count":
                contactManager.countContacts();
                return Menus.MAIN;
            case "exit":
                return Menus.EXIT;
            /*case "save":
                try {
                    contactManager.save("/home/asjkt/Escritorio/contacts.txt");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return Menus.MAIN;*/
            default:
                return Menus.MAIN;
        }
    }

    public Menus record() {
        String choice = consoleHelper.readString("[record] Enter action (edit, delete, menu): ");
        switch (choice) {
            case "edit":
                contactManager.editContact(index);
                return Menus.RECORD;
            case "delete":
                contactManager.removeContact(index);
                return Menus.RECORD;
            case "menu":
                return Menus.MAIN;
            default:
                return Menus.RECORD;
        }
    }

    public Menus search() {
        String choice = consoleHelper.readString("[search] Enter action ([number], back, again): ");
        switch (choice) {
            case "back":
                return Menus.MAIN;
            case "again":
                searchContactsIndex = contactManager.searchContact();
                return Menus.SEARCH;
            default:
                index = Integer.parseInt(choice) - 1;
                if (index < searchContactsIndex.length && index >= 0) {
                    contactManager.infoContact(searchContactsIndex[index]);
                }
                return Menus.RECORD;
        }
    }

    public Menus list() {
        String choice = consoleHelper.readString("[list] Enter action ([number], back): ");
        switch (choice) {
            case "back":
                return Menus.MAIN;
            default:
                index = Integer.parseInt(choice) - 1;
                contactManager.infoContact(index);
                return Menus.RECORD;
        }
    }
}

enum Menus {
    MAIN,
    RECORD,
    SEARCH,
    LIST,
    EXIT
}
