import sys

def multiply_lists(s):
    l1, l2 = s.split('|')
    l1 = [int(x) for x in l1.split()]
    l2 = [int(x) for x in l2.split()]
    lret = []
    for i in xrange(len(l1)):
        lret.append(str(l1[i] * l2[i]))
    return ' '.join(lret)

if __name__ == '__main__':

    test_cases = open(sys.argv[1], 'r')

    for test in test_cases:
        test = test.rstrip()
        if test:
            print(multiply_lists(test))

    test_cases.close()