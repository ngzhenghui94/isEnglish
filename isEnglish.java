import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;

public class isEnglish {
    // Example: A = 22, B = 2, C = 0, D = 10, .... Z = 5
    // IC = A(A-1) + B(B-1) + D(D-1)... / Total(Total - 1)
    public double calculate(String text){
        int N = 0;
        double sum = 0.0;
        double total = 0.0;
        text = text.toUpperCase();

        //initialize array of values to count frequency of each letter
        int[] values = new int[26];
        for(int i=0; i<26; i++){
            values[i] = 0;
        }

        //calculate frequency of each letter in s
        int getChar;
        for(int i=0; i<text.length(); i++){
            getChar = text.charAt(i)-65;
            if(getChar>=0 && getChar<26){
                values[getChar]++;
                N++;
            }
        }

        //calculate the sum of each frequency
        for(int i=0; i<26; i++){
            getChar = values[i];
            sum = sum + (getChar * (getChar-1));
        }

        //divide by N(N-1)
        total = sum/(N*(N-1));

        //return the result
        return total;
    }

    // Read string in file.
    private static String readFile(String fileName) throws IOException {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data.toUpperCase();
    }

    public static void main(String[] args) throws IOException {
        final double englishIcBaseLine = 0.0667;
        isEnglish obj = new isEnglish();
        String getText = obj.readFile(args[0]);
        double getTextIC = obj.calculate(getText);
        System.out.println("Text: " + getText);
        if (getTextIC < englishIcBaseLine){
            System.out.println("This is not an english text");
        } else if (getTextIC > englishIcBaseLine){
            System.out.println("This is an english text");
        }
        DecimalFormat f = new DecimalFormat("0.000");
        System.out.println("Index of Coincidence: " + f.format(getTextIC));
    }
}

//        Testing
//        String testString="testString";
//        String testString2="operwlfdsgd";
//        System.out.println(obj.calculate(testString));
//        System.out.println(obj.calculate(testString2));