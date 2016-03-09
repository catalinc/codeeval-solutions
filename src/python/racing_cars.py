import sys


def solve(track):
    route = []
    last_way_point = -1
    for section in track:
        way_point = section.find('C')
        if way_point < 0:
            way_point = section.find('_')
        direction = '|'
        if last_way_point >= 0:
            if way_point > last_way_point:
                direction = '\\'
            elif way_point < last_way_point:
                direction = '/'
        last_way_point = way_point
        route.append(set_at(section, way_point, direction))
    return '\n'.join(route)


def set_at(s, index, ch):
    l = list(s)
    l[index] = ch
    return ''.join(l)


if __name__ == '__main__':

    with open(sys.argv[1], 'r') as infile:
        track = []
        for line in infile:
            track.append(line.rstrip())
        print(solve(track))