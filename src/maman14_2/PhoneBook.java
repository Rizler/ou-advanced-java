package maman14_2;

import java.io.Serializable;
import java.util.ArrayList;

import static java.util.Collections.sort;

public class PhoneBook implements Serializable {
    /**
     * Represents a phone book
     * Contains a list of PhoneBookEntry objects
     * Can add new entries and search for existing ones
     */

    private ArrayList<PhoneBookEntry> entries = new ArrayList<>();

    public ArrayList<PhoneBookEntry> getEntries() {
        return entries;
    }

    public void addEntry(String name, String phoneNumber) {
        entries.add(new PhoneBookEntry(name, phoneNumber));
        sort(entries);
    }

    public ArrayList<PhoneBookEntry> search(String name) {
        // Returns a list of PhoneBookEntry objects that match the given name
        ArrayList<PhoneBookEntry> matchingEntries = new ArrayList<>();
        for (PhoneBookEntry entry : entries) {
            if (entry.getName().toLowerCase().contains(name.toLowerCase())) {
                matchingEntries.add(entry);
            }
        }
        return matchingEntries;
    }

}
