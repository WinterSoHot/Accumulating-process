import numpy as np
import torch
import torch.nn as nn
import torch.optim as optim

import string

char_arr = [c for c in string.ascii_lowercase]
word_dict = {w: i for i, w in enumerate(char_arr)}
number_dict = {i: w for i, w in enumerate(char_arr)}
n_class = len(word_dict)  # number of class(=number of vocab)

seq_data = ['make', 'need', 'coal', 'word', 'love', 'hate', 'live', 'home', 'hash', 'star']

# TextLSTM Parameters

n_step = 3
n_hidden = 128


def make_batch(seq_data):
    input_batch, target_batch = [], []
    for seq in seq_data:
        # 字符等级，输入为几个字符，目标是下一个字符
        input_data = [word_dict[c] for c in seq[:-1]]
        target = word_dict[seq[-1]]

        input_batch.append(np.eye(n_class)[input_data])
        target_batch.append(target)

    return torch.tensor(input_batch).float(), torch.tensor(target_batch).long()


class TextLSTM(nn.Module):
    def __init__(self):
        super(TextLSTM, self).__init__()
        self.lstm = nn.LSTM(input_size=n_class, hidden_size=n_hidden)
        self.l1 = nn.Linear(n_hidden, n_class)

    def forward(self, X):
        input = X.transpose(0, 1)  # X : [n_step, batch_size, n_class]

        hidden_state = torch.zeros(1, len(X), n_hidden)
        cell_state = torch.zeros(1, len(X), n_hidden)

        outputs, (_, _) = self.lstm(input=input, hx=(hidden_state, cell_state))
        output = outputs[-1]  # [batch_size, n_hidden]
        return self.l1(output)


input_batch, target_batch = make_batch(seq_data)

model = TextLSTM()
criterion = nn.CrossEntropyLoss()
optimizer = optim.Adam(model.parameters(), lr=0.001)

for epoch in range(5000):
    optimizer.zero_grad()

    predict = model(input_batch)
    loss = criterion(predict, target_batch)
    loss.backward()

    if (epoch + 1) % 1000 == 0:
        print('Epoch:', '%04d' % (epoch + 1), 'cost =', '{:.6f}'.format(loss))

    optimizer.step()

inputs = [seq[:3] for seq in seq_data]
predict = model(input_batch).data.max(1, keepdim=True)[1]
print(inputs, '->', [number_dict[n.item()] for n in predict.squeeze()])
