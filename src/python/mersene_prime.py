import sys


_MERSENNE_PRIMES = [3, 7, 31, 127, 2047, 8191]

if __name__ == '__main__':
    with open(sys.argv[1], 'r') as infile:
        for line in infile:
            n = int(line)
            r = []
            for x in _MERSENNE_PRIMES:
                if x >= n:
                    break
                r.append(str(x))
            print(', '.join(r))
