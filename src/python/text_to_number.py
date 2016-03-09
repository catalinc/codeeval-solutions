import sys

word_to_number = {
    'zero': 0,
    'one': 1,
    'two': 2,
    'three': 3,
    'four': 4,
    'five': 5,
    'six': 6,
    'seven': 7,
    'eight': 8,
    'nine': 9,
    'ten': 10,
    'eleven': 11,
    'twelve': 12,
    'thirteen': 13,
    'fourteen': 14,
    'fifteen': 15,
    'sixteen': 16,
    'seventeen': 17,
    'eighteen': 18,
    'nineteen': 19,
    'twenty': 20,
    'thirty': 30,
    'forty': 40,
    'fifty': 50,
    'sixty': 60,
    'seventy': 70,
    'eighty': 80,
    'ninety': 90,
    'hundred': 100,
    'thousand': 1000,
    'million': 1000000}


def text_to_number(s):
    sign = 1
    stack = []
    words = s.split(' ')
    for w in words:
        if w == 'negative':
            sign = -1
        else:
            n = word_to_number[w]
            if len(stack) == 0:
                stack.append(n)
            else:
                if n >= 100:
                    m = 0
                    while stack and stack[-1] < n:
                        m += stack.pop()
                    stack.append(m * n)
                else:
                    stack.append(n)
    return sign * sum(stack)


if __name__ == '__main__':

    test_cases = open(sys.argv[1], 'r')

    for test in test_cases:
        test = test.rstrip()
        if test:
            print(text_to_number(test))

    test_cases.close()
