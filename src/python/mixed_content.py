import sys


def words_and_digits(input):
    words = []
    ints = []
    for s in input.split(','):
        if is_int(s):
            ints.append(s)
        else:
            words.append(s)
    result = []
    if words:
        result.append(','.join(words))
    if ints:
        result.append(','.join(ints))
    return '|'.join(result)


def is_int(s):
    try:
        int(s)
    except ValueError:
        return False
    return True


if __name__ == '__main__':
    if len(sys.argv) >= 2:
        with open(sys.argv[1], 'r') as test_cases:
            for test_case in test_cases:
                test_case = test_case.rstrip()
                print(words_and_digits(test_case))