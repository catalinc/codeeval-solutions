import sys


def extra_bats(min_dist, bats_pos):
    count = 0
    for i in xrange(1, len(bats_pos)):
        gap = bats_pos[i] - bats_pos[i-1]
        if i == 1 or i == len(bats_pos) - 1:
            gap -= 6
        else:
            gap -= min_dist
        if gap > 0:
            count += gap / min_dist
    return count


def parse_input(s):
    ints = [int(x) for x in s.split(' ')]
    wire_len = ints[0]
    min_dist = ints[1]
    bats_pos = [0] + sorted(ints[3:]) + [wire_len]
    return min_dist, bats_pos


if __name__ == '__main__':

    with open(sys.argv[1], 'r') as infile:
        for line in infile:
            print(extra_bats(*parse_input(line)))