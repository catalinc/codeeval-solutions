import sys
import ast


def parse_input(s):
    streets, avenues = map(ast.literal_eval, s.split(' '))
    return streets, avenues


def fly_over(streets, avenues):
    count = 0
    diagonal = (0, 0, streets[-1], avenues[-1])
    for i in xrange(len(streets) - 1):
        for j in xrange(len(avenues) - 1):
            block = (streets[i], avenues[j], streets[i + 1], avenues[j + 1])
            if intersects(diagonal, block):
                count += 1
    return count


def intersects(line, rect):
    # Liang-Barsky algorithm for clipping
    dx = line[2] - line[0]
    dy = line[3] - line[1]
    p = [-dx, dx, -dy, dy]
    q = [line[0] - rect[0], rect[2] - line[0], line[1] - rect[1], rect[3] - line[1]]
    u1 = float('-inf')
    u2 = float('inf')
    for k in xrange(len(p)):
        if p[k] == 0:
            if q[k] < 0:
                return False
        else:
            t = float(q[k]) / p[k]
            if p[k] < 0 and u1 < t:
                u1 = t
            elif p[k] > 0 and u2 > t:
                u2 = t

    if u1 >= u2 or u1 > 1 or u1 < 0:
        return False

    return True


if __name__ == '__main__':
    with open(sys.argv[1], 'r') as infile:
        for test_case in infile:
            print(fly_over(*parse_input(test_case)))