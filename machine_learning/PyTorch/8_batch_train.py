import torch
import torch.utils.data as Data

BATCH_SIZE = 5
# BATCH_SIZE = 8

x = torch.linspace(1, 10, 10)
y = torch.linspace(10, 1, 10)

torch_dataset = Data.TensorDataset(x, y)
loader = Data.DataLoader(
    dataset=torch_dataset,
    batch_size=BATCH_SIZE,  # 批训练 每批数据大小
    shuffle=True,  # 每次批训练是否打乱 shuffle : 随机
    num_workers=2  # 采用两个线程从数据中抽取数据
)

for epoch in range(3):
    for step, (batch_x, batch_y) in enumerate(loader):
        # train you data
        print(f"Epoch: {epoch} | Step: {step} | batch_x: {batch_x.numpy()} | batch_y: {batch_y.numpy()}")
