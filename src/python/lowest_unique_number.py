import sys

def find_winner(test):
    numbers = map(int, test.split(' '))
    frequencies = {}
    for n in numbers:
        if not n in frequencies:
            frequencies[n] = 0
        frequencies[n] += 1
    winner, lowest = 0, None
    for i, n in enumerate(numbers):
        if frequencies[n] == 1:
            if lowest is None or n < lowest:
                lowest = n
                winner = i + 1
    return winner


if __name__ == '__main__':
    test_cases = open(sys.argv[1], 'r')
    for test in test_cases:
        print(find_winner(test))
    test_cases.close()
