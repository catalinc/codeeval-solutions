import sys
import math


if __name__ == '__main__':
    with open(sys.argv[1], 'r') as infile:
        for line in infile:
            m = line.rstrip().split(' ')
            n = int(math.sqrt(len(m)))
            l = []
            i = len(m) - n
            while i < len(m):
                j = i
                while j >= 0:
                    l.append(m[j])
                    j -= n
                i += 1
            print(' '.join(l))