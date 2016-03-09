#include <iostream>
#include <fstream>

using namespace std;

int countOnes(int i) {
    i = i - ((i >> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >> 2) & 0x33333333);
    return (((i + (i >> 4)) & 0x0F0F0F0F) * 0x01010101) >> 24;
}

int main(int argc, char* argv[]) {
    ifstream infile(argv[1]);
    int i;
    while (infile >> i) {
        cout << countOnes(i) << endl;
    }
    infile.close();
    return 0;
}

