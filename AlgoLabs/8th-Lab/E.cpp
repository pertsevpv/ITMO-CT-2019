#include <iostream>
#include "vector"
#include <set>
#include <iterator>
#include <map>

using namespace std;

int n = 0;
int m = 0;
int time = 0;
int maxColor = 0;
vector<set<int>> G;
vector<bool> visited;
vector<int> Up;
vector<int> In;
vector<int> colors;
map<pair<int, int>, set<int >> edges;

int min(int a, int b) {
    if (a < b)return a;
    return b;
}

int max(int a, int b) {
    if (a > b)return a;
    return b;
}

pair<int, int> edge(int u, int v) {
    return make_pair(min(u, v), max(u, v));
}

void dfs(int v, int p = -1) {
    time++;
    In[v] = Up[v] = time;
    visited[v] = true;
    for (int u : G[v]) {
        if (u == p)continue;
        if (visited[u])Up[v] = min(Up[v], In[u]);
        else {
            dfs(u, v);
            Up[v] = min(Up[v], Up[u]);
        }
    }
}

void paint(int v, int color, int par = -1) {
    visited[v] = true;
    for (int u:G[v]) {
        if (u == par)continue;
        if (!visited[u]) {
            if (Up[u] >= In[v]) {
                int col = ++maxColor;
                for (int pos: edges[edge(u, v)])
                    colors[pos] = col;
                paint(u, col, v);
            } else {
                for (int pos: edges[edge(u, v)])
                    colors[pos] = color;
                paint(u, color, v);
            }
        } else if (In[u] <= In[v]) {
            for (int pos: edges[edge(u, v)])
                colors[pos] = color;
        }
    }
}

void solve() {
    for (int i = 0; i < n; ++i)visited[i] = false;
    for (int v = 0; v < n; ++v) {
        if (!visited[v]) paint(v, maxColor);
    }
}

void findCutPoint() {
    visited.resize(n);
    for (int i = 0; i < n; ++i) visited[i] = false;
    In.resize(n);
    Up.resize(n);
    for (int u = 0; u < n; ++u) {
        if (!visited[u])dfs(u);
    }
}

int main() {
    cin >> n >> m;
    G.resize(n);
    for (int i = 0; i < m; ++i) {
        int u, v;
        cin >> u >> v;
        u--;
        v--;
        G[u].insert(v);
        G[v].insert(u);
        edges[edge(u, v)].insert(i);
    }
    colors.resize(m);
    findCutPoint();
    solve();
    cout << maxColor << endl;
    for (int c:colors)
        cout << c << " ";
    return 0;
}
