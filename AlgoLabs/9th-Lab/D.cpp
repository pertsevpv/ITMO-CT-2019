#include <iostream>
#include "vector"
#include <queue>
#include "set"
#include <algorithm>
#include "map"

using namespace std;

int n = 0;
int m = 0;
int k = 0;

vector<vector<int >> d;

vector<pair<pair<int, int>, int>> E;

void solve(int s) {
    d = vector<vector<int >>(k + 1, vector<int>(n, INT32_MAX));
    d[0][s] = 0;

    for (int i = 1; i < k + 1; ++i) {
        for (int j = 0; j < m; ++j) {
            int u = E[j].first.first;
            int v = E[j].first.second;
            int w = E[j].second;
            if (d[i - 1][u] < INT32_MAX)
                d[i][v] = min(d[i][v], d[i - 1][u] + w);
        }
    }
}

int main() {
    int s;
    cin >> n >> m >> k >> s;
    s--;

    for (int i = 0; i < m; ++i) {
        int u, v;
        int w;
        cin >> u >> v >> w;
        u--;
        v--;
        E.push_back({{u, v}, w});
    }

    solve(s);
    for (int i = 0; i < n; ++i) {
        if (d[k][i] == INT32_MAX) {
            cout << "-1\n";
        } else cout << d[k][i] << "\n";
    }

    return 0;
}