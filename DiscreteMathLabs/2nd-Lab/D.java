import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class D {

    static HashMap<String, Integer> abc = new HashMap<>();
    static int len = 26;

    public static void setAbc() {
        String s = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < s.length(); i++) {
            abc.put(String.valueOf(s.charAt(i)), i);
        }
    }

    public static String LZW(String w) {
        setAbc();
        StringBuilder res = new StringBuilder();
        String t = "";
        int i = 0;
        boolean b = true;
        while (i < w.length()) {
            String c = String.valueOf(w.charAt(i++));
            StringBuilder tc = new StringBuilder(t);
            tc.append(c);
            while (abc.get(tc.toString()) != null && i < w.length()) {
                t = tc.toString();
                c = c = String.valueOf(w.charAt(i++));
                tc = new StringBuilder(t);
                tc.append(c);
            }
            if(abc.get(tc.toString()) != null){
                res.append(abc.get(tc.toString()));
                res.append(" ");
                b = false;
            }else {
                res.append(abc.get(t));
                res.append(" ");
                abc.put(tc.toString(), len++);
                t = c;
            }

        }
        if(b){
            res.append(abc.get(t));
        }
        return res.toString();
    }

    public static void main(String[] args) throws IOException {
        File in = new File("lzw.in");
        File out = new File("lzw.out");
        in.createNewFile();
        out.createNewFile();
        String a = "";
        Scanner sc;
        try {
            sc = new Scanner(in);
            a = sc.next();
            String res = LZW(a);
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