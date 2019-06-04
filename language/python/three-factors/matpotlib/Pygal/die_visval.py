from die import Die
import pygal

die = Die()
die2 = Die()

results = []

for roll_num in range(50000):
    result = die.roll() + die2.roll()
    results.append(result)
print(results)

# 分析结果
frequence = []
max_num = die.num_size + die2.num_size
for value in range(1,max_num+1):
    count = results.count(value)
    frequence.append(count)

print(frequence)

hist = pygal.Bar()

hist.title = 'Results of rolling one D6 1000 times.'
# hist.x_labels = ['1','2','3','4','5','6']
hist.x_labels = ['2','3','4','5','6','7','8','9','10','11','12']
hist.x_title = 'Result'
hist.y_title = 'Frequency of Result'

# hist.add('D6',frequence)
hist.add('D6+D6',frequence)

hist.render_to_file('div_visival.svg')