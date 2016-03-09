#include <iostream>
#include <string>
#include <sstream>
#include <fstream>
#include <map>

using namespace std;

int main(int argc, const char* argv[]) {
    map<string, int> word_to_number;
    word_to_number[string("zero")] = 0;
    word_to_number[string("one")] = 1;
    word_to_number[string("two")] = 2;
    word_to_number[string("three")] = 3;
    word_to_number[string("four")] = 4;
    word_to_number[string("five")] = 5;
    word_to_number[string("six")] = 6;
    word_to_number[string("seven")] = 7;
    word_to_number[string("eight")] = 8;
    word_to_number[string("nine")] = 9;

    map<string, int>::iterator it;

    ifstream infile(argv[1]);
    string line, token;
    while (getline(infile, line)) {
        istringstream iss(line);
        stringstream ss;
        while (getline(iss, token, ';')) {
            it = word_to_number.find(token);
            ss << it->second;
        }
        cout << ss.str() << endl;
    }

    return 0;
}