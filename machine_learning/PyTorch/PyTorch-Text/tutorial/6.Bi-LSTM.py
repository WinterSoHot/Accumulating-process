import numpy as np
import torch
import torch.nn as nn
import torch.optim as optim

sentence = (
    'Lorem ipsum dolor sit amet consectetur adipisicing elit '
    'sed do eiusmod tempor incididunt ut labore et dolore magna '
    'aliqua Ut enim ad minim veniam quis nostrud exercitation'
)

word_dict = {w: i for i, w in enumerate(list(set(sentence.split())))}
number_dict = {i: w for i, w in enumerate(list(set(sentence.split())))}
n_class = len(word_dict)
max_len = len(sentence.split())
n_hidden = 5


def make_batch(sentence):
    input_batch = []
    target_batch = []

    words = sentence.split()
    for i, word in enumerate(words[:-1]):
        # 单词等级， 根据输入当前的词语组，目标是下一个词语
        input = [word_dict[n] for n in words[:(i + 1)]]
        input = input + [0] * (max_len - len(input))  # 剩余置为0
        target = word_dict[words[i + 1]]
        input_batch.append(np.eye(n_class)[input])
        target_batch.append(target)

    return torch.tensor(input_batch).float(), torch.tensor(target_batch).long()


class BiLSTM(nn.Module):
    def __init__(self):
        super().__init__()
        # 因为采用 one-hot编码，因此输入维度维n_class
        self.lstm = nn.LSTM(input_size=n_class, hidden_size=n_hidden, bidirectional=True)
        self.l1 = nn.Linear(n_hidden * 2, n_class)

    def forward(self, X):
        input = X.transpose(0, 1)  # input : [n_step, batch_size, n_class]
        hidden_state = torch.zeros(1 * 2, len(X),
                                   n_hidden)  # [num_layers(=1) * num_directions(=1), batch_size, n_hidden]
        cell_state = torch.zeros(1 * 2, len(X), n_hidden)  # [num_layers(=1) * num_directions(=1), batch_size, n_hidden]

        outputs, (_, _) = self.lstm(input=input, hx=(hidden_state, cell_state))
        return self.l1(outputs[-1])


model = BiLSTM()

input_batch, target_batch = make_batch(sentence)

criterion = nn.CrossEntropyLoss()
optimizer = optim.Adam(model.parameters(), lr=0.001)

# Training
for epoch in range(10000):
    optimizer.zero_grad()
    output = model(input_batch)
    loss = criterion(output, target_batch)
    if (epoch + 1) % 1000 == 0:
        print('Epoch:', '%04d' % (epoch + 1), 'cost =', '{:.6f}'.format(loss))

    loss.backward()
    optimizer.step()

predict = model(input_batch).data.max(1, keepdim=True)[1]
print(sentence)
print([number_dict[n.item()] for n in predict.squeeze()])
