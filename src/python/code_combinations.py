import sys


# Solution to https://www.codeeval.com/open_challenges/238/


def parse_matrix(s):
    return [[c for c in row] for row in s.split(' | ')]


_CODE = set('code')


def count_code(m):
    count = 0
    rows = len(m)
    cols = len(m[0])
    for r in xrange(0, rows - 2 + 1):
        for c in xrange(0, cols - 2 + 1):
            v = [m[r][c],
                 m[r + 1][c],
                 m[r][c + 1],
                 m[r + 1][c + 1]]
            if set(v) == _CODE:
                count += 1
    return count


if __name__ == '__main__':
    with open(sys.argv[1], 'r') as infile:
        for line in infile:
            line = line.rstrip('\n')
            matrix = parse_matrix(line)
            print(count_code(matrix))
