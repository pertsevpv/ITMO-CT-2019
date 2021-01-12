#include <iostream>
#include "vector"
#include "set"
#include "algorithm"
#include "map"
#include "list"

using namespace std;

int n;
vector<int> code;
vector<int> deg;
vector<bool> used;

void solve() {
    vector<pair<int, int >> ans;
    deg.assign(n, 1);
    for (int i = 0; i < n - 2; ++i)
        deg[code[i]]++;
    set<int> leaves;
    for (int i = 0; i < n; ++i)
        if (deg[i] == 1) leaves.insert(i);
    for (int i = 0; i < n - 2; ++i) {
        int v = *leaves.begin();
        leaves.erase(leaves.begin());

        int u = code[i];
        ans.emplace_back(v, u);
        deg[u]--;
        if (deg[u] == 1)leaves.insert(u);
    }
    ans.emplace_back(*leaves.begin(), *--leaves.end());
    for (pair<int, int> p: ans)
        cout << p.first + 1 << " " << p.second + 1 << "\n";
}

int main() {
    cin >> n;
    deg.resize(n);
    for (int i = 0; i < n - 2; ++i) {
        int u;
        cin >> u;
        u--;
        code.push_back(u);
    }
    solve();
    return 0;
}