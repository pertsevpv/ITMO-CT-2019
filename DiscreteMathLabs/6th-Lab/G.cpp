#include <iostream>
#include "vector"
#include "set"
#include "algorithm"
#include "map"
#include "list"

using namespace std;

int n;
int m;
int maxDeg = 0;
vector<vector<int>> G;
vector<int> color;

int mex(set<int> s) {
    int cur = 1;
    for (int a:s) {
        if (a == 0)continue;
        if (cur == a)cur++;
        else break;
    }
    return cur;
}

void dfs(int v) {
    set<int> s;
    for (int u : G[v]) s.insert(color[u]);
    color[v] = mex(s);
    maxDeg = max(maxDeg, (int) G[v].size());
    for (int u : G[v])
        if (color[u] == 0) dfs(u);
}


int main() {
    cin >> n >> m;
    G.resize(n);
    color.resize(n);
    color.assign(n, 0);
    for (int i = 0; i < m; ++i) {
        int u, v;
        cin >> u >> v;
        u--;
        v--;
        G[u].push_back(v);
        G[v].push_back(u);
    }
    dfs(0);
    if (maxDeg % 2 == 0)maxDeg++;
    cout << maxDeg << "\n";
    for (int c : color)cout << c << "\n";
    return 0;
}