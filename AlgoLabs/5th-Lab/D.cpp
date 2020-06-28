#include <iostream>
#include <string.h>

using namespace std;

struct Node {
    long long x;
    int y;
    Node *l;
    Node *r;
    long long sum;

    Node(long long val) {
        x = sum = val;
        y = rand();
        l = r = nullptr;
    }
};

long long  getSum(Node *&t) {
    if (t != nullptr)
        return t->sum;
    else return 0;
}

void setSum(Node *&t) {
    if (t != nullptr)
        t->sum = getSum(t->l) + getSum(t->r) + t->x;
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
        setSum(t);
        return make_pair(t, p.second);
    } else {
        p = split(t->l, x);
        t->l = p.second;
        setSum(t);
        return make_pair(p.first, t);
    }
}

Node *merge(Node *t1, Node *t2) {
    if (t1 == nullptr || t2 == nullptr) {
        if (t2 == nullptr) {
            setSum(t1);
            return t1;
        } else {
            setSum(t2);
            return t2;
        }
    } else if (t1->y > t2->y) {
        t1->r = merge(t1->r, t2);
        setSum(t1);
        return t1;
    } else {
        t2->l = merge(t1, t2->l);
        setSum(t2);
        return t2;
    }
}

void insert(Node *&t, Node *v) {
    pair<Node *, Node *> p = split(t, v->x);
    t = merge(merge(p.first, v), p.second);
    setSum(t);
}

void remove(Node *&t, long long x) {
    pair<Node *, Node *> p1 = split(t, x);
    pair<Node *, Node *> p2 = split(p1.second, x + 1);
    t = merge(p1.first, p2.second);
    setSum(t);
}

bool exists(Node *&t, long long x) {
    if (t == nullptr) return false;
    if (x == t->x) return true;
    if (x < t->x)
        return exists(t->l, x);
    else
        return exists(t->r, x);
}

long long findSum(Node *&t, long long l, long long r) {
    pair<Node *, Node *> p1 = split(t, l);
    pair<Node *, Node *> p2 = split(p1.second, r + 1);
    long long res = 0;
    if (p2.first != nullptr)res = p2.first->sum;
    t = merge(merge(p1.first, p2.first), p2.second);
    return res;
}

int main() {
    Node *t = nullptr;

    int n;
    cin >> n;
    char c;
    long long x;
    long long y = 0;
    while (n--) {
        cin >> c;
        if (c == '+') {
            scanf("%lli", &x);
            Node *v = new Node((x + y) % 1000000000);
            if (!exists(t, (x + y) % 1000000000))
                insert(t, v);
            y = 0;
        } else {
            long long int l;
            long long int r;
            scanf("%lli %lli", &l, &r);
            y = findSum(t, l, r);
            cout << y << endl;
        }

    }
}