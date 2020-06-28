#include <iostream>
#include <stdlib.h>
#include <stdint.h>
#include <vector>
using namespace std;

int max(int a, int b) {
    if (a > b) return a;
    else return b;
}

int min(int a, int b) {
    if (a < b) return a;
    else return b;
}

int main(){
    int n, x, y, a0;
    cin >> n;
    cin >> x;
    cin >> y;
    cin >> a0;
    vector<unsigned int> a;
    a.resize(n);
    vector<long long> s;
    s.resize(n);
    a[0] = a0;
    s[0] = a[0];
    for (int i = 1; i < n; i++) {
        a[i] = (x * a[i - 1] + y) % (1 << 16);
        s[i] = s[i - 1] + a[i];
    }
    int m, z, t, b0;
    cin >> m;
    cin >> z;
    cin >> t;
    cin >> b0;
    if (m == 0)cout << 0 << endl;
    else
    {
        vector<unsigned int> b;
        b.resize(2 * m);
        b[0] = b0;
        for (int i = 1; i < b.size(); i++) {
            int i1 = z * b[i - 1] + t;
            for (; i1 < 0; i1 += (1 << 30));
            b[i] = i1 % (1 << 30);
        }
        long long sum = 0;
        for (int i = 0; i < m; i++) {
            int l = min(b[2 * i] % n, b[2 * i + 1] % n);
            int r = max(b[2 * i] % n, b[2 * i + 1] % n);
            if (l == 0)sum += s[r];
            else sum += s[r] - s[l - 1];
        }
        cout << sum << endl;
    }
    return 0;
}