import sys
import itertools


def choose(n, s):
    result = set()
    for c in itertools.product(s, repeat=n):
        result.add(''.join(c))
    result = list(result)
    result.sort()
    return result


if __name__ == '__main__':
    with open(sys.argv[1]) as infile:
        for line in infile:
            n, s = line.strip().split(',')
            result = choose(int(n), s)
            print(','.join(result))
