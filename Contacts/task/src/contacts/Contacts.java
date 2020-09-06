package contacts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Contacts implements Serializable {
    private static final long serialVersionUID = 1L;
    private final List<Contact> contacts;

    public Contacts() {
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void removeContact(int index) {
        contacts.remove(index);
    }

    public int size() {
        return contacts.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void replaceContact(int index, Contact p) {
        contacts.set(index, p);
    }

    public Contact getContact(int index) {
        return contacts.get(index);
    }

    public List<Contact> getContactsList() {
        return contacts;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Contact p : contacts) {
            sb.append(i).append(". ").append(p).append(System.lineSeparator());
            i++;
        }

        return sb.toString();
    }
}
