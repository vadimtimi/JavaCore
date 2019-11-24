package com.photosynthesis;

import com.photosynthesis.exeptions.IntParseExeption;
import com.photosynthesis.exeptions.SizeArrayExeption;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
	    // Перехват необрабатываемых исключений
        Handler handler = new Handler();
        Thread.setDefaultUncaughtExceptionHandler(handler);

        // Домашнее задание
        testArray(initErrorSize());
        testArray(initErrorData());
        testArray(null);
        testArray(initSucsess());
    }

    public static String[][]  initSucsess() {
        String[][] arraysSucsess = {
                {"1","2","3", "4"},
                {"1","2","3", "4"},
                {"1","2","3", "4"},
                {"1","2","3", "4"}
        };

        return arraysSucsess;
    }

    public static String[][]   initErrorSize() {
        String[][] arraysErrorSize = {
                {"1","2","3", "4"},
                {"1","2","3", "4"},
                {"1","2","3"},
                {"1","2","3", "4"}
        };

        return arraysErrorSize;
    }

    public static String[][]  initErrorData() {
        String[][] arraysErrorData = {
                {"1","2","3", "4"},
                {"1","2th","3", "4"},
                {"1","2","3", "4"},
                {"1","2","3", "4"}
        };
        return arraysErrorData;
    }

    public static void testArray(String[][] arrays) {
        try{
            int sum = sumArrays(arrays);
            System.out.println("Сумма элементов массива: "  + sum);
        }catch (SizeArrayExeption siseEx) {
            System.out.println(siseEx.getMessage() + " Длинна: " + siseEx.getSize());
        }catch (IntParseExeption intEx) {
            System.out.println(intEx.getMessage() + " Значение: " + intEx.getNoNum());
        }catch (Exception eX){
            System.out.println("Error testArray: " + eX.getMessage());
        }
    }

    // Получаем сумму чисел многомерногг массива размерностью 4х4
    public static int sumArrays(String[][] arrays) throws IntParseExeption, SizeArrayExeption {

        int sum = 0;
        if (arrays.length != 4) {
            throw new SizeArrayExeption("Длинна массива массивов не равна 4", arrays.length);
        }
        for (int i = 0; i < arrays.length; i++) {
            if(arrays[i].length !=4 ) {
                throw new SizeArrayExeption("Длинна подмассива " + i + " не равна 4", arrays[i].length);
            }
            for (int y = 0; y < arrays[i].length; y++) {
                if(!isNumber(arrays[i][y])) {
                    throw new IntParseExeption("Элемент " + i + " " + y + " не число", arrays[i][y]);
                }
                sum += Integer.parseInt(arrays[i][y]);
            }
        }
        return sum;
    }

    // Является ли строка - числом
    public static boolean isNumber(String str) {
        if (str == null || str.isEmpty()) return false;
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) return false;
        }
        return true;
    }
}

class Handler implements Thread.UncaughtExceptionHandler {
    public void uncaughtException(Thread t, Throwable e) {
        String er = "Error: " + e.getMessage() + "\n";
        er +=  t.toString();
        System.out.println("Error: " + e.getMessage());
        System.out.println(t.toString());
        JOptionPane.showMessageDialog(null, er,"Ошибка", JOptionPane.ERROR_MESSAGE);
    }
}
