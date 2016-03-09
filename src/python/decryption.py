import string

class Cipher(object):
    def __init__(self, keyed_alphabet):
        self.cipher_table = dict(zip(keyed_alphabet, string.ascii_uppercase))

    def decrypt(self, message):
        result = []
        for word in message.split():
            result.append(self.decrypt_word(word))
        return ' '.join(result)

    def decrypt_word(self, word):
        result = []
        for i in xrange(0, len(word), 2):
            index = int(word[i:i + 2])
            char = string.ascii_uppercase[index]
            result.append(self.cipher_table[char])
        return ''.join(result)

if __name__ == '__main__':
    message = "012222 1114142503 0313012513 03141418192102 0113 2419182119021713 06131715070119"
    c = Cipher("BHISOECRTMGWYVALUZDNFJKPQX")
    print(c.decrypt(message))
