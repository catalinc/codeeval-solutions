import sys


def reconstruct_sentence(s):
    shuffled, hints = s.split(';')
    words = shuffled.split(' ')
    hints = [int(x) for x in hints.split(' ')]
    sentence = [''] * len(words)
    i = 0
    while i < len(hints):
        w = words[i]
        p = hints[i]
        sentence[p-1] = w
        i += 1
    i = 0
    while i < len(sentence):
        if not sentence[i]:
            sentence[i] = words[-1]
            break
        i += 1
    return ' '.join(sentence)


if __name__ == '__main__':
    with open(sys.argv[1], 'r') as infile:
        for line in infile:
            line = line.rstrip()
            print(reconstruct_sentence(line))
