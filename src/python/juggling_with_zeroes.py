import sys


def parse_zero_based_number(s):
    binary = []
    splits = s.split(' ')
    for i in xrange(1, len(splits), 2):
        digit = '0'
        if splits[i - 1] == '00':
            digit = '1'
        binary.append(digit * len(splits[i]))
    return int(''.join(binary), 2)


if __name__ == '__main__':

    with open(sys.argv[1], 'r') as test_cases:
        for test_case in test_cases:
            test_case = test_case.rstrip()
            if test_case:
                print(parse_zero_based_number(test_case))
