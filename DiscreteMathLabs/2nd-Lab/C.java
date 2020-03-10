
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class C {

    public static String abc = "abcdefghijklmnopqrstuvwxyz";

    public static String shift(int pos) {
        StringBuilder sb = new StringBuilder();
        sb.append(abc.charAt(pos));
        sb.append(abc.substring(0, pos));
        sb.append(abc.substring(pos + 1));
        return sb.toString();
    }

    public static String MTF(String s) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int a = abc.indexOf(s.charAt(i));
            res.append(a + 1);
            res.append(" ");
            if (a != 0) {
                abc = shift(a);
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {

        File in = new File("mtf.in");
        File out = new File("mtf.out");

        String a = "";
        Scanner sc;
        try {
            sc = new Scanner(in);
            a = sc.next();
            String res = MTF(a);
            FileWriter fw;
            try {
                fw = new FileWriter(out);
                fw.append(res);
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}