import sys
import math


def n(i):
    if i == 0:
        return 0
    if i == 1:
        return 1
    k = int(2**(math.floor(math.log(i, 2))))
    return s(n(i-k))


def s(n):
    if n == 0:
        return 1
    if n == 1:
        return 2
    if n == 2:
        return 0


if __name__ == '__main__':
    test_cases = open(sys.argv[1], 'r')
    for test in test_cases:
        test = test.rstrip()
        if test:
            print(n(int(test)))
    test_cases.close()