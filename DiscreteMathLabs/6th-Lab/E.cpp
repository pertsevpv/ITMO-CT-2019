#include <iostream>
#include "vector"
#include "set"
#include "algorithm"
#include "map"
#include "list"

using namespace std;

int n;
vector<vector<int>> G;
vector<int> deg;
vector<bool> used;

void solve() {
    set<int> leaves;
    vector<int> ans;
    used.assign(n, false);
    for (int i = 0; i < n; ++i)
        if (deg[i] == 1) leaves.insert(i);
    for (int i = 0; i < n - 2; ++i) {
        int v = *leaves.begin();
        leaves.erase(leaves.begin());
        used[v] = true;
        int u;
        for (int to : G[v])
            if (!used[to]) u = to;
        ans.push_back(u);
        deg[u]--;
        if (deg[u] == 1)leaves.insert(u);
    }
    for (int u : ans)
        cout << u + 1 << " ";
}

int main() {
    cin >> n;
    G.resize(n);
    deg.resize(n);
    used.resize(n);
    for (int i = 0; i < n - 1; ++i) {
        int u, v;
        cin >> u >> v;
        u--;
        v--;
        G[u].push_back(v);
        G[v].push_back(u);
        deg[u]++;
        deg[v]++;
    }
    solve();
    return 0;
}