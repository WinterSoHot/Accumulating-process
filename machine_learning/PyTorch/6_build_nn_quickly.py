import torch
import torch.nn.functional as F
from collections import OrderedDict

# 快速构建net

# 一个时序容器。Modules 会以他们传入的顺序被添加到容器中。
net = torch.nn.Sequential(
    torch.nn.Linear(2, 10),
    torch.nn.ReLU(),
    torch.nn.Linear(20, 2)
)

# print(net)

net2 = torch.nn.Sequential(OrderedDict({
    'hidden': torch.nn.Linear(2, 10),
    'relu': torch.nn.ReLU(),
    'predict': torch.nn.Linear(20, 2)
}))

# print(net2)
for module in net2.modules():
    print(module)

for module in net2.children():
    print(module)
