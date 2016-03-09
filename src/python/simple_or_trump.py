import sys


def eval_hand(card1, card2, trump):
    if card1[-1] == trump and card2[-1] != trump:
        return card1
    if card2[-1] == trump and card1[-1] != trump:
        return card2
    v1 = card_value(card1)
    v2 = card_value(card2)
    if v1 > v2:
        return card1
    if v2 > v1:
        return card2
    return '%s %s' % (card1, card2)


def card_value(c):
    card_no_suite = c[:-1]
    v = {'J': 11, 'Q': 12, 'K': 13, 'A': 14}.get(card_no_suite, -1)
    if v > 0:
        return v
    return int(card_no_suite)


if __name__ == '__main__':
    with open(sys.argv[1], 'r') as infile:
        for line in infile:
            card1, card2, _, trump = line.split(' ')
            print(eval_hand(card1, card2, trump[0]))
