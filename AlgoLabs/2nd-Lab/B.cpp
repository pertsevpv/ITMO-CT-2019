#include <iostream>
using namespace std;

int balls[100000][2];
int arrSize = 0;
int countBalls = 0;

int main()
{
	int n;
	scanf("%d", &n);
	if (n > 0) {
		scanf("%d", &balls[0][0]);
		balls[0][1] = 1;
		arrSize++;
	}
	for (int i = 1; i < n; i++){
		int a;
		scanf("%d", &a);
		if (a == balls[arrSize - 1][0]) {
			balls[arrSize - 1][1]++;
		}else {
			if (balls[arrSize - 1][1] >= 3) {
				countBalls += balls[arrSize - 1][1];
				arrSize--;				
			}
			if (balls[arrSize - 1][0] == a) {
				balls[arrSize - 1][1]++;
			}else {
				arrSize++;
				balls[arrSize - 1][0] = a;
				balls[arrSize - 1][1] = 1;
			}

		}
	}
	if (balls[arrSize - 1][1] >= 3) {
		countBalls += balls[arrSize - 1][1];
	}
	printf("%d",countBalls);
	return 0;
}