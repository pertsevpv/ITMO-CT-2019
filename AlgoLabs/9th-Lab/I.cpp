#include <iostream>
#include "vector"
#include <queue>
#include "set"
#include <algorithm>
#include "map"

using namespace std;

enum result {
    FIRST, SECOND, DRAW
};

int n = 0;
int m = 0;
vector<vector<int >> G;
vector<vector<int >> H;
vector<result> game;
vector<int> sizes;
vector<bool> visited;


void dfs(int v) {
    visited[v] = true;
    for (int i : H[v]) {
        if (!visited[i]) {
            if (game[v] == SECOND) {
                game[i] = FIRST;
            } else {
                sizes[i]--;
                if (sizes[i] == 0)game[i] = SECOND;
                else continue;
            }
            dfs(i);
        }
    }
}

void solve() {
    vector<int> leaves;
    for (int i = 0; i < n; ++i) {
        sizes[i] = G[i].size();
        if (sizes[i] == 0) {
            game[i] = SECOND;
            leaves.push_back(i);
        }
    }
    for (int i:leaves)dfs(i);
    for (int i = 0; i < n; ++i) {
        switch (game[i]) {
            case FIRST:
                cout << "FIRST\n";
                break;
            case SECOND:
                cout << "SECOND\n";
                break;
            case DRAW:
                cout << "DRAW\n";
                break;
        }
    }
    cout << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    while (cin >> n >> m) {
        G.clear();
        G.resize(n);

        H.clear();
        H.resize(n);

        game.clear();
        game.resize(n);
        game.assign(n, DRAW);

        sizes.clear();
        sizes.resize(n);
        sizes.assign(n, 0);

        visited.clear();
        visited.resize(n);
        visited.assign(n, false);

        for (int i = 0; i < m; ++i) {
            int u, v;
            cin >> u >> v;
            u--;
            v--;
            G[u].push_back(v);
            H[v].push_back(u);
        }
        solve();
    }
    return 0;
}