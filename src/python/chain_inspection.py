import sys


def is_good_chain(s):
    chain = dict([p.split('-') for p in s.split(';')])
    visited = set()
    state = 'BEGIN'
    while True:
        visited.add(state)
        state = chain[state]
        if state == 'END' or state in visited:
            break
    return state == 'END' and len(visited) == len(chain)


if __name__ == '__main__':

    with open(sys.argv[1], 'r') as test_cases:
        for test_case in test_cases:
            test_case = test_case.rstrip()
            if test_case:
                if is_good_chain(test_case):
                    print('GOOD')
                else:
                    print('BAD')
