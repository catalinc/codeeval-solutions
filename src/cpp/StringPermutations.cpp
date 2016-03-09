#include <iostream>
#include <fstream>
#include <sstream>
#include <algorithm>

using namespace std;

void swap_chars(string & s, int i, int j) {
    char t = s[i];
    s[i] = s[j];
    s[j] = t;
}

void reverse_chars(string & s, int start, int end) {
    while (start < end) {
        swap_chars(s, start, end);
        start++;
        end--;
    }
}

void printPermutations(string s) {
    sort(s.begin(), s.end());
    cout << s << flush;

    int k, l;
    while (true) {
        k = -1;
        l = -1;
        for (int i = s.length() - 2; i >= 0; i--) {
            if (s[i] < s[i + 1]) {
                k = i;
                break;
            }
        }
        if (k < 0) {
            break;
        }
        for (int i = s.length() - 1; i > k; i--) {
            if (s[i] > s[k]) {
                l = i;
                break;
            }
        }
        swap_chars(s, k, l);
        reverse_chars(s, k + 1, s.length() - 1);

        cout << ',' << s << flush;
    }

    cout << endl;
}

int main(int argc, char* argv[]) {
    ifstream infile(argv[1]);
    string line;
    while (getline(infile, line)) {
        printPermutations(line);
    }
    infile.close();
    return 0;
}