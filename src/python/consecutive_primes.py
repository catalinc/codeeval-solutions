import sys


_PRIMES_UNDER_40 = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37}


def is_prime_under_40(n):
    return n in _PRIMES_UNDER_40


def count_ways(n):
    if n % 2 != 0:
        return 0
    return count_ways_rec(range(1, n + 1), n, 1)


def count_ways_rec(beads, n, pos):
    if pos == n:
        if is_prime_under_40(beads[0] + beads[-1]):
            return 1
        return 0
    ways = 0
    previous = beads[pos - 1]
    if is_prime_under_40(previous + beads[pos]):
        ways += count_ways_rec(beads, n, pos + 1)
    for i in xrange(pos + 1, n):
        if is_prime_under_40(previous + beads[i]):
            beads[pos], beads[i] = beads[i], beads[pos]
            ways += count_ways_rec(beads, n, pos + 1)
            beads[pos], beads[i] = beads[i], beads[pos]
    return ways

# for i in xrange(2, 19):
#     print(count_ways(i))

_PRE_COMPUTED = {
    2: 1,
    4: 2,
    6: 2,
    8: 4,
    10: 96,
    12: 1024,
    14: 2880,
    16: 81024,
    18: 770144
}

if __name__ == '__main__':
    with open(sys.argv[1], 'r') as infile:
        for line in infile:
            n = int(line)
            print(_PRE_COMPUTED.get(n, 0))
