import sys
from collections import namedtuple
from collections import Counter
from collections import defaultdict

User = namedtuple('User', ['name', 'friends', 'groups'])


def parse_user(s):
    name, friends, groups = s.split(':')
    return User(name=name,
                friends=set(friends.split(',') if friends else set()),
                groups=set(groups.split(',')) if groups else set())


if __name__ == '__main__':
    with open(sys.argv[1]) as infile:
        users = {}
        for line in infile:
            user = parse_user(line.rstrip())
            users[user.name] = user
        suggest_groups = defaultdict(list)
        for name, user in users.items():
            counter = Counter()
            for friend_name in user.friends:
                friend = users[friend_name]
                for group in friend.groups:
                    counter[group] += 1
            n = int(round(float(len(user.friends)) / 2))
            for group in counter:
                if not group in user.groups and counter[group] >= n:
                    suggest_groups[name].append(group)
        for name in sorted(suggest_groups):
            print('%s:%s' % (name, ','.join(sorted(suggest_groups[name]))))