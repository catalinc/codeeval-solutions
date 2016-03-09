import sys

def longest_word(s):
    words = s.split(' ')
    return first_max(words, key=lambda w: len(w))

def first_max(iterable, key=None):
    item, max_value = None, None
    for x in iterable:
        if key:
            value = key(x)
        else:
            value = x
        if item is None or max_value < value:
            item = x
            max_value = value
    return item

if __name__ == '__main__':

    test_cases = open(sys.argv[1], 'r')

    for test in test_cases:
        if test:
            test = test.rstrip()
            print(longest_word(test))

    test_cases.close()