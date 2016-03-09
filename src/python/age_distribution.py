import sys


_DISTRIBUTION = [(0, 2, "Still in Mama's arms"),
                 (3, 4, "Preschool Maniac"),
                 (5, 11, "Elementary school"),
                 (12, 14, "Middle school"),
                 (15, 18, "High school"),
                 (19, 22, "College"),
                 (23, 65, "Working for the man"),
                 (66, 100, "The Golden Years")]


def age_distribution(age):
    for d in _DISTRIBUTION:
        if d[0] <= age <= d[1]:
            return d[2]
    return "This program is for humans"

if __name__ == '__main__':
    with open(sys.argv[1], 'r') as infile:
        for line in infile:
            print(age_distribution(int(line)))