#include <iostream>
#include "vector"
#include "set"
#include <algorithm>
#include "map"

using namespace std;

int n = 0;
vector<pair<pair<int, int>, int>> E; //u - v - w
vector<int> d;
vector<int> p;

void solve() {
    int m = E.size();
    d.resize(n);
    //d.assign(n, INT32_MAX);
    //d[0] = 0;
    p.resize(n);
    p.assign(n, -1);
    int x = -1;
    for (int i = 0; i < n; i++) {
        x = -1;
        for (pair<pair<int, int>,int > e : E) {
            int u = e.first.first;
            int v = e.first.second;
            int w = e.second;
            if (d[v] > d[u] + w) {
                d[v] = max(INT32_MIN, d[u] + w);
                p[v] = u;
                x = v;
            }
        }
    }
    if (x == -1) {
        cout << "NO\n";
        return;
    } else {
        cout << "YES\n";
        int y = x;
        for (int i = 0; i < n; i++)
            y = p[y];

        vector<int> path;
        path.push_back(y);
        int cur = p[y];
        while (cur != y)
        {
            path.push_back(cur);
            cur = p[cur];
        }
        reverse(path.begin(), path.end());
        cout << path.size() << "\n";
        for (int i = 0; i < path.size(); i++) {
            cout << path[i] + 1 << " ";
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cin >> n;
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            int w;
            cin >> w;
            if (w == 100000)continue;
            E.push_back({{i, j}, w});
        }
    }
    solve();
    return 0;
}
