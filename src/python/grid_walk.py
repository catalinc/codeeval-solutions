import sys
from functools import wraps
from time import time

def timed(f):
    @wraps(f)
    def wrapper(*args, **kwds):
        start = time()
        result = f(*args, **kwds)
        elapsed = time() - start
        print "%s took %f seconds to finish" % (f.__name__, elapsed)
        return result
    return wrapper

explored = set()
_cache = {}

def sum_of_digits(number):
    n_abs = abs(number)
    n = n_abs
    if n in _cache:
        return _cache[n]
    r = 0
    while n:
        r += n % 10
        n /= 10
    _cache[n_abs] = r
    return r


def can_walk((x, y)):
    return sum_of_digits(x) + sum_of_digits(y) <= 19


def walkable_neighbours((x, y)):
    neighbours = []
    for dx in (-1, 1):
        p = (x + dx, y)
        if (not p in explored) and can_walk(p):
            neighbours.append(p)
    for dy in (-1, 1):
        p = (x, y + dy)
        if (not p in explored) and can_walk(p):
            neighbours.append(p)
    return neighbours

@timed
def grid_walk():
    to_explore = [(0, 0)]
    while to_explore:
        point = to_explore.pop()
        explored.add(point)
        for neighbour in walkable_neighbours(point):
            to_explore.append(neighbour)
    return len(explored)


if __name__ == '__main__':
    print(grid_walk())
