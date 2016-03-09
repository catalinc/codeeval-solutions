import sys
from collections import Counter


def major_element(test):
    numbers = test.split(',')
    counter = Counter(numbers)
    most_common = counter.most_common()[0]
    if most_common[1] > len(numbers)/2:
        return most_common[0]
    return 'None'


if __name__ == '__main__':
    test_cases = open(sys.argv[1], 'r')
    for test in test_cases:
        test = test.rstrip()
        if test:
            print(major_element(test))
    test_cases.close()