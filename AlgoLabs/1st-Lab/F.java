import java.util.Scanner;

public class Main {
    public static int[] values;

    public static int binSearch(int n){
        int l = 0,r = values.length-1;
        int m = (l+r)/2;
        while (l<r){
            if(values[m]==n){
                return values[m];
            }else if(values[m] < n){
                l = m;
            }else {
                r = m;
            }
            m = (l+r)/2;
            if(m==l){
                break;
            }
        }
        if(Math.abs(values[l]-n) > Math.abs(values[r]-n)){
            return values[r];
        }else{
            return values[l];
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        values = new int[n];

        for (int i = 0; i < n; i++) {
            values[i] = sc.nextInt();
        }
        for (int i = 0; i < k; i++) {
            System.out.println(binSearch(sc.nextInt()));
        }
    }
}
