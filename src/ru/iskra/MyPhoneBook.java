package ru.iskra;

import java.util.*;

public class MyPhoneBook {
    private Map<String, HashSet<String>> phoneBook = new HashMap<>();

    public boolean add(String fio, String phone) {
        if(phoneBook.containsKey(fio)) {
            HashSet<String> tmp = phoneBook.get(fio);
            tmp.add(phone);
        }else {
            phoneBook.put(fio, new HashSet<String>() {{ add(phone); }});
        }
        return true;
    }

    public HashSet<String> getPhones(String fio) {
        if(phoneBook.containsKey(fio)) {
            return phoneBook.get(fio);
        }
        return null;
    }

    public void printAllPhoneBook() {
        System.out.println(phoneBook);
    }
}
