#include <iostream>
#include <set>
#include <map>
#include <vector>
#include <cmath>
#include <utility>

using namespace std;

static map<int, int> sumOfDigitsCache;
static map<int, int>::iterator itSumOfDigitsCache;

inline int sumOfDigits(int n) {
    int nAbs = abs(n);
    itSumOfDigitsCache = sumOfDigitsCache.find(nAbs);
    if (itSumOfDigitsCache != sumOfDigitsCache.end()) {
        return itSumOfDigitsCache->second;
    }
    int sumOfDigits = 0;
    int number = nAbs;
    while (number) {
        sumOfDigits += number % 10;
        number /= 10;
    }
    sumOfDigitsCache[nAbs] = sumOfDigits;
    return sumOfDigits;
}

inline bool canWalkOn(const pair<int, int>& xy) {
    return (sumOfDigits(xy.first) + sumOfDigits(xy.second)) <= 19;
}

void walkableNeighbours(const pair<int, int>& xy, const set< pair<int, int> >& exploredSet, vector< pair<int, int> >& neighbours) {
    for (int dx = -1; dx < 2; dx += 2) {
        pair<int, int> p = make_pair(xy.first + dx, xy.second);
        if (!(exploredSet.find(p) != exploredSet.end()) && canWalkOn(p)) {
            neighbours.push_back(p);
        }
    }
    for (int dy = -1; dy < 2; dy += 2) {
        pair<int, int> p = make_pair(xy.first, xy.second + dy);
        if (!(exploredSet.find(p) != exploredSet.end()) && canWalkOn(p)) {
            neighbours.push_back(p);
        }
    }
}

int solve() {
    set< pair<int, int> > exploredSet;
    vector< pair<int, int> > toExplore;
    vector< pair<int, int> > neighbours;
    vector< pair<int, int> >::iterator itNeighbours;

    toExplore.push_back(make_pair(0, 0));
    while (!toExplore.empty()) {
        pair<int, int> p = toExplore.back();
        toExplore.pop_back();
        exploredSet.insert(p);
        neighbours.clear();
        walkableNeighbours(p, exploredSet, neighbours);
        for (itNeighbours = neighbours.begin(); itNeighbours != neighbours.end(); ++itNeighbours) {
            toExplore.push_back(*itNeighbours);
        }
    }

    return exploredSet.size();
}

int main(int argc, char* argv[]) {
    int result = solve();
    cout << result << endl;
}