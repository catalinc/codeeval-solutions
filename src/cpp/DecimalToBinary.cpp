#include <iostream>
#include <fstream>
#include <sstream>

using namespace std;

string decimalToBinary(int n) {
    if (n == 0) return "0";
    if (n == 1) return "1";
    string s;
    stringstream ss;
    int b;
    while (n > 0) {
        b = n & 1;
        ss.str("");
        ss.clear();
        ss << b;
        s.insert(0, ss.str());
        n = n >> 1;
    }
    return s;
}

int main(int argc, char* argv[]) {
    ifstream infile(argv[1]);
    int n;
    while (infile >> n) {
        cout << decimalToBinary(n) << endl;
    }
    infile.close();
    return 0;
}

