package rsa;

import java.math.*;
import java.util.*;

class RSA {
    // Function to calculate GCD
    static int gcd(int e, int z) {
        if (e == 0)
            return z;
        else
            return gcd(z % e, e);
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int p, q, n, z, d = 0, e, i;
        int msg;

        // Input message
        System.out.print("Enter the message (as an integer): ");
        msg = sc.nextInt();

        // Input prime numbers
        System.out.print("Enter the first prime number: ");
        p = sc.nextInt();

        System.out.print("Enter the second prime number: ");
        q = sc.nextInt();

        // Step 1: Compute n and z (Euler’s Totient)
        n = p * q;
        z = (p - 1) * (q - 1);
        System.out.println("The value of z = " + z);

        // Step 2: Choose public key exponent e
        for (e = 2; e < z; e++) {
            if (gcd(e, z) == 1) {
                break;
            }
        }
        System.out.println("The value of e = " + e);

        // Step 3: Calculate private key exponent d
        for (i = 0; i <= 9; i++) {
            int x = 1 + (i * z);
            if (x % e == 0) {
                d = x / e;
                break;
            }
        }
        System.out.println("The value of d = " + d);

        // Step 4: Encryption
        double c = (Math.pow(msg, e)) % n;
        System.out.println("Encrypted message is : " + c);

        // Step 5: Decryption
        BigInteger N = BigInteger.valueOf(n);
        BigInteger C = BigDecimal.valueOf(c).toBigInteger();
        BigInteger msgback = (C.pow(d)).mod(N);

        System.out.println("Decrypted message is : " + msgback);

        sc.close();
    }
}
