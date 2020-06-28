#include <iostream>
#include <stdlib.h>
#include <stdint.h>
#include <math.h>
#include <vector>
using namespace std;

struct Node {
    int num;
    int seg;
    int set;
    int leafs;
    bool hasUpd;
    Node() {
        num = 0;
        seg = 0;
        set = 0;
        leafs = 1;
        hasUpd = 0;
    }
    Node(int l) {
        num = 0;
        seg = 0;
        set = 0;
        leafs = l;
        hasUpd = 0;
    }
};

vector<Node> tree;
vector<char> color;
vector<int> vleft;
vector<int> vright;
int k;

int min(int a,int b) {
    if (a > b) return b;
    else return a;
}

void build(int v,int l) {
    if (v >= k - 1) {
        tree[v] = Node(1);
        return;
    }
    tree[v] = Node(l);
    build(2 * v + 1, l / 2);
    build(2 * v + 2, l / 2);
}

int max(int a, int b) {
    if (a > b) return a;
    else return b;
}

int getLeafs(int v) {
    int count = 0;
    while (v > 0) {
        v = (v - 1) / 2;
        count++;
    }
    return (1 << ((int)(log2(2 * k) - count - 1)));
}

void push(int v) {
    if (tree[v].hasUpd) {
        tree[v].num = tree[v].set * tree[v].leafs;
        tree[v].seg = tree[v].set;
        tree[v].hasUpd = false;
        if (v < k - 1) {
            tree[v * 2 + 1].set = tree[v].set;
            tree[v * 2 + 2].set = tree[v].set;

            tree[v * 2 + 1].hasUpd = true;
            tree[v * 2 + 2].hasUpd = true;
        }
    }
}

int nextPow(int n) {
    int p = 1;
    while (p < n) p *= 2;
    return p;
}

bool getRight(int v) {
    push(v);
    if (v >= k - 1) {
        return tree[v].num == 1;
    }
    return getRight(v * 2 + 2);
}

bool getLeft(int v) {
    push(v);
    if (v >= k - 1) {
        return tree[v].num == 1;
    }
    return getLeft(v * 2 + 1);
}

 void update(int v, int l, int r, int a, int b, int col) {
    if (b <= l || a >= r) return;
    if (l >= a && r <= b) {
        push(v);
        tree[v].set = col;
        tree[v].hasUpd = true;
        return;
    }
    push(v);
    update(2 * v + 1, l, (l + r) / 2, a, b, col);
    update(2 * v + 2, (l + r) / 2, r, a, b, col);

    bool right = getRight(2 * v + 1);
    bool left = getLeft(2 * v + 2);

    tree[v].num = tree[v * 2 + 1].num + tree[2 * v + 2].num;
    tree[v].seg = tree[v * 2 + 1].seg + tree[2 * v + 2].seg;

    if (left && right) {
        tree[v].seg--;
    }
}


int main(){
    int n;
    scanf("%d", &n);

    color.resize(n);
    vleft.resize(n);
    vright.resize(n);

    int mmax = INT32_MIN;
    int mmin = 0;
    for (int i = 0; i < n; i++) {
        scanf("\n%c %d %d", &color[i], &vleft[i], &vright[i]);
        if (vright[i] > 0) vright[i]--;
        else vright[i]++;

        mmax = max(vleft[i] + vright[i], mmax);
        mmin = min(mmin, vleft[i]);
    }
    int len = mmax - mmin + 1;

    k = nextPow(len);
    tree.resize(2 * k - 1);
    build(0, k);

    for (int i = 0; i < n; i++) {
        update(0, 0, k, vleft[i] - mmin, vleft[i] + vright[i] - mmin + 1, color[i]=='B' ? 1 : 0);
        printf("%d %d\n", tree[0].seg, tree[0].num);
    }
    return 0;
}