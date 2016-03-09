import sys


def evaluate(test):
    number, pattern = test.split(' ')
    plus_op = pattern.find('+')
    if plus_op > 0:
        n1 = int(number[0:plus_op])
        n2 = int(number[plus_op:])
        return n1 + n2
    minus_op = pattern.find('-')
    if minus_op > 0:
        n1 = int(number[0:minus_op])
        n2 = int(number[minus_op:])
        return n1 - n2


if __name__ == '__main__':
    test_cases = open(sys.argv[1], 'r')
    for test in test_cases:
        test = test.rstrip()
        if test:
            print(evaluate(test))
    test_cases.close()