# Realize classical ML Algorithm By Numpy


```python
# Import packages needed for coding
import numpy as np
import matplotlib.pyplot as plt
```

##  Linear Regression


```python
x = np.linspace(0, 10, 50)
y = 3 * x + 3 + np.random.randn(x.size)
N = len(x)

# plt.scatter(x, y)
# plt.show()

# Parameters
LR = 0.001

plt.ion()


def grad(w, b):
    # Compute Gradient
    b_grad = (-2 / N * (y - (w * x + b))).sum()
    w_grad = (-2 / N * x * (y - (w * x + b))).sum()
    return w_grad, b_grad


def optimizer(w, b):
    # Optimizer
    plt.scatter(x, y)
    w_grad, b_grad = grad(w, b)

    new_w = w - LR * w_grad
    new_b = b - LR * b_grad

    return new_w, new_b


losses = []

W, B, EPOCH = 0, 0, 100
for epoch in range(EPOCH):
    W, B = optimizer(W, B)
    P = W * x + B

    plt.cla()
    plt.subplot(1, 2, 1)
    plt.scatter(x, y)
    plt.plot(x, P, c='red')
    loss = np.abs((P - y).mean())
    plt.xlabel(f"Loss: {loss}.4f")
    plt.subplot(1, 2, 2)
    losses.append(loss)
    plt.plot(losses,'yo-')
    plt.pause(0.01)
    print(f"Epoch : {epoch}, Loss : {loss}")

plt.ioff()
plt.show()
```

## Logistic Regression


```python
# Load Data
LR = 0.01
MAX_EPOCH = 100

theta = np.zeros((3, 1))
# theta = np.array([-16.38, 0.1483, 0.1589]).reshape(3, 1)

rx = np.fromfile('ex4/ex4x.dat', sep='\n').reshape(-1, 2)
x = (rx - rx.mean(0))/rx.std(0) # z-score 归一化
x = np.hstack((np.ones((80, 1)), x))
y = np.fromfile('ex4/ex4y.dat', sep='\n').reshape(-1, 1)

N = x.shape[0]
pos = (y == 1)[:, 0]
neg = (y == 0)[:, 0]

L = list()
L.clear()
# plt.scatter(x[pos, 1], x[pos, 2], marker='+', c='red')
# plt.scatter(x[neg, 1], x[neg, 2], marker='o', c='blue')


def sigmoid(z):
    return 1 / (1 + np.exp(-z))


def GD():
    global theta, x, y
    h = sigmoid(x.dot(theta))
    grad = (x.T).dot(y - h)
    l = np.exp((np.log(h).T.dot(y) + np.log(1 - h).T.dot(1-y)).sum()/N)
    return grad, l


def SGD():
    global theta, x, y
    r = np.random.randint(0, x.shape[0])
    h = sigmoid(x[r, np.newaxis].dot(theta))
    grad = (y[r] - h)*(x[r, np.newaxis].T)
    l = np.exp((np.log(h).T.dot(y[r]) + np.log(1 - h).T.dot(1-y[r])).sum())
    return grad, l


def NewTon():
    global theta, x, y
    h = sigmoid(x.dot(theta))
    grad = (x.T).dot(h-y)/N
    H = (1/N)*x.T.dot(np.diag(np.squeeze(h))
                      ).dot(np.diag(np.squeeze(1-h))).dot(x)
    d = - np.linalg.inv(H).dot(grad)
    l = np.exp(-(np.log(h).T.dot(y) + np.log(1 - h).T.dot(1-y)).sum()/N)
    return d, l


def optimizer(way):
    res = 0
    if way == 'GD':
        res, l = GD()
    if way == 'SGD':
        res, l = SGD()
    if way == 'NT':
        res, l = NewTon()
    global theta, LR, L
    theta = theta + LR * res
    L.append(l)
#     print(f"grad : {res}, theta: {theta}")


d = np.linspace(np.min(x[:, 1]), np.max(x[:, 1]), 100)

plt.clf()
plt.ion()

for epoch in range(MAX_EPOCH):
    optimizer('NT')
    plt.figure(figsize=(12, 5), dpi=80)
    plt.subplot(121)
    plt.xlim(np.min(x[:,1]), np.max(x[:,1]))
    plt.ylim(np.min(x[:,2]), np.max(x[:,2]))
    plt.scatter(x[pos, 1], x[pos, 2], marker='+', label="Admitted", c='red')
    plt.scatter(x[neg, 1],
                x[neg, 2],
                marker='o',
                label='Not Admitted',
                c='blue')
    plt.plot(d,
             -(theta[0, 0] + theta[1, 0] * d) / theta[2, 0],
             'r',
             c='green',
             label='descision boundary')
    plt.legend(loc="upper right")

    plt.subplot(122)
    plt.plot(L)
    plt.pause(0.01)
    print(f'Epoch: {epoch}, Loss: {L[-1]}')
    if L[-1] < 0.5:
        break

plt.ioff()
plt.show()
print(theta)
```

## Softmax Regression


```python
# Load Data
LR = 0.01
MAX_EPOCH = 100

theta = np.zeros((3,2 ))
# theta = np.array([-16.38, 0.1483, 0.1589]).reshape(3, 1)

rx = np.fromfile('ex4/ex4x.dat', sep='\n').reshape(-1, 2)
x = (rx - rx.mean(0))/rx.std(0) # z-score 归一化
x = np.hstack((np.ones((80, 1)), x))
y = np.fromfile('ex4/ex4y.dat', sep='\n').reshape(-1, 1)


N = x.shape[0]
pos = (y == 1)[:, 0]
neg = (y == 0)[:, 0]

loss = list()
loss.clear()

def GD():
    global theta, x, y
    grad = np.zeros((3,2))
    l = 0
    for k in range(theta.shape[1]-1):
        theta_k = theta[:,k]
        grad_k = np.zeros((3,1))
        for i,xi in enumerate(x):
            j = y[i,0] # the class  of current item
            t = 1 if k==j else 0
            h_k = np.exp(xi.dot(theta_k))/np.exp(xi.dot(theta)).sum()
            l += t*np.log(h_k)
            grad_k += (xi.T*(t - h_k))[:,np.newaxis]
        grad[:,k]= grad_k[:,0]
    return grad,l


def SGD():
    global theta, x, y
    grad = np.zeros((3,2))
    l = 0
    r = np.random.randint(0, x.shape[0])
    for k in range(theta.shape[1]-1):
        theta_k = theta[:,k]
        xi = x[r]
        j = y[r,0] # the class  of current item
        t = 1 if k==j else 0
        h_k = np.exp(xi.dot(theta_k))/np.exp(xi.dot(theta)).sum()
        l += t*np.log(h_k)
        grad_k = (xi.T*(t - h_k))[:,np.newaxis]
        grad[:,k]= grad_k[:,0]
    return grad,l

def optimizer(method='GD'):
    global theta,loss
    if method == 'GD':
        grad,l = GD()
    elif method == 'SGD':
        grad,l = SGD()
    theta = theta + LR*grad
    loss.append(np.exp(l))

d = np.linspace(np.min(x[:, 1]), np.max(x[:, 1]), 100)

plt.clf()
plt.ion()
    
for epoch in range(MAX_EPOCH):
    optimizer('SGD')
    plt.figure(figsize=(12, 5), dpi=80)
    plt.subplot(121)
    plt.xlim(np.min(x[:,1]), np.max(x[:,1]))
    plt.ylim(np.min(x[:,2]), np.max(x[:,2]))
    plt.scatter(x[pos, 1], x[pos, 2], marker='+', label="Admitted", c='red')
    plt.scatter(x[neg, 1],
                x[neg, 2],
                marker='o',
                label='Not Admitted',
                c='blue')
    plt.plot(d,
             -(theta[0, 0] + theta[1, 0] * d) / theta[2, 0],
             'r',
             c='green',
             label='descision boundary')
    plt.legend(loc="upper right")

    plt.subplot(122)
    plt.ylim(0,1)
    plt.plot(loss)
    plt.pause(0.01)

    print(f'Epoch: {epoch}, Loss: {loss[-1]}')

plt.ioff()
plt.show()
print(theta)
```


```python

```
