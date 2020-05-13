import numpy as np
import torch
import torch.nn as nn
import torch.optim as optim

sentences = ["i like dog", "i love coffee", "i hate milk"]

word_list = ' '.join(sentences).split()
word_list = list(set(word_list))

word_dict = {w: i for i, w in enumerate(word_list)}
number_dict = {i: w for i, w in enumerate(word_list)}

n_class = len(word_dict)

# 参数设置
n_step = 2  # n-1 in paper
n_hidden = 2  # h in paper
m = 2  # m in paper


def make_batch(sentences):
    input_batch = []
    target_batch = []
    for sen in sentences:
        word = sen.split()
        input_data= [word_dict[n] for n in word[:-1]]
        target = word_dict[word[-1]]

        input_batch.append(input_data)
        target_batch.append(target)

    return input_batch, target_batch


class NNLM(nn.Module):

    def __init__(self):
        super(NNLM, self).__init__()
        self.C = nn.Embedding(n_class, m)
        # self.H = nn.Parameter(torch.randn(n_step * m, n_hidden), requires_grad=True)
        self.H = nn.Linear(n_step * m, n_hidden)
        self.W = nn.Linear(n_step * m, n_class, bias=False)
        self.U = nn.Linear(n_hidden, n_class)
        self._init_parameter()
        # self.W = nn.Parameter(torch.randn(n_step * m, n_class), requires_grad=True)
        # self.d = nn.Parameter(torch.randn(n_hidden), requires_grad=True)
        # self.U = nn.Parameter(torch.randn(n_hidden, n_class), requires_grad=True)
        # self.b = nn.Parameter(torch.randn(n_class), requires_grad=True)

    def _init_parameter(self):
        self.H.weight.data.normal_(0, 1)
        self.H.bias.data.normal_(0, 1)
        self.W.weight.data.normal_(0, 1)
        # self.W.bias.data.normal_(0, 1)
        self.U.weight.data.normal_(0, 1)
        self.U.bias.data.normal_(0, 1)

    def forward(self, X):
        X = self.C(X)
        X = X.view(-1, n_step * m)  # [batch_size, n_step * m]
        # tanh = torch.tanh(self.d + torch.mm(X, self.H))  # [batch_size, n_step * m]
        tanh = torch.tanh(self.H(X))  # [batch_size, n_step * m]
        # output = self.b + torch.mm(X, self.W) + torch.mm(tanh, self.U)  # [batch_size, n_class]
        output = self.W(X) + self.U(tanh)  # [batch_size, n_class]
        return output


model = NNLM()

criterion = nn.CrossEntropyLoss()
optimizer = optim.Adam(model.parameters(), lr=0.001)

input_batch, target_batch = make_batch(sentences)
input_batch = torch.tensor(input_batch).long()
target_batch = torch.tensor(target_batch).long()

# 训练
for epoch in range(5000):
    optimizer.zero_grad()
    predict = model(input_batch)
    loss = criterion(predict, target_batch)
    if (epoch + 1) % 1000 == 0:
        print('Epoch:', '%04d' % (epoch + 1), 'cost =', '{:.6f}'.format(loss))
    loss.backward()
    optimizer.step()

# Predict
predict = model(input_batch).data.max(1, keepdim=True)[1]

# Test
print([sen.split()[:2] for sen in sentences], '->', [number_dict[n.item()] for n in predict.squeeze()])
