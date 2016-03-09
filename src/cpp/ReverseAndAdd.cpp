#include <iostream>
#include <fstream>
#include <string>
#include <deque>
#include <utility>

using namespace std;

void digits(int n, deque<int>& digitsOfN) {
	digitsOfN.clear();
	while(n) {
		digitsOfN.push_front(n % 10);
		n = n / 10;
	}
}

int reverse(const deque<int>& digitsOfN) {
	int result = digitsOfN[digitsOfN.size() - 1];
	for (int i = digitsOfN.size() - 2; i >= 0; i--) {
		result = result * 10 + digitsOfN[i];
	}
	return result;
}

bool isPalidrome(const deque<int>& digits) {
	for (int i = 0, j = digits.size() - 1; i < j; i++, j--) {
		if (digits[i] != digits[j]) {
			return false;
		}
	}
	return true;
}

void reverseAndAddUntilIsPalindrome(int n, pair<int, int>& result) {
	deque<int> digitsOfN;
	digits(n, digitsOfN);
	
	n = n + reverse(digitsOfN);
	int iterations = 1;
	digits(n, digitsOfN);
	while(!isPalidrome(digitsOfN)) {
		n = n + reverse(digitsOfN);
		digits(n, digitsOfN);
		iterations++;
	}
	result.first = iterations;
	result.second = n;
}

int main(int argc, char* argv[]) {
	ifstream infile(argv[1]);
	int n;
	pair<int, int> result;
	while (infile >> n) {
		reverseAndAddUntilIsPalindrome(n, result);
		cout << result.first << ' ' << result.second << endl;
	}
	infile.close();
	return 0;
}
