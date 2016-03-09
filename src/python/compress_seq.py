import sys


def compress_seq(s):
    i = 0
    ret = []
    ns = s.split(' ')
    while i < len(ns):
        n = ns[i]
        i += 1
        c = 1
        while i < len(ns) and n == ns[i]:
            i += 1
            c += 1
        ret.append(str(c))
        ret.append(n)
    return ' '.join(ret)


if __name__ == '__main__':

    with open(sys.argv[1], 'r') as test_cases:
        for test_case in test_cases:
            test_case = test_case.rstrip()
            if test_case:
                print(compress_seq(test_case))
