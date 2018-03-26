package com.nbtlxx.dining.exception;

/**
 * Created by chenxu on 18/3/19.
 */
public class FoodItemException extends RuntimeException {

    private static final long serialVersionUID = 2361446884822298905L;

    public FoodItemException(String message) {
        super(message);
    }
}
