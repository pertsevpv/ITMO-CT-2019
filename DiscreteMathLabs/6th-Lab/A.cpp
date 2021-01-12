#include <iostream>
#include "vector"
#include "set"
#include "deque"

using namespace std;

int n = 0;
vector<vector<bool >> G;

void reverseSubQueue(deque<int> &Q, int a, int b) {
    for (int i = 0; a + i < b - i; ++i)swap(Q[a + i], Q[b - i]);
}

void solve() {
    deque<int> Q;
    for (int i = 0; i < n; ++i) Q.push_back(i);
    for (int k = 0; k < n * (n - 1); ++k) {
        if (!G[Q[0]][Q[1]]) {
            int i = 2;
            while (!G[Q[0]][Q[i]] || !G[Q[1]][Q[i + 1]]) i++;
            reverseSubQueue(Q, 1, i);
        }
        Q.push_back(Q.front());
        Q.pop_front();
    }
    for (int v : Q)
        cout << v + 1 << " ";
}

int main() {
    deque<int> q;
    cin >> n;
    G.resize(n);
    for (int i = 0; i < n; ++i) G[i].assign(n, false);

    for (int i = 1; i < n; ++i) {
        string s;
        cin >> s;
        for (int j = 0; j < i; ++j)
            if (s[j] == '1') G[i][j] = G[j][i] = true;
    }
    solve();
    return 0;
}
