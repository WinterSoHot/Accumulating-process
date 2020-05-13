import numpy as np
import torch
import torch.nn as nn
import torch.optim as optim

sentences = ["i like dog", "i love coffee", "i hate milk"]

word_list = " ".join(sentences).split()
word_list = list(set(word_list))
word_dict = {w: i for i, w in enumerate(word_list)}
number_dict = {i: w for i, w in enumerate(word_list)}
n_class = len(word_dict)

# TextRNN Parameter
batch_size = len(sentences)
n_step = 2  # number of cells(= number of Step)
n_hidden = 5  # number of hidden units in one cell


def make_batch(sentences):
    input_batch = []
    target_batch = []

    for sen in sentences:
        word = sen.split()
        # 词语等级，输入为几个词语，目标是下一个词
        input_data = [word_dict[n] for n in word[:-1]]
        target = word_dict[word[-1]]

        input_batch.append(np.eye(n_class)[input_data])
        target_batch.append(target)

    return torch.tensor(input_batch).float(), torch.tensor(target_batch).long()


input_batch, target_batch = make_batch(sentences)


class TextRNN(nn.Module):
    def __init__(self):
        super().__init__()
        self.rnn = nn.RNN(input_size=n_class, hidden_size=n_hidden)
        self.W = nn.Linear(in_features=n_hidden, out_features=n_class)

    def forward(self, hidden, X):
        X = X.transpose(0, 1)  # X : [n_step, batch_size, n_class]
        outputs, hidden = self.rnn(X, hidden)
        # outputs : [n_step, batch_size, num_directions(=1) * n_hidden]
        # hidden : [num_layers(=1) * num_directions(=1), batch_size, n_hidden]
        outputs = outputs[-1]  # [batch_size, num_directions(=1) * n_hidden]
        return self.W(outputs)


model = TextRNN()
criterion = nn.CrossEntropyLoss()
optimizer = optim.Adam(model.parameters(), lr=0.001)

for epoch in range(5000):
    optimizer.zero_grad()

    init_hidden = torch.randn((1, batch_size, n_hidden))
    predict = model(init_hidden, input_batch)

    loss = criterion(input=predict, target=target_batch)
    if (epoch + 1) % 1000 == 0:
        print('Epoch:', '%04d' % (epoch + 1), 'cost =', '{:.6f}'.format(loss))

    loss.backward()
    optimizer.step()


input = [sen.split()[:-1] for sen in sentences]

# Predict
hidden = torch.zeros(1, batch_size, n_hidden)
predict = model(hidden, input_batch).data.max(1, keepdim=True)[1]
print([sen.split()[:2] for sen in sentences], '->', [number_dict[n.item()] for n in predict.squeeze()])