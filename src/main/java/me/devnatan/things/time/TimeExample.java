package me.devnatan.things.time;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TimeExample {

    /*
        OUTPUT:

        We have: 1 seconds
        We have: 1 seconds, 32 minutes, 2 hours and 1 days
        We have: 1 seconds, 6 minutes, 4 hours and 2 days
        We have: 1 seconds, 12 minutes, 6 hours and 3 days
        We have: 1 seconds, 45 minutes, 8 hours and 4 days
        We have: 1 seconds, 33 minutes, 10 hours and 5 days
        We have: 1 seconds, 10 minutes, 12 hours and 6 days
        We have: 1 seconds, 55 minutes, 14 hours and 7 days
        We have: 1 seconds, 53 minutes, 17 hours and 8 days
        We have: 1 seconds, 8 minutes, 20 hours and 9 days

        Process finished with exit code 0
     */
    public static void main(String[] args) {
        long millis = 1000;
        Random r = new Random();
        for(int j = 0; j < 10; j++) {
            System.out.println("We have: " + Time.of(millis)
                    .sec(i -> i + " seconds")
                    .min(i -> i + " minutes")
                    .hour(i -> i + " hours")
                    .day(i -> i + " days")
                    .get());
            for(int k = 0; k < 5; k++) {
                millis += TimeUnit.MINUTES.toMillis(r.nextInt(60));
            }
            millis += TimeUnit.DAYS.toMillis(1);
        }
    }

}
