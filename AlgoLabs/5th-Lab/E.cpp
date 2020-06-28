#include <iostream>
#include <string.h>

using namespace std;

struct Node {
    long long x;
    int y;
    Node *l;
    Node *r;
    int numOfNodes;

    Node(long long val) {
        x = val;
        y = rand();
        l = r = nullptr;
        numOfNodes = 1;
    }
};

int getNum(Node *&t) {
    if (t != nullptr)
        return t->numOfNodes;
    else return 0;
}

void setNum(Node *&t) {
    if (t != nullptr)
        t->numOfNodes = getNum(t->l) + getNum(t->r) + 1;
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
    } else if (t->x < x) {
        p = split(t->r, x);
        t->r = p.first;
        setNum(t);
        return make_pair(t, p.second);
    } else {
        p = split(t->l, x);
        t->l = p.second;
        setNum(t);
        return make_pair(p.first, t);
    }
}

Node *merge(Node *t1, Node *t2) {
    if (t1 == nullptr || t2 == nullptr) {
        if (t2 == nullptr)
            return t1;
        else
            return t2;
    } else if (t1->y > t2->y) {
        t1->r = merge(t1->r, t2);
        setNum(t1);
        return t1;
    } else {
        t2->l = merge(t1, t2->l);
        setNum(t2);
        return t2;
    }
}

void insert(Node *&t, Node *v) {
    pair<Node *, Node *> p = split(t, v->x);
    t = merge(merge(p.first, v), p.second);
    setNum(t);
}

void remove(Node *&t, long long x) {
    pair<Node *, Node *> p1 = split(t, x);
    pair<Node *, Node *> p2 = split(p1.second, x + 1);
    t = merge(p1.first, p2.second);
    setNum(t);
}

bool exists(Node *&t, long long x) {
    if (t == nullptr) return false;
    if (x == t->x) return true;
    if (x < t->x)
        return exists(t->l, x);
    else
        return exists(t->r, x);
}

Node *findMax(Node *&t, long long k) {
    if (getNum(t->l) == k)
        return t;
    if (getNum(t->l) > k)
        return findMax(t->l, k);
    else
        return findMax(t->r, k - getNum(t->l) - 1);
}

int main() {
    Node *t = nullptr;
    int n;
    cin >> n;
    int c;
    long long x;
    while (n--) {
        cin >> c >> x;
        if (c == 1) {
            Node *v = new Node(x);
            if (!exists(t, x))
                insert(t, v);
        }
        if (c == 0) {
            cout << findMax(t, getNum(t) - x)->x << endl;
        }
        if (c == -1) {
            if (exists(t, x)) {
                remove(t, x);
            }
        }
    }
}