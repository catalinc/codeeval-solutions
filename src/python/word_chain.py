import sys


def longest_word_chain(s):
    words = s.split(',')
    max_len = 0
    for w in words:
        chain_len = word_chain_len(words, [w])
        if chain_len > max_len:
            max_len = chain_len
            if max_len == len(words):
                break
    return max_len


def word_chain_len(words, chain):
    next_words = next_words_in_chain(words, chain)
    if not next_words:
        return len(chain)
    else:
        max_len = 0
        for w in next_words:
            chain_len = word_chain_len(words, chain + [w])
            if chain_len > max_len:
                max_len = chain_len
        return max_len


def next_words_in_chain(words, chain):
    ret = []
    ch = chain[-1][-1]
    for w in words:
        if w.startswith(ch) and w not in chain:
            ret.append(w)
    return ret

if __name__ == '__main__':

    with open(sys.argv[1], 'r') as test_cases:
        for test_case in test_cases:
            test_case = test_case.rstrip()
            if test_case:
                longest = longest_word_chain(test_case)
                if longest == 1:
                    longest = None
                print(longest)
