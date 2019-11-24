package com.photosynthesis.exeptions;

public class SizeArrayExeption extends Exception{
    private int size;

    public int getSize() {
        return size;
    }

    public SizeArrayExeption(String message, int size) {
        super(message);
        this.size = size;
    }
}
