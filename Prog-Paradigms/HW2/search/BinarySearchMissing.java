package search;

public class BinarySearchMissing {

    /**
     * @param arr - sorted array of integers to search in
     * @param n   - element to find
     * @return min index i of arr for which arr[i] == n if n exists in arr or -i-1 if n doesn't
     * PRE: arr.length > 0
     * PRE: Ɐ k > j: arr[k] <= arr[j]
     * POST: (i - min index of arr for which arr[i] == n && i >= 0) || (-i-1 - insertion point for which arr[i] < n && i < 0)
     * POST: arr' == arr
     **/
    public static int binSearch(int n, int[] arr) {
        // INV: (l == -1) || ((0 <= l < arr.length) && (arr[l] > n))
        int l = -1;
        // INV: (r == arr.length) || ((0 <= r < arr.length) && (arr[r] <= n))
        int r = arr.length;
        // INV: l < m < r
        int m = (r + l) / 2;
        // INV: (r - l >= 1) && (r' - l' <= (r - l) / 2)
        while (r - l > 1) {
            if (arr[m] <= n) {
                r = m;
                // (r' == m) && (l' = l) -> r' - l' == (l + r) / 2 - l == (r - l) / 2
                // l' == l -> (l' == -1) || ((0 <= l' < arr.length) && (arr[l'] > n))
                // (r' == m) && (l < m < r) -> (0 < r' < arr.length) && (arr[r'] <= n)
                // (r - l > 1) && (r' == m) && (l < m < r) -> r' - l' >= 1
            } else {
                l = m;
                // (l' == m) && (r' = r) -> r' - l' == r - (l + r) / 2 == (r - l) / 2
                // r' == r -> (r == arr.length) || ((0 <= r < arr.length) && (arr[r] <= n))
                // (l' == m) && (l < m < r) -> (-1 < l' < arr.length) && (arr[l'] > n)
                // (r - l > 1) && (l' == m) && (l < m < r) -> r' - l' >= 1
            }
            m = (r + l) / 2;
        }
        // !(r - l > 1) && (r - l >= 1) -> r == l + 1
        // arr[arr.length - 1] > n -> r = arr.length && l = arr.length - 1
        // r < arr.length -> arr[r] <= n
        // r == 0 -> arr[r] <= n
        // r > 0 && r - l == 1 -> arr[r - 1] > n
        if (r != arr.length && arr[r] == n) {
            // arr[r] == n
            return r;
        } else {
            // arr[r] < n;
            return -r - 1;
        }
    }

    /**
     * @param arr - sorted array of integers to search in
     * @param n   - element to find
     * @param l   - left border of the search
     * @param r   - right border of the search
     * @return min index i of arr[l..r] for which arr[i] == n if n exists in arr and -i-1 if n doesn't
     * PRE: arr.length > 0
     * PRE: Ɐ k > j: arr[k] <= arr[j]
     * PRE: l == -1 && r == arr.length
     * INV: (l == -1) || ((0 <= l < arr.length) && (arr[l] > n))
     * INV: (0 <= r <= arr.length) && (arr[r] <= n)
     * INV: (r - l >= 1) && (r' - l' <= (r - l) / 2)
     * POST: (i - min index of arr for which arr[i] == n && i >= 0) || (-i-1 - insertion point for which arr[i] < n && i < 0)
     * POST: arr' == arr
     **/
    public static int binSearch(int n, int[] arr, int l, int r) {
        // INV: l < m < r
        int m = (l + r) / 2;
        if (r - l > 1) {
            if (arr[m] <= n) {
                r = m;
                // (r' == m) && (l' = l) -> r' - l' == (l + r) / 2 - l == (r - l) / 2
                // l' == l -> (l == -1) || ((0 <= l' < arr.length) && (arr[l'] > n))
                // (r' == m) && (l < m < r) -> (0 < r' < arr.length) && (r' <= n)
                // (r - l > 1) && (r' == m) && (l < m < r) -> r' - l' >= 1
            } else {
                // (l' == m) && (r' = r) -> r' - l' == r - (l + r) / 2 == (r - l) / 2
                // r' == r -> (r == arr.length) || ((0 <= r < arr.length) && (arr[r] <= n))
                // (l' == m) && (l < m < r) -> (0 <= l' < arr.length) && (arr[l'] > n)
                // (r - l > 1) && (l' == m) && (l < m < r) -> r' - l' >= 1
                l = m;
            }
            return binSearch(n, arr, l, r);
        } else {
            // !(r - l > 1) && (r - l >= 1) -> r == l + 1
            // arr[arr.length - 1] > n -> r = arr.length && l = arr.length - 1
            // r < arr.length -> arr[r] <= n
            // r == 0 -> arr[r] <= n
            // r > 0 && r - l == 1 -> arr[r - 1] > n
            if (r != arr.length && arr[r] == n) {
                // arr[r] == n
                return r;
            } else {
                // arr[r] < n;
                return -r - 1;
            }
        }
    }

    // PRE: args.length > 0
    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int[] arr = new int[args.length - 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(args[i + 1]);
        }
        if (arr.length != 0) {
            //System.out.println(binSearch(x, arr));
            System.out.println(binSearch(x, arr, -1, arr.length));
        } else {
            System.out.println(-1);
        }
    }
}