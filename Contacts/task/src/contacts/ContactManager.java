package contacts;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactManager {
    private final Contacts contacts;
    private final ConsoleHelper consoleHelper = ConsoleHelper.getInstance();
    private String fileName;

    public ContactManager(Contacts contacts) {
        this.contacts = contacts;
    }

    public ContactManager() {
        this(new Contacts());
    }

    public ContactManager(String fileName) throws IOException, ClassNotFoundException {
        this.fileName = fileName;
        File file = new File(fileName);
        if (file.exists()) {
            this.contacts = (Contacts) SerializationUtils.deserialize(fileName);
        } else {
            this.contacts = new Contacts();
        }
    }

    public void addContact() {
        String type = consoleHelper.readString("Enter the type (person, organization): ");
        Contact contact = null;
        if (type.equals("person")) {
            contact = new Person();
        } else if (type.equals("organization")) {
            contact = new Organization();
        }

        if (contact != null) {
            for (String field : contact.getFields()) {
                String value = consoleHelper.readString("Enter " + field + ": ");
                contact.setField(field, value);
            }
            contacts.addContact(contact);
            saveChanges();
            System.out.println("The record added.");
        }
    }

    public void editContact(int index) {
        if (contacts.isEmpty()) {
            System.out.println("No records to edit!");
        } else if (isIndexCorrect(index)) {
            Contact contact = contacts.getContact(index);
            String field = consoleHelper.readString("Select a field ("
                    + String.join(", ", contact.getFields()) + "): ");
            String newValue = consoleHelper.readString("Enter " + field + ": ");
            contact.setField(field, newValue);
            saveChanges();
            System.out.println("Saved");
            System.out.println(contact);
        }
    }

    public void removeContact(int index) {
        if (contacts.isEmpty()) {
            System.out.println("No records to remove!");
        } else if (isIndexCorrect(index)) {
            contacts.removeContact(index);
            saveChanges();
            System.out.println("The record removed!");
        }
    }

    private void saveChanges() {
        if (fileName != null) {
            try {
                SerializationUtils.serialize(contacts, fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isIndexCorrect(int index) {
        if (index >= 0 && index < contacts.size()) {
            return true;
        } else {
            System.out.print("Wrong index");
            return false;
        }
    }

    public void countContacts() {
        System.out.println("The Phone Book has " + contacts.size() + " records.");
    }

    public void listContacts() {
        if (!contacts.isEmpty()) {
            int i = 1;
            StringBuilder sb = new StringBuilder();
            String ln = System.lineSeparator();
            for (Contact contact : contacts.getContactsList()) {
                sb.append(i).append(". ").append(contact.getIntro()).append(ln);
                i++;
            }
            System.out.print(sb.toString());
        } else {
            System.out.println("The Phone Book is empty");
        }
    }

    public int[] searchContact() {
        String query = consoleHelper.readString("Enter search query: ");
        Pattern pattern = Pattern.compile(query, Pattern.CASE_INSENSITIVE);
        Matcher matcher;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int j = 0;
        ArrayList<Integer> index = new ArrayList<>();
        for (Contact c : contacts.getContactsList()) {
            matcher = pattern.matcher(c.query());
            if (matcher.find()) {
                sb.append(++i).append(". ").append(c.getIntro()).append(System.lineSeparator());
                index.add(j);
            }
            j++;
        }
        System.out.print(sb.toString());
        return index.stream().mapToInt(Integer::intValue).toArray();
    }

    public void infoContact(int index) {
        if (isIndexCorrect(index)) {
            System.out.println(contacts.getContact(index));
        }
    }

    public void save(String fileName) throws IOException {
        SerializationUtils.serialize(contacts, fileName);
    }
}
