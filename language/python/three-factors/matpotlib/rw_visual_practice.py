import matplotlib.pyplot as plt
from random_walk import Random_Walk

rw = Random_Walk()
rw.fill_walk()
plt.figure(figsize=(10,6))
plt.plot(rw.x_values,rw.y_values,linewidth=10)
plt.axes().get_xaxis().set_visible(False)

plt.show()