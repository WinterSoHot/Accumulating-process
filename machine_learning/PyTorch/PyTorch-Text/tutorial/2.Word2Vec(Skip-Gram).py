import numpy as np
import torch
import torch.nn as nn
import torch.optim as optim
from torch.autograd import Variable
import matplotlib.pyplot as plt

# 3 Words Sentence
sentences = ["i like dog", "i like cat", "i like animal",
             "dog cat animal", "apple cat dog like", "dog fish milk like",
             "dog cat eyes like", "i like apple", "apple i hate",
             "apple i movie book music like", "cat dog hate", "cat dog like"]

word_sequence = " ".join(sentences).split()
word_list = " ".join(sentences).split()
word_list = list(set(word_list))
word_dict = {w: i for i, w in enumerate(word_list)}

# 网络参数
batch_size = 20  # To show 2 dim embedding graph
embedding_size = 2  # To show 2 dim embedding graph
voc_size = len(word_list)


def random_batch(data, batch_size):
    random_inputs = []
    random_labels = []
    random_index = np.random.choice(range(len(data)), batch_size, replace=False)

    for i in random_index:
        random_inputs.append(np.eye(voc_size)[data[i][0]])
        random_labels.append(data[i][1])  # context word
    return torch.tensor(random_inputs).float(), torch.tensor(random_labels).long()


# Make skip gram of one size window
skip_grams = []
for i in range(1, len(word_sequence) - 1):
    target = word_dict[word_sequence[i]]
    context = [word_dict[word_sequence[i - 1]], word_dict[word_sequence[i + 1]]]

    for w in context:
        skip_grams.append([target, w])


class Word2Vec(nn.Module):
    def __init__(self):
        super().__init__()
        # W and WT is not Traspose relationship
        # self.W = nn.Parameter(-2 * torch.rand(voc_size, embedding_size) + 1,
        #                       requires_grad=True).type(torch.float)  # voc_size > embedding_size Weight
        self.W = nn.Linear(voc_size, embedding_size, bias=False)
        # self.WT = nn.Parameter(-2 * torch.rand(embedding_size, voc_size) + 1,
        #                        requires_grad=True).type(torch.float)  # embedding_size > voc_size Weight
        self.WT = nn.Linear(embedding_size, voc_size, bias=False)

    def forward(self, X):
        # X : [batch_size, voc_size]
        # hidden_layer = torch.matmul(X, self.W)  # hidden_layer : [batch_size, embedding_size]
        hidden_layer = self.W(X)  # hidden_layer : [batch_size, embedding_size]
        # output_layer = torch.matmul(hidden_layer, self.WT)  # output_layer : [batch_size, voc_size]
        output_layer = self.WT(hidden_layer)  # output_layer : [batch_size, voc_size]
        return output_layer


model = Word2Vec()

criterion = nn.CrossEntropyLoss()
optimizer = optim.Adam(model.parameters(), lr=0.001)

for epoch in range(5000):
    input_batch, target_batch = random_batch(skip_grams, batch_size)

    optimizer.zero_grad()
    predict = model(input_batch)
    loss = criterion(predict, target_batch)
    if (epoch + 1) % 1000 == 0:
        print('Epoch:', '%04d' % (epoch + 1), 'cost =', '{:.6f}'.format(loss))

    loss.backward()
    optimizer.step()


for i, label in enumerate(word_list):
    W, WT = model.parameters()
    # x,y = float(W[i][0]), float(W[i][1])
    x, y = float(W[0][i]), float(W[1][i])
    plt.scatter(x, y)
    plt.annotate(label, xy=(x, y), xytext=(5, 2), textcoords='offset points', ha='right', va='bottom')
plt.show()
