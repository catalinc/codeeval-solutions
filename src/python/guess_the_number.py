import sys
import math

# Solution to https://www.codeeval.com/open_challenges/170/


def find_number(max_num, guesses):
    hi = max_num
    lo = 0
    for g in guesses:
        n = math.ceil((lo + hi) / 2.0)
        if g == 'Yay!':
            return int(n)
        elif g == 'Lower':
            hi = n - 1
        elif g == 'Higher':
            lo = n + 1

if __name__ == '__main__':
    with open(sys.argv[1], 'r') as input_file:
        for line in input_file:
            line = line.rstrip()
            tokens = line.split(' ')
            print(find_number(int(tokens[0]), tokens[1:]))