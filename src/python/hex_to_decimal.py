SYMBOLS = '0123456789abcdef'


def hex2dec(s):
    v = 0
    for i, d in enumerate(reversed(s.lower())):
        v += (16 ** i) * SYMBOLS.index(d)
    return v

if __name__ == '__main__':
    import sys
    if len(sys.argv) == 2:
        with open(sys.argv[1]) as infile:
            for line in infile:
                line = line.strip()
                print(hex2dec(line))
    else:
        sys.exit(1)
