#include <iostream>
#include "vector"
#include "math.h"
#include <set>
#include <algorithm>

using namespace std;

int n = 0;
int m = 0;
vector<pair<int, int>> points;
vector<float> minEdges;
vector<int> edges;
vector<int> p;
vector<bool> visited;

int get(int v) {
    if (p[v] == v)return v;
    else {
        p[v] = get(p[v]);
        return p[v];
    }
}

void unite(int a, int b) {
    a = get(a);
    b = get(b);
    if (a != b)p[a] = b;
}

pair<int, int> edge(int u, int v) {
    return make_pair(min(u, v), max(u, v));
}

float d(pair<int, int> m1, pair<int, int> m2) {
    float x1 = m1.first;
    float y1 = m1.second;
    float x2 = m2.first;
    float y2 = m2.second;
    return sqrt(pow(x2 - x1, 2) + pow(y2 - y1, 2));
}


float findMST() {
    minEdges.resize(n);
    minEdges.assign(n, INT16_MAX);
    minEdges[0] = 0;
    edges.resize(n);
    edges.assign(n, -1);
    visited.resize(n);
    visited.assign(n, false);
    float mst = 0.0;
    for (int i = 0; i < n; ++i) {
        int v = -1;
        for (int j = 0; j < n; ++j) {
            if (!visited[j] && (v == -1 || minEdges[j] < minEdges[v])) {
                v = j;
            }
        }
        visited[v] = true;
        int e = edges[v];
        if (e != -1)mst += d(points[v], points[e]);
        for (int u = 0; u < n; ++u) {
            float dist = d(points[v], points[u]);
            if (dist < minEdges[u]) {
                minEdges[u] = dist;
                edges[u] = v;
            }
        }
    }
    return mst;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cin >> n;
    for (int i = 0; i < n; ++i) {
        int x, y;
        cin >> x >> y;
        points.emplace_back(x, y);
    }

    cout << findMST();
    return 0;
}
