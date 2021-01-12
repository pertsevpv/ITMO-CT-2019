#include <iostream>
#include "vector"
#include "algorithm"
#include "map"
#include "list"

using namespace std;

map<pair<int, int>, bool> ans;

bool cmp(int i, int j) {
    if (ans.find(make_pair(i, j)) == ans.end()) {
        cout << 1 << " " << i << " " << j << "\n";
        cout.flush();
        string s;
        cin >> s;
        ans[make_pair(i, j)] = s == "YES";
        ans[make_pair(j, i)] = !ans[make_pair(i, j)];
    }
    return ans[make_pair(i, j)];
}

int main() {
    int n;
    cin >> n;
    list<int> vec;
    for (int i = 0; i < n; ++i)
        vec.push_back(i + 1);

    vec.sort(cmp);
    
    cout << 0 << " ";
    for (int v:vec)
        cout << v << " ";
    return 0;
}