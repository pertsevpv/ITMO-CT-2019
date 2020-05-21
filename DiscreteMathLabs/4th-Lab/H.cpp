#include <iostream>
#include <vector>
#include <unordered_set>
#include <queue>
#include <map>

using namespace std;

int n, m, k;
vector<int> good;
vector<bool> T;
vector<vector<vector<int>>> rev;
vector<vector<int>> nxt;


void findGood(int q) {
    good[q] = true;
    for (int c = 0; c < 26; ++c) {
        if (!good[nxt[q][c]])
            findGood(nxt[q][c]);
    }
}

void init() {
    cin >> n >> m >> k;
    n++;
    good.resize(n);
    T.resize(n);
    nxt.resize(n, vector<int>(26));
    rev.resize(n, vector<vector<int>>(26));
    for (int i = 0; i < k; ++i) {
        int a;
        cin >> a;
        T[a] = true;
    }
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < 26; ++j) {
            int a, b;
            char c;
            cin >> a >> b >> c;
            nxt[a][c - 'a'] = b;
        }
    }
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < 26; ++j) {
            rev[nxt[i][j]][j].push_back(i);
        }
    }
    findGood(1);
}

bool contains(unordered_set<int> st, int val) {
    return st.find(val) != st.end();
};

void minimize() {
    vector<int> classes(n);
    vector<unordered_set<int>> P;
    queue<pair<int, int>> Q;

    unordered_set<int> Tset;
    unordered_set<int> nTset;

    for (int i = 0; i < n; i++) {
        if (T[i]) {
            Tset.insert(i);
            classes[i] = 0;
        } else {
            nTset.insert(i);
            classes[i] = 1;
        }
    }

    P.push_back(Tset);
    P.push_back(nTset);

    for (int i = 0; i < 26; ++i) {
        Q.push(pair<int, int>(0, i));
        Q.push(pair<int, int>(1, i));
    }

    while (!Q.empty()) {
        const pair<int, int> p = Q.front();
        Q.pop();

        map<int, vector<int>> inv;
        for (int q: P[p.first]) {
            for (int r: rev[q][p.second]) {
                int i = classes[r];
                inv[i].push_back(r);
            }
        }
        for (pair<int, vector<int>> x: inv) {
            if (!x.second.empty()) {
                int i = x.first;
                if (inv[i].size() < P[i].size()) {
                    unordered_set<int> tmp;
                    P.push_back(tmp);

                    int j = P.size() - 1;
                    for (int r: inv[x.first]) {
                        P[i].erase(r);
                        P[j].insert(r);
                    }

                    for (int r: P[j]) {
                        classes[r] = j;
                    }

                    for (int i = 0; i < 26; i++) {
                        Q.push(make_pair(P.size() - 1, i));
                    }
                }
            }
        }
    }
    vector<int> conv(n);
    for (int i = 0; i < n; ++i) {
        conv[i] = -1;
    }
    for (unordered_set<int> st:P) {
        if (contains(st, 0)) {
            for (int a:st) {
                conv[a] = 0;
            }
        }
        if (contains(st, 1) && conv[1] == -1) {
            for (int a:st) {
                conv[a] = 1;
            }
        }
    }
    int pos = 1;
    for (unordered_set<int> st:P) {
        int i = *st.begin();
        if (!good[i])continue;
        if (conv[i] != -1)continue;
        pos++;
        for (int a: st) {
            conv[a] = pos;
        }
    }
    vector<bool> fT(pos + 1);
    int fk = 0;
    for (int i = 0; i < n; ++i) {
        if (T[i] && conv[i] != -1 && !fT[conv[i]]) {
            fT[conv[i]] = true;
            fk++;
        }
    }
    if (fT[0])fk--;

    vector<vector<int>> fnext(n + 1, vector<int>(26));
    int fm = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < 26; j++) {
            if (conv[i] > 0 && conv[nxt[i][j]] > 0 && fnext[conv[i]][j] == 0) {
                fnext[conv[i]][j] = conv[nxt[i][j]];
                fm++;
            }
        }
    }
    n = pos + 1;
    m = fm;
    k = fk;
    T = fT;
    nxt = fnext;
}

void writeInFile() {
    cout << n - 1 << " " << m << " " << k << endl;
    for (int i = 0; i < n; i++) {
        if (T[i]) {
            cout << i << " ";
        }
    }
    cout << endl;
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < 26; ++j) {
            if (nxt[i][j] != 0)cout << i << " " << nxt[i][j] << " " << (char(j + 'a')) << endl;
        }
    }
}

int main() {
    freopen("minimization.in", "r", stdin);
    freopen("minimization.out", "w", stdout);
    init();
    minimize();
    writeInFile();
    return 0;
}
