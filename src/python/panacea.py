import sys

# Solution to https://www.codeeval.com/open_challenges/237


def parse_int(s, base):
    p = 1
    n = 0
    for i in xrange(len(s) - 1, -1, -1):
        n += p * _int(s[i])
        p *= base
    return n


def _int(c):
    if '0' <= c <= '9':
        return ord(c) - ord('0')
    if 'a' <= c <= 'f':
        return ord(c) - ord('a') + 10


if __name__ == '__main__':
    with open(sys.argv[1], 'r') as infile:
        for line in infile:
            line = line.rstrip('\n')
            virus, antivirus = line.split(' | ')
            virus = [parse_int(s, 16) for s in virus.split(' ')]
            antivirus = [parse_int(s, 2) for s in antivirus.split(' ')]
            if sum(virus) <= sum(antivirus):
                print(True)
            else:
                print(False)
