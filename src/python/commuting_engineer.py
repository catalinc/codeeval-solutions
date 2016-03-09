import sys
import re
import math

# Solution to https://www.codeeval.com/open_challenges/90/


def shortest_path(locations):
    path = []
    while locations:
        if not path:
            path.append(locations.pop(0))
        else:
            min_dist = sys.maxint
            nearest = None
            for index, location in enumerate(locations):
                dist = distance(path[-1], location)
                if dist < min_dist:
                    min_dist = dist
                    nearest = index
            path.append(locations.pop(nearest))
    return path


def distance(location1, location2):
    # Harvesine formula
    start_lat = math.radians(location1[1])
    start_lng = math.radians(location1[2])
    end_lat = math.radians(location2[1])
    end_lng = math.radians(location2[2])
    d_lat = end_lat - start_lat
    d_lng = end_lng - start_lng
    a = math.sin(d_lat/2)**2 + math.cos(start_lat) * math.cos(end_lat) * math.sin(d_lng/2)**2
    c = 2 * math.asin(math.sqrt(a))
    return 6371 * c

if __name__ == '__main__':
    with open(sys.argv[1], 'r') as infile:
        locs = []
        for line in infile:
            m = re.match('(.+?) \| (.+?) \((.+?), (.+?)\)', line)
            if m:
                idx = int(m.group(1))
                lat = float(m.group(3))
                lng = float(m.group(4))
                locs.append((idx, lat, lng))
            else:
                print('unable to parse: %s' % line)
                sys.exit(1)
        print('\n'.join([str(l[0]) for l in shortest_path(locs)]))