import sys
import random


def gen_test_data(filename, count):
    with open(filename, 'a') as file:
        for _ in xrange(count):
            file.write("%d\n" % random.randint(0, sys.maxint))

if __name__ == '__main__':
    if len(sys.argv) != 3:
        print("usage: %s filename count")
    filename = sys.argv[1]
    count = int(sys.argv[2])
    gen_test_data(filename, count)
