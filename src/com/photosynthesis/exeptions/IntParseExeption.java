package com.photosynthesis.exeptions;

public class IntParseExeption extends Throwable {
    private String noInt;

    public String getNoNum() {
        return noInt;
    }

    public IntParseExeption(String message, String noInt) {
        super(message);
        this.noInt = noInt;
    }
}
