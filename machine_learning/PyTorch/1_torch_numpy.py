import numpy as np
import torch

np_data = np.arange(6).reshape((2, 3))
torch_data = torch.from_numpy(np_data)
tensor2array = torch_data.numpy()

print(
    '\nnumpy', np_data,
    '\ntorch', torch_data,
    '\ntensor2array', tensor2array
)

data = [[1, -1], [3, -4]]
tensor = torch.FloatTensor(data)  # 32-bit
data = np.array(data)

print(
    '\nabs',
    '\nnumpy', np.abs(data),
    '\ntorch', torch.abs(tensor),
)

print(
    '\nsin',
    '\nnumpy', np.sin(data),
    '\ntorch', torch.sin(tensor),
)

print(
    '\nnumpy', np.matmul(data, data),
    '\ntorch', torch.mm(tensor, tensor),
)
