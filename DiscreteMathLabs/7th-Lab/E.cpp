#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

unsigned n, m;
vector<pair<unsigned, int>> S; // el - w
vector<bool> masks;

bool cmp(pair<int, int> a, pair<int, int> b) {
    return a.second > b.second;
}

int maxBase() {
    int ans = 0;
    unsigned mask = 0;
    for (pair<unsigned, int> C : S) {
        unsigned bit = (unsigned) 1 << C.first;
        if (!masks[mask | bit]) {
            ans += C.second;
            mask |= bit;
        }
    }
    return ans;
}

int main() {
    freopen("cycles.in", "r", stdin);
    freopen("cycles.out", "w", stdout);

    cin >> n >> m;

    masks.resize((unsigned) 1 << n);
    masks.assign((unsigned) 1 << n, false);

    for (int i = 0; i < n; i++) {
        int w;
        cin >> w;
        S.emplace_back(i, w);
    }

    for (int i = 0; i < m; i++) {
        unsigned s;
        cin >> s;
        unsigned mask = 0;
        for (int j = 0; j < s; j++) {
            unsigned x;
            cin >> x;
            x--;
            mask |= (unsigned) 1 << x;
        }
        masks[mask] = true;
        for (unsigned j = mask + 1; j < (unsigned) 1 << n; j++)
            if ((j | mask) == j)
                masks[j] = true;
    }
    sort(S.begin(), S.end(), cmp);

    cout << maxBase();
    return 0;
}