#include <iostream>
#include <string>
#include <sstream>
#include <fstream>
#include <vector>

using namespace std;

int shortestPeriod(const string & s) {
    for (unsigned int i = 1; i < s.length(); i++) {
        if (s[i] == s[0]) {
            bool isPeriod = true;
            for (unsigned int j = 0; j < i; j++) {
                if (s[i + j] != s[j]) {
                    isPeriod = false;
                    break;
                }
            }
            if (isPeriod) return i;
        }
    }
    return s.length();
}

int main(int argc, char* argv[]) {
    ifstream infile(argv[1]);
    string line;
    while (getline(infile, line)) {
        cout << shortestPeriod(line) << endl;
    }
    infile.close();
    return 0;
}
