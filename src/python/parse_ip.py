import sys
import re
import collections


IP_DOTTED_DEC = '\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}'
IP_DOTTED_HEX = '0x(\d{1,2})\.0x(\d{1,2})\.0x(\d{1,2})\.0x(\d{1,2})'
IP_DOTTED_OCT = '0(\d{1,3})\.0(\d{1,3})\.0(\d{1,3})\.0(\d{1,3})'
IP_DOTTED_BIN = '(0|1){1,8}\.(0|1){1,8}\.(0|1){1,8}\.(0|1){1,8}'

IP_DEC = '\d+'
IP_HEX = '0x\d+'
IP_OCT = '0\d+'
IP_BIN = '(0|1)+'

IP_PATTERN = re.compile('|'.join(["(%s)" % p for p in [IP_DOTTED_DEC, IP_DOTTED_HEX, IP_DOTTED_OCT, IP_DOTTED_BIN,
                                                       IP_DEC, IP_HEX, IP_OCT, IP_BIN]]))

MIN_IP = 16777216
MAX_IP = 4294967294


IP = collections.namedtuple('IP', ['dotted', 'decimal'])


def parse_ips(line):
    ips = []
    matches = IP_PATTERN.findall(line)
    for match in matches:
        for token in match:
            if token:
                parsed_ip = parse_ip(token)
                if parsed_ip:
                    ips.append(parsed_ip)
    return ips


def parse_ip(s):
    if '.' in s:
        decimal = dotted_to_decimal(s)
        return IP(s, decimal)
    else:
        decimal = parse_int(s)
        dotted = decimal_to_dotted(decimal)
        return IP(dotted, decimal)


def dotted_to_decimal(dotted):
    decimal = 0
    for part in dotted.split('.'):
        octet = parse_int(part)
        decimal *= 256
        decimal += octet
    return decimal


def decimal_to_dotted(decimal):
    dotted = [decimal & 0xff]
    dotted.insert(0, (decimal >> 8) & 0xff)
    dotted.insert(0, (decimal >> 16) & 0xff)
    dotted.insert(0, (decimal >> 24) & 0xff)
    return '.'.join([str(octet) for octet in dotted])


def parse_int(s):
    try:
        if is_bin(s):
            return int(s, 2)
        elif is_hex(s):
            return int(s, 16)
        elif is_oct(s):
            return int(s, 8)
        return int(s)
    except ValueError:
        return 0


def is_bin(s):
    for c in s:
        if c not in '01':
            return False
    return True


def is_hex(s):
    return s.startswith('0x')


def is_oct(s):
    return s.startswith('0')


def is_valid(ip):
    return MIN_IP <= ip.decimal <= MAX_IP


def most_frequent_ips(ips):
    freq = {}
    count = len(ips)
    for i in xrange(count):
        current = ips[i]
        if is_valid(current):
            for j in xrange(i+1, count):
                other = ips[j]
                if current.decimal == other.decimal or other.dotted.find(current.dotted) >= 0:
                    freq.setdefault(current, 1)
                    freq[current] += 1

    sorted_by_freq = sorted(freq.items(), key=lambda f: f[1], reverse=True)
    max_freq = sorted_by_freq[0][1]
    most_freq = [sorted_by_freq[0][0]]
    for i in xrange(1, len(sorted_by_freq)):
        item = sorted_by_freq[i]
        if item[1] == max_freq:
            most_freq.append(item[0])
        else:
            break
    sorted_by_dec = sorted(most_freq, key=lambda ip: ip.decimal, reverse=True)
    return [decimal_to_dotted(ip.decimal) for ip in sorted_by_dec]


if __name__ == '__main__':
    with open(sys.argv[1], 'r') as infile:
        ips = []
        for log_line in infile:
            log_line = log_line.rstrip()
            parsed_ips = parse_ips(log_line)
            if parsed_ips:
                ips.extend(parsed_ips)
        print(' '.join(most_frequent_ips(ips)))