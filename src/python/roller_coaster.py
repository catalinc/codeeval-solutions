import sys


def roller_coaster(s):
    to_upper = True
    result = []
    for c in s:
        if c.isalpha():
            if to_upper:
                result.append(c.upper())
            else:
                result.append(c.lower())
            to_upper = not to_upper
        else:
            result.append(c)
    return ''.join(result)


if __name__ == '__main__':
    with open(sys.argv[1], 'r') as infile:
        for line in infile:
            line = line.rstrip()
            print(roller_coaster(line))