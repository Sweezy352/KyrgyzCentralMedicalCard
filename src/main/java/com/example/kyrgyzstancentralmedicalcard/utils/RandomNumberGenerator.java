package com.example.kyrgyzstancentralmedicalcard.utils;


import java.util.concurrent.ThreadLocalRandom;

public class RandomNumberGenerator {

    public static Integer generateRandomNumber(){
        Integer randomNum = ThreadLocalRandom.current().nextInt(100_000, 1_000_000);
        return randomNum;
    }
}
