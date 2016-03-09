import sys
import re


def compute_distances(s):
    ds = sorted([int(d) for d in re.split('\W+', s) if d and d.isdigit()])
    r = [ds[0]]
    for i in xrange(1, len(ds)):
        r.append(ds[i]-ds[i-1])
    return r


def join(seq, sep=','):
    return sep.join([str(x) for x in seq])


if __name__ == '__main__':

    with open(sys.argv[1], 'r') as test_cases:
        for test_case in test_cases:
            test_case = test_case.rstrip()
            if test_case:
                print(join(compute_distances(test_case)))
