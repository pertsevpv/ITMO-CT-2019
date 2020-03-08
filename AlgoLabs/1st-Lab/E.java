import java.util.*;

public class E {

    public static void add(HashMap<Integer, Integer> hm, int k) {
        if (hm.get(k) == null) {
            hm.put(k, 1);
        } else {
            int v = hm.get(k);
            hm.put(k, v + 1);
        }
    }

    public static int count(ArrayList<Integer> list,int l,int r){
        HashMap<Integer,Integer> c = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            add(c,list.get(i));
        }
        int count = 0;
        for (int i = l; i <= r; i++) {
            if(c.get(i)!=null){
                count+=c.get(i);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(input.nextInt());
        }
        int k = input.nextInt();
        for (int i = 0; i < k; i++) {
            int l = input.nextInt();
            int r = input.nextInt();
            System.out.print(count(arr,l,r) + " ");
        }
    }
}

