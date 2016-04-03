import sys


def can_transform(binary, ab_seq):
    bin_len = len(binary)
    ab_len = len(ab_seq)

    state = [[False for _ in xrange(ab_len + 1)] for _ in xrange(bin_len + 1)]
    for i in xrange(bin_len + 1):
        state[i][0] = True
    for i in xrange(ab_len + 1):
        state[0][i] = True

    for i in xrange(1, bin_len + 1):
        for j in xrange(1, ab_len + 1):
            p = binary[i - 1]
            t = ab_seq[j - 1]
            if state[i - 1][j - 1]:
                state[i][j] = (p == '0' and t == 'A') or p == '1'
                if j >= 2:
                    state[i][j] = state[i][j] and (t == ab_seq[j - 2])
            else:
                state[i][j] = state[i - 1][j] and (t == ab_seq[j - 2])

    return state[bin_len][ab_len]


if __name__ == '__main__':
    with open(sys.argv[1], 'r') as test_cases:
        for test in test_cases:
            test = test.rstrip()
            if test:
                binary, ab_seq = test.split(' ')
                if can_transform(binary, ab_seq):
                    print('Yes')
                else:
                    print('No')
