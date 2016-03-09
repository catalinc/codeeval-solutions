import sys
import fnmatch


# Solution to https://www.codeeval.com/open_challenges/169/


def find_matches(pattern, filenames):
    return [f for f in filenames if fnmatch.fnmatchcase(f, pattern)]


if __name__ == '__main__':
    with open(sys.argv[1], 'r') as infile:
        for line in infile:
            line = line.rstrip()
            tokens = line.split(' ')
            matches = find_matches(tokens[0], tokens[1:])
            if matches:
                print(' '.join(matches))
            else:
                print('-')