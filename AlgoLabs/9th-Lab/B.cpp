#include <iostream>
#include "vector"
#include <queue>
#include "set"
#include <algorithm>
#include "map"

using namespace std;

int n = 0;
int m = 0;
vector<map<int, int >> G;
vector<int> d;

void dijkstra(int s) {
    d.assign(n, INT32_MAX);
    d[s] = 0;
    set<pair<int, int>> set;

    set.insert({0, s});
    while (!set.empty()) {
        auto f = set.begin();
        int u = f->second;
        set.erase(f);

        for (pair<int, int> next :G[u]) {
            int v = next.first;
            int len = next.second;
            if (d[v] > d[u] + len) {
                if (set.find({d[v], v}) != set.end())
                    set.erase(set.find({d[v], v}));
                d[v] = d[u] + len;
                set.insert({d[v], v});
            }
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cin >> n;
    cin >> m;
    G.resize(n);
    d.resize(n);

    for (int i = 0; i < m; ++i) {
        int u, v, w;
        cin >> u >> v >> w;
        u--;
        v--;
        if (G[u][v] == 0) {
            G[u][v] = w;
            G[v][u] = w;
        } else {
            G[u][v] = min(G[u][v], w);
            G[v][u] = min(G[v][u], w);
        }
    }

    dijkstra(0);
    for (int i = 0; i < n; ++i)
        cout << d[i] << " ";
    return 0;
}
