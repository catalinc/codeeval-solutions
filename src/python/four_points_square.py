import sys
import ast


def distance(p1, p2):
    return (p1[0] - p2[0]) ** 2 + (p1[1] - p2[1]) ** 2


def is_square(test):
    a, b, c, d = ast.literal_eval(test)
    distances = sorted([distance(p[0], p[1]) for p in [(a, b,), (a, c), (a, d), (b, c), (b, d), (c, d)]])
    return distances[0] == distances[3] and distances[4] == distances[5]


if __name__ == '__main__':
    test_cases = open(sys.argv[1], 'r')
    for test in test_cases:
        if is_square(test):
            print('true')
        else:
            print('false')
    test_cases.close()
