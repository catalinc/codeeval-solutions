import sys
import ast


def cmp_by_price_per_unit(x, y):
    r1 = float(x[2]) / x[1]
    r2 = float(y[2]) / y[1]
    if r1 > r2:
        return 1
    return -1


def knapsack(s):
    splits = s.split(' : ')
    max_weight = int(splits[0])
    items = map(ast.literal_eval, splits[1].replace('$', '').split(' '))
    items.sort(cmp=cmp_by_price_per_unit, reverse=True)
    items_to_pack = []
    for item in items:
        item_index = item[0]
        item_weight = item[1]
        if item_weight <= max_weight:
            max_weight -= item_weight
            items_to_pack.append(item_index)
        if max_weight == 0:
            break
    return items_to_pack


if __name__ == '__main__':

    with open(sys.argv[1], 'r') as infile:

        for line in infile:
            line = line.rstrip()
            if line:
                sol = knapsack(line)
                if sol:
                    print(','.join(map(str, sol)))
                else:
                    print('-')
