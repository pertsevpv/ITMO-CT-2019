#include <iostream>
using namespace std;

int stack[1000000];
int stackSize = 0;

int min(int a, int b) {
	if (a > b) {
		return b;
	}
	else {
		return a;
	}
}

int getMin() {
	return stack[stackSize - 1];
}

void add(int val) {
	if (stackSize == 0) {
		stack[stackSize++] = val;
	}
	else {
		stack[stackSize] = min(val, stack[stackSize - 1]);
		stackSize++;
	}
}

void removeLast() {
	--stackSize;
}

int main()
{
	int n;
	scanf("%d", &n);
	for (int i = 0; i < n; i++) {
		int t;
		scanf("%d", &t);
		if (t == 1) {
			int a;
			scanf("%d", &a);
			add(a);
		}else if(t==2){
			removeLast();
		}else {
			cout << getMin() << endl;
		}
	}
	return 0;
}