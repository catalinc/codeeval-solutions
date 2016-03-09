import sys
from datetime import datetime

# Solution to https://www.codeeval.com/open_challenges/166/


def parse_datetime(s):
    return datetime.strptime(s, '%H:%M:%S')


def format_timedelta(t):
    s = str(t)
    return ':'.join(map(zero_pad, s.split(':')))


def zero_pad(s):
    if len(s) == 2:
        return s
    return '0' + s

if __name__ == '__main__':

    with open(sys.argv[1], 'r') as test_cases:
        for test_case in test_cases:
            test_case = test_case.rstrip()
            if test_case:
                d1, d2 = map(parse_datetime, test_case.split(' '))
                t = d1 - d2 if d1 > d2 else d2 - d1
                print(format_timedelta(t))
