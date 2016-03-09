#include <iostream>
#include <fstream>
#include <sstream>
#include <vector>

using namespace std;

typedef vector< vector<string> > Matrix;

void parseMatrix(Matrix & m, string s) {
    int rows, cols;
    char c;
    stringstream ss(s);
    ss >> rows;
    ss >> c;
    ss >> cols;
    ss >> c;
    m.clear();
    for (int i = 0; i < rows; i++) {
        vector<string> row;
        for (int j = 0; j < cols; j++) {
            string s;
            ss >> s;
            row.push_back(s);
        }
        m.push_back(row);
    }
}

Matrix rotateCCW(const Matrix & m) {
    int rows = m.size();
    int cols = m[0].size();
    Matrix rotated;
    for (int i = 0; i < cols; i++) {
        vector<string> row(rows, "");
        rotated.push_back(row);
    }
    for (int i = 0; i < rows; i++) {
        for (int j = cols - 1; j >= 0; j--) {
            rotated[cols - 1 - j][i] = m[i][j];
        }
    }
    return rotated;
}

void printFirstRow(const Matrix & m) {
    vector<string> row = m[0];
    for (unsigned int i = 0; i < row.size(); i++) {
        cout << row[i];
        if (i != row.size() - 1) {
            cout << ' ';
        }
    }
}

void removeFirstRow(Matrix & m) {
    m.erase(m.begin());
}

int main(int argc, char* argv[]) {
    ifstream infile(argv[1]);
    string line;
    Matrix m;
    while (getline(infile, line)) {
        parseMatrix(m, line);
        while (m.size() > 0) {
            printFirstRow(m);
            removeFirstRow(m);
            if (m.size() > 0) {
                cout << ' ';
                m = rotateCCW(m);
            }
        }
        cout << endl;
    }
    infile.close();
    return 0;
}