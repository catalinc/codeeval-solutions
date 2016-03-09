import sys


# The TRANSLATION_TABLE was built as follow:
#
# def build_translation_table(encoded, decoded):
#     t = {}
#     for i in xrange(len(encoded)):
#         t[encoded[i]] = decoded[i]
#     return t
#
#
# _SAMPLE_ENCODED = 'rbc vjnmkf kd yxyqci na rbc zjkfoscdd ew rbc ujllmcp tc rbkso rbyr ejp ' \
#                   'mysljylc kd kxveddknmc re jsicpdrysi de kr kd eoya kw aej icfkici re zjkr'
# _SAMPLE_DECODED = 'the public is amazed by the quickness of the juggler we think that our ' \
#                   'language is impossible to understand so it is okay if you decided to quit'
#
# TRANSLATION_TABLE = build_translation_table(_SAMPLE_ENCODED, _SAMPLE_DECODED)
# TRANSLATION_TABLE['g'] = 'v'  # from problem text
# TRANSLATION_TABLE['h'] = 'x'  # remaining letters

TRANSLATION_TABLE = {' ': ' ',
                     'a': 'y',
                     'b': 'h',
                     'c': 'e',
                     'd': 's',
                     'e': 'o',
                     'f': 'c',
                     'g': 'v',
                     'h': 'x',
                     'i': 'd',
                     'j': 'u',
                     'k': 'i',
                     'l': 'g',
                     'm': 'l',
                     'n': 'b',
                     'o': 'k',
                     'p': 'r',
                     'q': 'z',
                     'r': 't',
                     's': 'n',
                     't': 'w',
                     'u': 'j',
                     'v': 'p',
                     'w': 'f',
                     'x': 'm',
                     'y': 'a',
                     'z': 'q'}


def decode(test_case):
    decoded = []
    for c in test_case:
        decoded.append(TRANSLATION_TABLE[c])
    return ''.join(decoded)


if __name__ == '__main__':

    with open(sys.argv[1], 'r') as test_cases:
        for test_case in test_cases:
            test_case = test_case.rstrip()
            if test_case:
                print(decode(test_case))
