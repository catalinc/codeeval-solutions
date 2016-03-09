import sys
import copy

# Solution to https://www.codeeval.com/open_challenges/216/

_ACTIONS = {
    'ban': 0,
    'grant': 1,
    'write': 2,
    'read': 4,
}

_ACCESS = {
    'user_1': {'file_1': 7, 'file_2': 3, 'file_3': 0},
    'user_2': {'file_1': 6, 'file_2': 2, 'file_3': 4},
    'user_3': {'file_1': 5, 'file_2': 1, 'file_3': 5},
    'user_4': {'file_1': 3, 'file_2': 7, 'file_3': 1},
    'user_5': {'file_1': 6, 'file_2': 0, 'file_3': 2},
    'user_6': {'file_1': 4, 'file_2': 2, 'file_3': 6}
}


def eval_actions(actions):
    access = copy.deepcopy(_ACCESS)
    for action in actions:
        if not eval_one_action(action, access):
            return False
    return True


def eval_one_action(action, access):
    tokens = action.split('=>')
    user_id = tokens[0]
    file_id = tokens[1]
    perm = _ACTIONS[tokens[2]]
    user_perm = access[user_id][file_id]
    if user_perm == 0:
        return False
    if perm == 1:
        new_perm = _ACTIONS[tokens[3]]
        other_user_id = tokens[4]
        if user_id == other_user_id and user_perm == 1:
            access[user_id][file_id] = new_perm
            return True
        if user_perm & perm == 1:
            access[other_user_id][file_id] = new_perm
            return True
        return False
    else:
        return user_perm & perm == perm


if __name__ == '__main__':
    with open(sys.argv[1], 'r') as infile:
        for line in infile:
            print(eval_actions(line.rstrip().split(' ')))
