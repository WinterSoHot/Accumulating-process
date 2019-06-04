import matplotlib.pyplot as plt
from random_walk import Random_Walk

while True:
	rw = Random_Walk(50000)
	rw.fill_walk()
	point_number = list(range(rw.num_point))
	# 设置绘图窗口的尺寸 单位为英寸
	plt.figure(figsize=(10,6))
	plt.scatter(rw.x_values, rw.y_values, c=point_number, cmap=plt.cm.Blues,
          edgecolor='none', s=1)
	# 突出起点和终点
	plt.scatter(0, 0, c='green', edgecolors='none', s=100)
	plt.scatter(rw.x_values[-1],rw.y_values[-1],c='red',edgecolors='none',s=100)
	plt.title("Random_Walk")

	# 隐藏坐标轴
	plt.axes().get_xaxis().set_visible(False)	
	plt.axes().get_yaxis().set_visible(False)

	plt.show()

	keep_running = input("Make another walk? (y/n):")
	if  keep_running == 'n':
		break
