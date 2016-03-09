import sys


def translate_hidden(s):
    ret = []
    for c in s:
        if c.isdigit():
            ret.append(c)
        else:
            d = ord(c) - ord('a')
            if 0 <= d <= 9:
                ret.append(str(d))
    if ret:
        return ''.join(ret)
    return 'NONE'


if __name__ == '__main__':

    with open(sys.argv[1], 'r') as test_cases:
        for test_case in test_cases:
            test_case = test_case.rstrip()
            if test_case:
                print(translate_hidden(test_case))
