import sys


def locks(n, m):
    doors = [True] * n
    for _ in xrange(m - 1):
        for i in xrange(1, len(doors), 2):
            doors[i] = False
        for j in xrange(2, len(doors), 3):
            doors[j] = not doors[j]
    doors[-1] = not doors[-1]
    unlocked = 0
    for d in doors:
        if d:
            unlocked += 1
    return unlocked


if __name__ == '__main__':
    with open(sys.argv[1], 'r') as infile:
        for line in infile:
            n, m = map(int, line.split(' '))
            print(locks(n, m))