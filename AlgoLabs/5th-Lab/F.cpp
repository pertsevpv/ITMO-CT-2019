#include <iostream>
#include <string.h>

using namespace std;

struct Node {
    long long x;
    int y;
    Node *l;
    Node *r;
    int size;

    Node(long long val) {
        x = val;
        y = rand();
        l = r = nullptr;
        size = 1;
    }
};

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
    recPrint(t->l);
    cout << t->x << " ";
    recPrint(t->r);
}

pair<Node *, Node *> split(Node *t, long long x) {
    pair<Node *, Node *> p;
    if (t == nullptr) {
        return make_pair(nullptr, nullptr);
    }
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

void insert(Node *&t, Node *v, int pos) {
    pair<Node *, Node *> p = split(t, pos);
    t = merge(merge(p.first, v), p.second);
    update(t);
}

void remove(Node *&t, int pos) {
    pair<Node *, Node *> p1 = split(t, pos + 1);
    pair<Node *, Node *> p2 = split(p1.first, pos);
    t = merge(p2.first, p1.second);
    update(t);
}

bool exists(Node *&t, long long x) {
    if (t == nullptr) return false;
    if (x == t->x) return true;
    if (x < t->x)
        return exists(t->l, x);
    else
        return exists(t->r, x);
}

void moveToStart(Node *&t, int l, int r) {
    pair<Node *, Node *> p1 = split(t, r + 1);
    pair<Node *, Node *> p2 = split(p1.first, l);
    t = merge(merge(p2.second, p2.first), p1.second);
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
        cin >> x;
        Node *v = new Node(x);
        t = merge(t, v);
    }
    while (m--) {
        scanf("%s", s);
        if (strcmp(s, "add") == 0) {
            len++;
            long long x;
            int pos;
            cin >> pos >> x;
            Node *v = new Node(x);
            insert(t, v, pos);
        } else {
            len--;
            int pos;
            cin >> pos;
            remove(t, pos - 1);
        }
    }
    cout << len << endl;
    recPrint(t);
}