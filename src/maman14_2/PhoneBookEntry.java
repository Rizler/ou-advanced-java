package maman14_2;

import java.io.Serializable;

public class PhoneBookEntry implements Comparable<PhoneBookEntry>, Serializable {
    /**
     * Represents a phone book entry
     * Contains a name and a phone number
     */

    private String name;
    private String phoneNumber;

    public PhoneBookEntry(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Override
    public int compareTo(PhoneBookEntry other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return this.name + ": " + this.phoneNumber.substring(0, 3) + "-" + this.phoneNumber.substring(3);
    }
}
