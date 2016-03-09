import sys


def bubble_sort_step(a):
    is_sorted = True
    for i in xrange(len(a) - 1):
        if a[i] > a[i+1]:
            t = a[i]
            a[i] = a[i+1]
            a[i+1] = t
            is_sorted = False
    return is_sorted


if __name__ == '__main__':
    with open(sys.argv[1], 'r') as infile:
        for line in infile:
            splits = line.split(' | ')
            array, steps = map(int, splits[0].split(' ')), int(splits[1])
            for _ in xrange(steps):
                if bubble_sort_step(array):
                    break
            print(' '.join(map(str, array)))