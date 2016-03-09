#include <iostream>
#include <fstream>
#include <sstream>
#include <vector>
#include <algorithm>

using namespace std;

string lcs(const string & s1, const string & s2) {
    vector< vector<int> > m(s1.length() + 1);
    for (unsigned int i = 0; i < m.size(); i++) {
        m[i] = vector<int>(s2.length() + 1, 0);
    }

    for (unsigned int i = 0; i < s1.length(); i++) {
        int r = i + 1;
        for (unsigned int j = 0; j < s2.length(); j++) {
            int c = j + 1;
            if (s1[i] == s2[j]) {
                m[r][c] = m[r - 1][c - 1] + 1;
            } else {
                m[r][c] = max(m[r][c - 1], m[r - 1][c]);
            }
        }
    }
    
    string ret;
    for (int r = s1.length(), c = s2.length(); r > 0 && c > 0; ) {
        if (s1[r - 1] == s2[c - 1]) {
            ret.insert(0, 1, s1[r - 1]);
            r--;
            c--;
        } else {
            if (m[r][c - 1] > m[r - 1][c]) {
                c--;
            } else {
                r--;
            }
        }
    }
    return ret;
}

vector<string> split(const string & s, const char sep) {
    stringstream ss(s);
    vector<string> tokens;
    string token;
    while(getline(ss, token, sep)) {
        tokens.push_back(token);
    }
    return tokens;
}

int main(int argc, char* argv[]) {
    ifstream infile(argv[1]);
    string line;
    while (getline(infile, line)) {
        if (line.size() > 0) {
            vector<string> strings = split(line, ';');
            cout << lcs(strings[0], strings[1]) << endl;
        }
    }
    infile.close();
    return 0;
}