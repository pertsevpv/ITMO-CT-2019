#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n;
vector<vector<int>> G;
vector<pair<int, int>> mt;
vector<bool> used;

bool cmp(pair<int, int> a, pair<int, int> b) {
    return a.second > b.second;
}

vector<pair<int, int>> order; // v - w

bool kuhn(int v) {
    if (used[v]) return false;
    used[v] = true;
    for (int u : G[v]) {
        if (mt[u].first == -1 || kuhn(mt[u].first)) {
            mt[u].first = v;
            mt[v].second = u;
            return true;
        }
    }
    return false;
}

void solve() {
    for (int i = 0; i < n; ++i) {
        int v = order[i].first;
        used.assign(n, false);
        kuhn(v);
    }
}

int main() {
    freopen("matching.in", "r", stdin);
    freopen("matching.out", "w", stdout);

    cin >> n;
    G.resize(n);
    mt.resize(n);
    mt.assign(n, make_pair(-1, -1));
    used.resize(n);

    for (int i = 0; i < n; ++i) {
        int w;
        cin >> w;
        order.emplace_back(i, w);
    }

    for (int i = 0; i < n; ++i) {
        int u;
        cin >> u;
        for (int j = 0; j < u; ++j) {
            int v;
            cin >> v;
            v--;
            G[i].push_back(v);
        }
    }

    sort(order.begin(), order.end(), cmp);
    solve();

    for (int i = 0; i < n; ++i)
        cout << mt[i].second + 1 << " ";

    return 0;
}