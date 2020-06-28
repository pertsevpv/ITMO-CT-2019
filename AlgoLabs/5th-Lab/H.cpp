#include <iostream>
#include <string.h>

using namespace std;

struct Node {
    long long x;
    int y;
    Node *l;
    Node *r;
    int size;
    bool needRev;

    Node(long long val) {
        x = val;
        y = rand();
        l = r = nullptr;
        size = 1;
        needRev = false;
    }
};

void push(Node *&t) {
    if (t == nullptr)return;
    if (t->needRev) {
        t->needRev = false;
        swap(t->l, t->r);
        if (t->l != nullptr)
            t->l->needRev = !t->l->needRev;
        if (t->r != nullptr)
            t->r->needRev = !t->r->needRev;
    }
}

int getSize(Node *&t) {
    if (t != nullptr)
        return t->size;
    else return 0;
}

void update(Node *&t) {
    if (t != nullptr)
        t->size = getSize(t->l) + getSize(t->r) + 1;
}

void recPrint(Node *t) {
    if (t == nullptr)return;
    push(t);
    recPrint(t->l);
    cout << t->x << " ";
    recPrint(t->r);
}

pair<Node *, Node *> split(Node *t, long long x) {
    pair<Node *, Node *> p;
    if (t == nullptr) {
        return make_pair(nullptr, nullptr);
    }
    push(t);
    int key = getSize(t->l);
    if (key < x) {
        p = split(t->r, x - key - 1);
        t->r = p.first;
        update(t);
        return make_pair(t, p.second);
    } else {
        p = split(t->l, x);
        t->l = p.second;
        update(t);
        return make_pair(p.first, t);
    }
}

Node *merge(Node *t1, Node *t2) {
    push(t1);
    push(t2);
    if (t1 == nullptr || t2 == nullptr) {
        if (t2 == nullptr) {
            update(t1);
            return t1;
        } else {
            update(t2);
            return t2;
        }
    } else if (t1->y > t2->y) {
        t1->r = merge(t1->r, t2);
        update(t1);
        return t1;
    } else {
        t2->l = merge(t1, t2->l);
        update(t2);
        return t2;
    }
}

void reverse(Node *&t, int l, int r) {
    pair<Node *, Node *> p1 = split(t, l);
    pair<Node *, Node *> p2 = split(p1.second, r - l + 1);
    p2.first->needRev = true;
    t = merge(merge(p1.first, p2.first), p2.second);
}

int main() {
    Node *t = nullptr;

    int n;
    int m;
    cin >> n >> m;
    int len = n;
    char s[4];
    for (int i = 0; i < n; i++) {
        long long x;
        Node *v = new Node(i + 1);
        t = merge(t, v);
    }
    while (m--) {
        int l, r;
        cin >> l >> r;
        reverse(t, l - 1, r - 1);
    }
    recPrint(t);
}