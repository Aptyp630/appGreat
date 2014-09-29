package com.algorithms;

public class Math {

    /**
     * Euclid's algorithm.
     * @return The greatest common divisor of two numbers
     */
    public static int gcd(int p, int q) {
        if(q == 0) return p;
        int r = p % q;
        return gcd(q, r);
    }

    /**
     * @return true if number is  prime
     */
    public static boolean isPrime(int number) {
        if(number < 2) return false;
        for(int i = 2 ; i*i <= number; i++)
            if(number % i == 0) return false;
        return true;
    }
}
