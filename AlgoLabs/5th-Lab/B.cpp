#include <iostream>
#include <string.h>

using namespace std;
struct Node {
    long long x;
    int y;
    Node *l;
    Node *r;

    Node(long long val) {
        x = val;
        y = rand();
        l = r = nullptr;
    }
};

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
        return make_pair(t, p.second);
    } else {
        p = split(t->l, x);
        t->l = p.second;
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
        return t1;
    } else {
        t2->l = merge(t1, t2->l);
        return t2;
    }
}

void insert(Node *&t, Node *v) {
    pair<Node *, Node *> p = split(t, v->x);
    t = merge(merge(p.first, v), p.second);
}

void remove(Node *&t, long long x) {
    pair<Node *, Node *> p1 = split(t, x);
    pair<Node *, Node *> p2 = split(p1.second, x + 1);
    t = merge(p1.first,p2.second);
}

bool exists(Node *&t, long long x) {
    if (t == nullptr) return false;
    if (x == t->x) return true;
    if (x < t->x)
        return exists(t->l, x);
    else
        return exists(t->r, x);
}

Node *next(Node *&t, long long x) {
    Node *cur = t;
    Node *res = nullptr;

    while (cur != nullptr) {
        if (cur->x > x) {
            res = cur;
            cur = cur->l;
        } else {
            cur = cur->r;
        }
    }
    return res;
}

Node *prev(Node *&t, long long x) {
    Node *cur = t;
    Node *res = nullptr;

    while (cur != nullptr) {
        if (cur->x < x) {
            res = cur;
            cur = cur->r;
        } else {
            cur = cur->l;
        }
    }

    return res;
}


int main() {
    Node *t = nullptr;
    char s[10];
    long long x;
    while (cin >> s >> x) {
        if (strcmp(s, "insert") == 0) {
            Node *v = new Node(x);
            if (!exists(t, x))
                insert(t, v);
            continue;
        }
        if (strcmp(s, "exists") == 0) {
            cout << ((exists(t, x)) ? "true" : "false") << endl;
            continue;
        }
        if (strcmp(s, "delete") == 0) {
            remove(t, x);
            continue;
        }
        if (strcmp(s, "next") == 0) {
            Node *v = next(t, x);
            if (v == nullptr)
                cout << "none" << endl;
            else
                cout << v->x << endl;
            continue;
        }
        if (strcmp(s, "prev") == 0) {
            Node *v = prev(t, x);
            if (v == nullptr)
                cout << "none" << endl;
            else
                cout << v->x << endl;
        }

    }
    return 0;
}