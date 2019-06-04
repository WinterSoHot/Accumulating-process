import matplotlib.pyplot as plt

input_value = [1,2,3,4,5]
squares = [ value**2 for value in input_value]
plt.plot(input_value,squares,linewidth=5)
plt.title("Square Numbers",fontsize=24)
plt.xlabel("Value",fontsize=14)
plt.ylabel("Square of Value",fontsize=14)

# 设置刻度标记的大小
plt.tick_params(axis='both', labelsize=14)

plt.show()