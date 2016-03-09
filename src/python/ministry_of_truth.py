import sys
import re


def apply_utterance(text, utterance):
    text = re.sub(' +', ' ', text)
    result = []
    i = 0
    j = 0
    while i < len(text):
        if j < len(utterance) and text[i] == utterance[j]:
            k = j
            while k < len(utterance):
                if text[i + k - j] != utterance[k]:
                    break
                k += 1
            if k == len(utterance) or utterance[k] == ' ' or utterance[k-1] == ' ':
                result.append(utterance[j:k])
                i += k - j - 1
                j = k
            else:
                if text[i] != ' ':
                    result.append('_')
                else:
                    result.append(' ')
        else:
            if text[i] == ' ':
                result.append(text[i])
            else:
                result.append('_')
        i += 1

    if j < len(utterance):
        return 'I cannot fix history'

    return ''.join(result)

if __name__ == '__main__':
    with open(sys.argv[1], 'r') as infile:
        for test_case in infile:
            text, utterance = test_case.rstrip('\n').split(';')
            print(apply_utterance(text, utterance))