package com.automation;

import java.util.Random;
import java.util.UUID;
import static io.dropwizard.testing.FixtureHelpers.fixture;

/**
 * Created by shirisha
 * since 3/6/20..
 */
public class HelperClass {


    public String getRandomId(){
        return UUID.randomUUID().toString();
    }

    public String generateRandomNumber() {
        return generateRandomNumber(5);
    }
    public String generateRandomNumber(int length) {
        Random random = new Random();
        String randomNumber = String.valueOf(random.nextInt(length));
        return randomNumber;
    }




}
