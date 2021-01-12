#include <vector>
#include <iostream>
#include <algorithm>
#include "set"

using namespace std;

int n;
vector<pair<int, int >> task; // d - w


bool cmp(pair<int, int> a, pair<int, int> b) {
    return a.second > b.second;
}


int main() {
    freopen("schedule.in", "r", stdin);
    freopen("schedule.out", "w", stdout);

    cin >> n;
    set<int> time;
    for (int i = 0; i < n; ++i) {
        int d, w;
        cin >> d;
        cin >> w;
        task.emplace_back(d, w);
        time.insert(i);
    }
    time.insert(INT32_MAX);
    sort(task.begin(), task.end(), cmp);

    long long res = 0;
    for (pair<int, int> t : task) {
        if (*time.begin() >= t.first) {
            res += t.second;
        } else {
            auto iter = time.lower_bound(t.first);
            if (iter != time.begin()) --iter;
            time.erase(iter);
        }
    }
    cout << res;
}