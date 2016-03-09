import sys

# Solution to https://www.codeeval.com/open_challenges/236/


def gray_to_bin(g):
    b = g
    m = g >> 1
    while m:
        b = b ^ m
        m >>= 1
    return b

if __name__ == '__main__':
    with open(sys.argv[1], 'r') as infile:
        for line in infile:
            grays = [int(x, 2) for x in line.split(' | ')]
            bins = [str(gray_to_bin(x)) for x in grays]
            print(' | '.join(bins))
