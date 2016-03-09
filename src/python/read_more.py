import sys

# Solution to https://www.codeeval.com/open_challenges/167/


def trim_to_read_more(s, m, n):
    if len(s) <= m:
        return s
    t = s[:n]
    if s[n] != ' ':
        i = t.rfind(' ')
        if i > 0:
            t = t[:i]
    return t + '... <Read More>'


if __name__ == '__main__':

    with open(sys.argv[1], 'r') as test_cases:
        for test_case in test_cases:
            test_case = test_case.rstrip()
            if test_case:
                print(trim_to_read_more(test_case, 55, 40))
