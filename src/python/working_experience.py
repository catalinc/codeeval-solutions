import sys
import calendar
import datetime
import math


def get_working_experience(s):
    periods = []
    for p in s.split(';'):
        periods.append(parse_period(p))

    periods = sorted(periods, key=lambda t: t[0])
    i = 0
    while i < len(periods) - 1:
        current = periods[i]
        next = periods[i+1]
        merged = merge_periods(current, next)
        if merged:
            periods[i] = merged
            periods.pop(i+1)
        else:
            i += 1

    years = get_years(periods)
    return years


def parse_period(p):
    start, end = p.split('-')

    start = datetime.datetime.strptime(start.lstrip(), '%b %Y')

    end = datetime.datetime.strptime(end, '%b %Y')
    end = end.replace(day=calendar.monthrange(end.year, end.month)[1], hour=23, minute=59, second=59)

    return start, end


def merge_periods(a, b):
    if a[1] < b[0]:
        return None
    start = a[0] if a[0] < b[0] else b[0]
    end = a[1] if a[1] > b[1] else b[1]
    return start, end


def get_years(periods):
    n = datetime.timedelta()
    for p in periods:
        n += p[1] - p[0]
    return int(math.floor((n.days + n.seconds/86400) / 365.2425))


if __name__ == '__main__':
    if len(sys.argv) == 2:
        with open(sys.argv[1], 'r') as infile:
            for line in infile:
                line = line.rstrip()
                print(get_working_experience(line))
