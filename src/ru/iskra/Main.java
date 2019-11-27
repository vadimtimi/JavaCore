package ru.iskra;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        arrayStringTest();
        phoneBookTest();
    }

    public static void arrayStringTest() {
        //  Создать массив с набором слов (10-20 слов, среди которых должны встречаться повторяющиеся).
        String[] numbers = new String[] {"1 одини" , "2 два", "3 три ", "4 четыре", "0 ноль", "2 два", "4 четыре", "5 пять", "6 шесть", "7 семь", "8 восемь", "9 девять", "4 четыре"};

        List<String>  listNum = new ArrayList<>();
        Collections.addAll(listNum, numbers);

        Set<String> setUnique= new HashSet<>();
        setUnique.addAll(listNum);

        // Значения массива
        System.out.println(listNum);
        System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - -");
        // Уникальные значения массива
        System.out.println(setUnique);
        System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - -");
        // Посчитать, сколько раз встречается каждое слово.
        Map<String ,Long> mapCount = listNum.stream().collect(Collectors.groupingBy(c -> c , Collectors.counting())) ;
        mapCount.forEach((k , v ) -> System.out.println( k + " : "+ v ));
        System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - -");
    }

    public static void phoneBookTest() {

        MyPhoneBook phoneBook = new MyPhoneBook();
        phoneBook.add("Иванов", "555-55-51"); // одинаковые номера не будут добавляться для одной фамилии
        phoneBook.add("Иванов", "555-55-51");
        phoneBook.add("Иванов", "555-55-51");
        phoneBook.add("Иванов", "555-55-52");
        phoneBook.add("Иванов", "555-55-53");
        phoneBook.add("Петров", "555-55-54");
        phoneBook.add("Сидоров", "555-55-55");
        phoneBook.add("Сидоров", "555-55-56");
        phoneBook.add("Тарасов", "555-55-57");
        phoneBook.add("Ленин", "555-55-58");

        printPhones("Ivanov", phoneBook);
        printPhones("Иванов", phoneBook);
        printPhones("Петров", phoneBook);
        printPhones("Сидоров", phoneBook);

        phoneBook.printAllPhoneBook();
    }

    private static void printPhones(String fio , MyPhoneBook phoneBook) {
        HashSet<String> phones; phones = phoneBook.getPhones(fio);
        if(phones != null) {
            System.out.println("У контакта с фамилией " + fio + " тел: " +phones);
        } else {
            System.out.println("Нет фамилии "+ fio+ " в моей телефонной книге");
        }
    }
}
