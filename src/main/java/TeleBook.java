import java.util.*;

public class TeleBook implements Iterable<Contact> {
    Map<String, Contact> contacts = new TreeMap<>();

    public TeleBook(Map<String, Contact> contacts) {
        this.contacts = contacts;
    }

    public TeleBook() {
    }

    public boolean add(String name, String telephone) {
        if (name == null || telephone == null)
            throw new  NullPointerException("name and telephone connet be null");
        if (name.isEmpty() || telephone.isEmpty())
            throw new IllegalArgumentException("name and telephone cannot be empty");
        if (!contacts.containsKey(name)){
            contacts.put(name, new Contact(name, telephone));
            return true;
        } else {
            return false;
        }
    }
    public boolean remove(String name) {
        return contacts.remove(name) != null;
    }
    public List<Contact> findByName(String name) {
        List<Contact> result = new ArrayList<>();
        for (Map.Entry<String, Contact> stringContactEntry : contacts.entrySet()) {
            if (stringContactEntry.getKey().contains(name))
                result.add(stringContactEntry.getValue());
        }
        return result;
    }
    public List<Contact> findByTelephone(String telephone) {
        List<Contact> result = new ArrayList<>();
        for (Contact value : contacts.values()) {
            if (value.getTelephone().contains(telephone))
                result.add(value);
        }
        return result;
    }

    @Override
    public Iterator<Contact> iterator() {
        return contacts.values().iterator();
    }
}
