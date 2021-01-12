#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

unsigned n, m;
vector<pair<unsigned, int>> S;
vector<bool> masks, checked;

bool check2(unsigned A);

bool check3(unsigned A, unsigned B);

bool cmp(pair<unsigned, int> a, pair<unsigned, int> b) {
    return a.second > b.second;
}

bool axiom1() {
    for (pair<int, int> A : S)
        if (A.second == 0)return true;
    return false;
}

bool axiom2() {
    for (pair<int, int> A : S)
        if (!check2(A.first))return false;
    
    return true;
}

bool axiom3() {
    for (int i = 0; i < S.size(); i++)
        for (int j = i + 1; j < S.size(); j++)
            if (S[i].second > S[j].second)
                if (!check3(S[i].first, S[j].first))
                    return false;

    return true;
}

bool check2(unsigned A) {
    if (checked[A]) return true;
    bool ans = true;
    for (unsigned bit = 1; bit <= A; bit <<= (unsigned) 1) {
        if ((A | bit) == A) {
            if (!masks[A ^ bit])
                return false;
            else
                ans = ans & check2(A ^ bit);
        }
    }
    checked[A] = ans;
    return ans;
}

bool check3(unsigned A, unsigned B) {
    unsigned C = A & ~B;
    for (unsigned bit = 1; bit <= C; bit <<= (unsigned)1)
        if ((C | bit) == C && masks[B | bit])
            return true;

    return false;
}

int main() {
    freopen("check.in", "r", stdin);
    freopen("check.out", "w", stdout);

    cin >> n >> m;
    S.resize(m);
    masks.resize((unsigned) 1 << n);
    masks.assign((unsigned) 1 << n, false);
    checked.resize((unsigned) 1 << n);
    checked.assign((unsigned) 1 << n, false);

    for (int i = 0; i < m; i++) {
        int s;
        cin >> s;

        unsigned mask = 0;
        for (int j = 0; j < s; j++) {
            unsigned x;
            cin >> x;
            x--;
            mask |= (unsigned) 1 << x;
        }

        S[i] = make_pair(mask, s);
        masks[mask] = true;
    }
    sort(S.begin(), S.end(), cmp);

    if (axiom1() && axiom2() && axiom3()) cout << "YES";
    else cout << "NO";

    return 0;
}