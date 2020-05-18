#include <iostream>
using namespace std;

int queue[100000];
int beginQ = 0;
int endQ = 0;

int main()
{
	int n;
	scanf("%d", &n);
	for (int i = 0; i < n; i++) {
		int a;
		scanf("%d", &a);
		switch (a) {
		case 1:
			int id;
			scanf("%d", &id);
			queue[endQ++] = id;
			break;
		case 2:
			beginQ++;
			break;
		case 3:
			endQ--;
			break;
		case 4: {
			int countQ = 0;
			int q;
			scanf("%d", &q);
			for (int i = beginQ; i < endQ; i++) {
				if (queue[i] == q) {
					break;
				}
				countQ++;
			}
			printf("%d", countQ);
			printf("%c",'\n');
			break;
		}
		case 5:
			printf("%d",queue[beginQ]);
			printf("%c",'\n');
			break;
		}
	}
	return 0;
}