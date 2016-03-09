import sys


def decrypt(n, c, l):
    rs = find_repeating(n, l)
    for s in rs:
        d = s[-1] - ord(c)
        if d > 0:
            return translate(l, d)
    return l


def find_repeating(n, l):
    rs = []
    for i in xrange(len(l)):
        s = l[i:i + n]
        r = find_all(s, l)
        if len(r) == 2:
            rs.append(s)
    return rs


def find_all(l1, l2):
    r = []
    for i in xrange(len(l2) - len(l1)):
        found = True
        for j in xrange(len(l1)):
            if l1[j] != l2[i + j]:
                found = False
                break
        if found:
            r.append(i)
    return r


def translate(l, d):
    r = []
    for n in l:
        r.append(chr(n - d))
    return ''.join(r)


if __name__ == '__main__':
    with open(sys.argv[1], 'r') as infile:
        for line in infile:
            splits = line.split(' | ')
            n = int(splits[0])
            c = splits[1]
            l = map(int, splits[2].split(' '))
            print(decrypt(n, c, l))