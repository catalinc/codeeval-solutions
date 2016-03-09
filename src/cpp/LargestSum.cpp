#include <iostream>
#include <fstream>
#include <sstream>
#include <vector>
#include <algorithm>

using namespace std;

int maxSum(const vector<int> & v) {
    int maxSum = v[0];
    int maxEndingHere = v[0];
    for (int i = 1; i < v.size(); i++) {
        maxEndingHere = max(v[i], v[i] + maxEndingHere);
        maxSum = max(maxSum, maxEndingHere);
    }
    return maxSum;
}

vector<int> parseIntVector(string s) {
    vector<int> v;
    stringstream ss(s);
    int n;
    while(ss >> n) {
        v.push_back(n);
        if (ss.peek() == ',') ss.ignore();
    }
    return v;
}

int main(int argc, char* argv[]) {
    ifstream infile(argv[1]);
    string line;
    vector<int> v;
    while (getline(infile, line)) {
        v = parseIntVector(line);
        cout << maxSum(v) << endl;
    }
    infile.close();
    return 0;
}

