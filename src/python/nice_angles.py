import sys


def convert(degrees_decimal):
    degrees = int(degrees_decimal)
    degrees_decimal -= degrees
    degrees_decimal *= 60
    minutes = int(degrees_decimal)
    degrees_decimal -= minutes
    seconds = int(degrees_decimal * 60)
    return degrees, minutes, seconds


if __name__ == '__main__':
    with open(sys.argv[1], 'r') as infile:
        for line in infile:
            print("%d.%02d'%02d\"" % convert(float(line)))