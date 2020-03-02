import unicodedata
import string
import glob
import torch
import os

findFiles = lambda path: glob.glob(path)
all_letters = string.ascii_letters + " .,;'"
n_letters = len(all_letters)


def unicodeToAscii(s):
    """
    Turn a Unicode string to plain ASCII, thanks to https://stackoverflow.com/a/518232/2809427
    :param s:
    :return:
    """
    return ''.join(
        c for c in unicodedata.normalize('NFD', s)
        if unicodedata.category(c) != 'Mn'
        and c in all_letters
    )


# 构建类别词典，每种语言的姓名列表
category_lines = {}
all_categories = []


def readLines(filename):
    """
    读取文件的所有行
    :param filename:
    :return:
    """
    lines = open(filename, encoding='utf-8').read().strip().split('\n')
    return [unicodeToAscii(line) for line in lines]


# 解析语言文件
for filename in findFiles('../data/names/*.txt'):
    category = os.path.splitext(os.path.basename(filename))[0]
    all_categories.append(category)
    lines = readLines(filename)
    category_lines[category] = lines

n_categories = len(all_categories)


# 将 name 转换成 Tensor 以便使用它们
# 我们使用one-hot 表示单个字母
# 应次一个word 将字母连接成一个2D-matrix <line_length * 1 * n_letters>
# 额外的1 dim 因为 PyTorch 认为 everything in batches，这里我们使用 a batch size of 1
def letterToIndex(letter):
    """
    返回字母的位置
    :param letter:
    :return:
    """
    return all_letters.find(letter)


def letterToTensor(letter):
    """
    将字母转为 Tensor
    :param letter:
    :return:
    """
    tensor = torch.zeros(1, n_letters)  # (1, n_letters)
    tensor[0][letterToIndex(letter)] = 1
    return tensor


def lineToTensor(line):
    """
    将 line(name) 转为 Tensor
    :param line:
    :return:
    """
    tensor = torch.zeros(len(line), 1, n_letters)
    for li, letter in enumerate(line):
        tensor[li][0][letterToIndex(letter)] = 1
    return tensor
