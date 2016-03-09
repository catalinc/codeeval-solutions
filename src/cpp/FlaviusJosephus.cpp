#include <iostream>
#include <fstream>
#include <sstream>
#include <vector>

using namespace std;

vector<int> executionOrder(int n, int m) {
    vector<int> executed;
    vector<bool> dead(n);
    int next = m - 1, skipped;
    while (true) {
        executed.push_back(next);
        dead[next] = true;
        if (executed.size() == n) {
            break; // all dead
        } else {
            skipped = 0;
            while (skipped < m) {
                next = (next + 1) % n;
                if (!dead[next]) skipped++;
            }
        }
    }
    return executed;
}

string join(const vector<int> & v, char sep) {
    stringstream ss;
    for (unsigned int i = 0; i < v.size(); i++) {
        ss << v[i];
        if (i != v.size() - 1) {
            ss << sep;
        }
    }
    return ss.str();
}

int main(int argc, char* argv[]) {
    ifstream infile(argv[1]);
    int n, m;
    char c;
    vector<int> executed;
    while (infile >> n >> c >> m) {
        executed = executionOrder(n, m);
        cout << join(executed, ' ') << endl;
    }
    infile.close();
    return 0;
}

