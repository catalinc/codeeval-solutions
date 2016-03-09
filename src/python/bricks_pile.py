import sys
import ast


def solve(test_case):
    hole, bricks = parse_hole_and_bricks(test_case)
    result = []
    for brick in bricks:
        if can_pass_through(hole, brick):
            result.append(brick[0])
    if result:
        return ','.join(map(str, sorted(result)))
    return '-'


def parse_hole_and_bricks(s):
    l = s.split('|')
    hole = parse_hole(l[0])
    bricks = map(parse_brick, l[1].split(';'))
    return hole, bricks


def parse_hole(s):
    l = s.split(' ')
    v1 = ast.literal_eval(l[0])
    v2 = ast.literal_eval(l[1])
    return sorted([abs(v1[0]-v2[0]), abs(v1[1]-v2[1])])


def parse_brick(s):
    l = s[1:-1].split(' ')
    i = int(l[0])
    v1 = ast.literal_eval(l[1])
    v2 = ast.literal_eval(l[2])
    return i, sorted([abs(v1[0]-v2[0]), abs(v1[1]-v2[1]), abs(v1[2]-v2[2])])


def can_pass_through(hole, brick):
    return hole[0] >= brick[1][0] and hole[1] >= brick[1][1]


if __name__ == '__main__':

    with open(sys.argv[1], 'r') as test_cases:
        for test_case in test_cases:
            test_case = test_case.rstrip()
            if test_case:
                print(solve(test_case))
