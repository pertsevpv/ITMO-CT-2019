#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

struct Edge {
    int u, v;
    unsigned long long w;
    int number;
    bool used;
};

int n, m;
unsigned long long s = 0;
unsigned long long sum = 0;
vector<Edge> E;
vector<int> p, r;

int get(int v) {
    if (v != p[v]) p[v] = get(p[v]);
    return p[v];
}

void unite(int x, int y) {
    x = get(x);
    y = get(y);
    if (r[x] < r[y])swap(x, y);
    p[y] = x;
    r[x] += r[y];
}

bool cmp_ord(Edge a, Edge b) {
    return b.number < a.number;
}

bool cmp_weight(Edge a, Edge b) {
    return a.w > b.w;
}

unsigned long long getMax() {
    p.resize(n);
    r.resize(n);
    for (int i = 0; i < n; ++i) {
        p[i] = i;
        r[i] = 1;
    }
    unsigned long long ans = 0;
    for (int i = 0; i < m; ++i) {

        int u = E[i].u;
        int v = E[i].v;
        unsigned long long w = E[i].w;
        if (get(u) != get(v)) {
            E[i].used = true;
            ans += w;
            unite(u, v);
        }
    }
    return ans;
}

int main() {

    freopen("destroy.in", "r", stdin);
    freopen("destroy.out", "w", stdout);

    cin >> n >> m >> s;

    for (int i = 0; i < m; ++i) {
        int u, v;
        unsigned long long w;
        cin >> u >> v >> w;
        u--;
        v--;
        Edge e = {u, v, w, i + 1, false};
        E.push_back(e);
        sum += w;
    }
    sort(E.begin(), E.end(),cmp_weight);

    unsigned long long max = getMax();

    for (int cur = 0; max + s < sum; cur++) {
        if (!E[cur].used) {
            E[cur].used = true;
            max += E[cur].w;
        }
    }
    vector<Edge> ans;

    for (Edge e : E)
        if (!e.used)ans.push_back(e);

    sort(ans.begin(), ans.end(), cmp_ord);
    cout << ans.size() << "\n";
    for (Edge e:ans)cout << e.number << " ";
}