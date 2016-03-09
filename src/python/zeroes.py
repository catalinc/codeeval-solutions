import sys

# Solution to https://www.codeeval.com/open_challenges/217/


def zeroes(n):
    c = 0
    while n:
        if n & 1 == 0:
            c += 1
        n >>= 1
    return c

if __name__ == '__main__':
    with open(sys.argv[1], 'r') as infile:
        for line in infile:
            n, m = map(int, line.split(' '))
            c = 0
            for i in xrange(1, m+1):
                if zeroes(i) == n:
                    c += 1
            print(c)
