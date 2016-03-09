import sys
import re


def can_transform(test_case):
    binary, ab_seq = test_case.split(' ')
    pattern = []
    zeroes_count = 0
    ones_count = 0

    for digit in binary:
        if digit == '0':
            if ones_count > 0:
                pattern.append("(?:A{%d,}|B{%d,})" % (ones_count, ones_count))
                ones_count = 0
            zeroes_count += 1
        else:
            if zeroes_count > 0:
                pattern.append("(?:A{%d,})" % zeroes_count)
                zeroes_count = 0
            ones_count += 1

    if zeroes_count > 0:
        pattern.append("(?:A{%d,})" % zeroes_count)
    if ones_count > 0:
        pattern.append("(?:A{%d,}|B{%d,})" % (ones_count, ones_count))

    return re.match(''.join(pattern), ab_seq)


if __name__ == '__main__':
    test_cases = open(sys.argv[1], 'r')
    for test in test_cases:
        test = test.rstrip()
        if test:
            if can_transform(test):
                print('Yes')
            else:
                print('No')
    test_cases.close()