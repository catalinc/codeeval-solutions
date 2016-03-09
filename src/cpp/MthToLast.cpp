#include <iostream>
#include <string>
#include <sstream>
#include <fstream>
#include <vector>

using namespace std;

int main(int argc, char* argv[]) {
	ifstream infile(argv[1]);
	string line, token;
	vector<string> tokens;
	int index;
	while(getline(infile, line)) {
		tokens.clear();
		istringstream iss(line);
		while(getline(iss, token, ' ')) {
			tokens.push_back(token);
		}
		istringstream last(tokens[tokens.size() - 1]);
		last >> index;
		index = tokens.size() - 1 - index;
		if (index >= 0 && index < tokens.size() - 1) {
			cout << tokens[index] << endl;
		}
	}
	infile.close();
	return 0;
}
