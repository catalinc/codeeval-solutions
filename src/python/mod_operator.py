def mod(a, b):
    return a - (b * int(a / b))

if __name__ == '__main__':
    import sys
    if len(sys.argv) != 2:
        sys.exit(1)
    with open(sys.argv[1]) as infile:
        for line in infile:
            a, b = map(int, line.split(','))
            print(mod(a, b))
