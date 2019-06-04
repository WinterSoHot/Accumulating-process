import matplotlib.pyplot as plt 

x_values = list(range(1,5001))
y_values = [x**3 for x in x_values]

plt.scatter(x_values,y_values,c=y_values,cmap = plt.cm.Blues)
plt.title("Title")
plt.xlabel("number",fontsize=14)
plt.ylabel("value",fontsize=14)

plt.tick_params(axis='both', which='major', labelsize=14)
plt.axis([0,5000,0,y_values[-1]])
plt.show()

