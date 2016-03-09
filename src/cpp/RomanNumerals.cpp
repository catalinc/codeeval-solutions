#include <iostream>
#include <string>
#include <sstream>
#include <fstream>
#include <map>
#include <utility>
#include <cmath>

using namespace std;

struct ArabicToRoman {

    static map<int, string> createMap() {
        map<int, string> m;
        m[0] = "";
        m[1] = "I";
        m[2] = "II";
        m[3] = "III";
        m[4] = "IV";
        m[5] = "V";
        m[6] = "VI";
        m[7] = "VII";
        m[8] = "VIII";
        m[9] = "IX";
        m[10] = "X";
        m[20] = "XX";
        m[30] = "XXX";
        m[40] = "XL";
        m[50] = "L";
        m[60] = "LX";
        m[70] = "LXX";
        m[80] = "LXXX";
        m[90] = "XC";
        m[100] = "C";
        m[200] = "CC";
        m[300] = "CCC";
        m[400] = "CD";
        m[500] = "D";
        m[600] = "DC";
        m[700] = "DCC";
        m[800] = "DCCC";
        m[900] = "CM";
        m[1000] = "M";
        m[2000] = "MM";
        m[3000] = "MMM";
        return m;
    }

    static const map<int, string> MAPPING;
};

const map<int, string> ArabicToRoman::MAPPING = ArabicToRoman::createMap();

string arabicToRoman(int n) {
    string s;
    int k = 10, u;
    map<int, string>::const_iterator it;
    while (n) {
        u = n % k;
        n -= u;
        k *= 10;
        it = ArabicToRoman::MAPPING.find(u);
        if(it != ArabicToRoman::MAPPING.end()) {
            s.insert(0, it->second);
        }
    }
    return s;
}

int main(int argc, char* argv[]) {
    ifstream infile(argv[1]);
    int n;
    while (infile >> n) {
        cout << arabicToRoman(n) << endl;
    }
    infile.close();
    return 0;
}

