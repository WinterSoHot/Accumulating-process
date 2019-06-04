import matplotlib.pyplot as plt

x_values = list(range(1,1001))
y_values =[ value**2 for value in x_values]

#plt.scatter(x_values,y_values,c='red',edgecolor='none',s=40)  #边框  大小
#plt.scatter(x_values,y_values,c=(0,0,0.8),edgecolor='none',s=40) #使用RGB指定颜色 范围为0~1
#使用颜色映射
plt.scatter(x_values,y_values,c=y_values,cmap=plt.cm.Blues,edgecolor='none',s=40)
# 设置图表标题并给坐标轴加上标签
plt.title("Square Numbers", fontsize=24)
plt.xlabel("Value", fontsize=14)
plt.ylabel("Square of Value", fontsize=14)

# 设置刻度标记的大小
plt.tick_params(axis='both', which='major', labelsize=14)

plt.axis([0,1001,0,1100000])
#plt.show()

# 自动保存到文件  param1 文件名  param2 删除多余空白
plt.savefig('squares_plot.png', bbox_inches='tight') 