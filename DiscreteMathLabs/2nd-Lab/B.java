import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class B {

    public static String[] BWTrans(String s) {
        String[] res = new String[s.length()];
        for (int i = 0; i < res.length; i++) {
            res[i] = s;
            StringBuilder sb = new StringBuilder();
            sb.append(s.substring(1));
            sb.append(s.charAt(0));
            s = sb.toString();
        }
        Arrays.sort(res);
        return res;
    }

    public static void main(String[] args) {
        File in = new File("bwt.in");
        File out = new File("bwt.out");
        String a = "";
        Scanner sc;
        try {
            sc = new Scanner(in);
            a = sc.next();
            String[] arr = BWTrans(a);
            StringBuilder res = new StringBuilder();

            for (int i = 0; i < arr.length; i++) {
                res.append(arr[i].charAt(arr[i].length() - 1));
            }
            FileWriter fw;
            try {
                fw = new FileWriter(out);
                fw.append(res.toString());
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}