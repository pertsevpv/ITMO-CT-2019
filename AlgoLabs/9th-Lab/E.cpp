#include <iostream>
#include "vector"
#include <queue>
#include "set"
#include <algorithm>
#include "map"
#include "math.h"

using namespace std;

int n = 0;
int m = 0;
int s = 0;


vector<vector<int> > G;
vector<pair<pair<int, int>, long long >> E;
vector<bool> visited;
vector<long long> d;

void solve() {
    d.resize(n);
    d.assign(n, LLONG_MAX);
    d[s] = 0;
    for (int i = 0; i < n; ++i) {
        for (pair<pair<int, int>, long long> edge : E) {
            int u = edge.first.first;
            int v = edge.first.second;
            long long w = edge.second;
            if ((d[u] < LLONG_MAX) && (d[v] > d[u] + w)) {
                d[v] = max(LLONG_MIN, d[u] + w);
            }
        }
    }

}

void dfs(int v) {
    visited[v] = true;
    for (int u : G[v])
        if (!visited[u])
            dfs(u);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> n >> m >> s;
    s--;

    G.resize(n);

    visited.resize(n);
    visited.assign(n, false);

    for (int i = 0; i < m; ++i) {
        int u, v;
        long long w;
        cin >> u >> v >> w;
        u--;
        v--;
        G[u].push_back(v);
        E.push_back({{u, v}, w});
    }

    solve();
    visited.assign(n, false);

    for (int i = 0; i < n; ++i) {
        for (pair<pair<int, int>, long long> edge : E) {
            int u = edge.first.first;
            int v = edge.first.second;
            long long w = edge.second;
            if ((d[u] < LLONG_MAX) && (d[v] > d[u] + w) && !visited[v]) {
                dfs(v);
            }
        }
    }

    for (int i = 0; i < n; ++i) {
        if (d[i] == LLONG_MAX)
            cout << "*\n";
        else if (visited[i])
            cout << "-\n";
        else
            cout << d[i] << "\n";
    }

    return 0;
}