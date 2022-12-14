package company;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.*;
import java.util.*;

public class RSA {
    
    BigInteger[] generateKey(String input1, String input2){
        BigInteger array[] = new BigInteger[3];
        BigInteger one = new BigInteger("1"); //hardcode 1 into biginteger...

        BigInteger p = new BigInteger(input1);
        BigInteger q = new BigInteger(input2);
        BigInteger n = new BigInteger(p.multiply(q).toString());
        BigInteger t = new BigInteger(p.subtract(one).multiply(q.subtract(one)).toString()); //totient function
        BigInteger e = new BigInteger("65537"); //Must be greater than 1
        BigInteger d = new BigInteger("0");

        while(e.compareTo(t) < 0){
            if(t.gcd(e).compareTo(one) == 0){
                d = e.modInverse(t);
                break;
            }
            e = e.add(one);
        }

        array[0] = n;
        array[1] = e;
        array[2] = d;

        return array;
    }

    String generatePrime(int digits){
        String lowerLimit = "1";
        String upperLimit = "9";
        for(int i = 0; i < digits - 1; i++){
            lowerLimit = lowerLimit + "0";
            upperLimit = upperLimit + "9";
        }

        BigInteger lower = new BigInteger(lowerLimit);
        BigInteger upper = new BigInteger(upperLimit);

        BigInteger result;
        Random rand = new Random();
        Boolean isPrime = false;
        int counter;
        do{
            result = new BigInteger(upper.bitLength(), rand);
            
            if(result.toString().length() <= 18){ //bailliePSW
                isPrime = testBailliePSW(result);
            }
            else{ //millerRabin
                counter = 0;
                do{
                    isPrime = testMillerRabin(result);
                    counter++;
                }while(isPrime && counter < 10);
                if(counter == 10){
                    isPrime = true;
                }
                else{
                    isPrime = false;
                }
            }
        }while((result.compareTo(upper) == 1) || (result.compareTo(lower) == -1) || !isPrime);

        

        return result.toString();
    }

    Boolean testBailliePSW(BigInteger num){
        //perform trial division by small prime number
        //perform base 2 strong probable prime test
        //find first D in sequence 5, -7, 9, -11, 13, -15 where Jacobi symbol = -1
        //perform strong Lucas probable prime test on n using parameters D, P, and Q

        return true;
    }

    Boolean testMillerRabin(BigInteger num){
        
        return true;
    }


    String stringToASCII(String input){
        input = input.toUpperCase();
        input = input.replace("{", "");
        input = input.replace("|", "");
        input = input.replace("}", "");
        input = input.replace("~", "");

        String builder = "";
        for(int i = 0; i < input.length(); i++){
            builder = builder + (int)input.charAt(i);
        }
        return builder;
    }

    String asciiToString(String input){
        String builder = "";
        for(int i = 0; i < input.length(); i = i + 2){
            builder = builder + (char)Integer.parseInt(input.substring(i, i + 2));
        }
        return builder;
    }

    String encrypt(String input) throws FileNotFoundException{
        input = stringToASCII(input);

        File file = new File("importRSA.txt");
        Scanner reader = new Scanner(file);


        BigInteger n = new BigInteger(reader.nextLine());
        BigInteger e = new BigInteger(reader.nextLine());
        BigInteger d = new BigInteger(reader.nextLine());
        BigInteger m = new BigInteger(input);

        if(input.length() > d.toString().length()){
            System.out.println("Input is too long for future decryption key.");
            System.exit(0);
        }

        BigInteger output = new BigInteger("0");
            
        reader.close();

        output = m.pow(e.intValue()).mod(n);

        return output.toString();
    }

    String decrypt(String input) throws FileNotFoundException{
        File file = new File("importRSA.txt");
        Scanner reader = new Scanner(file);


        BigInteger n = new BigInteger(reader.nextLine());
        BigInteger e = new BigInteger(reader.nextLine());
        BigInteger d = new BigInteger(reader.nextLine());
        BigInteger c = new BigInteger(input);
        
        BigInteger output = new BigInteger("0");
        
        reader.close();

        output = c.modPow(d, n);

        if(output.toString().length() % 2 != 0){
            System.out.println("Input is invalid.");
            System.exit(0);
        }
        
        return asciiToString(output.toString()).toLowerCase();
    }
}
