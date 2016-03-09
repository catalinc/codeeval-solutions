import sys


def lettercase_percentage(s):
    lower, upper = 0, 0
    for c in s:
        if c.islower():
            lower += 1
        else:
            upper += 1
    return percent(lower, len(s)), percent(upper, len(s))


def percent(a, b):
    return (float(a) / b) * 100


if __name__ == '__main__':

    with open(sys.argv[1], 'r') as test_cases:
        for test_case in test_cases:
            test_case = test_case.rstrip()
            if test_case:
                print('lowercase: %.2f uppercase: %.2f' % lettercase_percentage(test_case))
