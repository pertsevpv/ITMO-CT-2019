import java.util.*;

public class B {

    public static int getMax(List<Integer> list) {
        int max = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (max < list.get(i)) {
                max = list.get(i);
            }
        }
        return max;
    }

    public static void add(HashMap<Integer, Integer> hm, int k) {
        if (hm.get(k) == null) {
            hm.put(k, 1);
        } else {
            int v = hm.get(k);
            hm.put(k, v + 1);
        }
    }

    public static ArrayList<Integer> countSort(ArrayList<Integer> list){
        HashMap<Integer,Integer> c = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            add(c,list.get(i));
        }
        ArrayList<Integer> result = new ArrayList<>();

        int m = getMax(list);
        for (int i = 0; i <= m; i++) {
            if(c.get(i)!=null){
                for (int j = 0; j < c.get(i); j++) {
                    result.add(i);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        while (input.hasNextInt()){
            list.add(input.nextInt());
        }
        list = countSort(list);
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
    }
}
