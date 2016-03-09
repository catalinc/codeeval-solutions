import sys

def swap_elements(s):
    array, swaps = s.split(':')
    array = array.split()
    swaps = [tuple([int(index) for index in pair.split('-')]) for pair in swaps.split(',')]
    for i, j in swaps:
        array[i], array[j] = array[j], array[i]
    return ' '.join(array)

if __name__ == '__main__':

    test_cases = open(sys.argv[1], 'r')

    for test in test_cases:
        test = test.rstrip()
        if test:
            print(swap_elements(test))

    test_cases.close()