import sys
import json


def count_menu_ids(test):
    o = json.loads(test)
    return sum([item['id'] for item in o['menu']['items'] if item and 'label' in item])


if __name__ == '__main__':
    test_cases = open(sys.argv[1], 'r')
    for test in test_cases:
        test = test.strip()
        if test:
            print(count_menu_ids(test))
    test_cases.close()

