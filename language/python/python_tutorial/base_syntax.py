#!/usr/bin/python3

# Python 基本语法

import keyword

print(keyword.kwlist)

print("Hello,World!")

'''
多行注释
'''
a = 1
b = True
c = 1.1
cp = 1 + 2j

print(a,b,c,cp,sep=" -- ")

word = '字符串'
sentence = "这是一个句子。"
paragraph = """这是一个段落，
可以由多行组成"""

x="a"
y="b"
# 换行输出
print( x )
print( y )
 
print('---------')
# 不换行输出
print( x, end=" " )
print( y, end=" " )
print()

import sys
print('================Python import mode==========================');
print ('命令行参数为:')
for i in sys.argv:
    print (i)
print ('\n python 路径为',sys.path)

from sys import argv,path  #  导入特定的成员
 
print('================python from import===================================')
print('path:',path) # 因为已经导入path成员，所以此处引用时不需要加sys.path

list1 = ['Google', 'Runoob', 1997, 2000];
list2 = [1, 2, 3, 4, 5 ];
list3 = ["a", "b", "c", "d"];

print ("list1[0]: ", list1[0])
print ("list2[1:5]: ", list2[1:5])

print ("第三个元素为 : ", list1[2])
list1[2] = 2001
print ("更新后的第三个元素为 : ", list1[2])

print ("原始列表 : ", list1)
del list1[2]
print ("删除第三个元素 : ", list1)

tup1 = ('Google', 'Runoob', 1997, 2000);
tup2 = (1, 2, 3, 4, 5 );
tup3 = "a", "b", "c", "d";   #  不需要括号也可以

print(type(tup3))

dict = {'Name': 'Runoob', 'Age': 7, 'Class': 'First'}
 
print ("dict['Name']: ", dict['Name'])
print ("dict['Age']: ", dict['Age'])

thisset = set(("Google", "Runoob", "Taobao"))
thisset.remove("Runoob")

print(thisset)

var1 = 100
if var1:
    print ("1 - if 表达式条件为 true")
    print (var1)
 
var2 = 0
if var2:
    print ("2 - if 表达式条件为 true")
    print (var2)
print ("Good bye!")

n = 100
 
sum = 0
counter = 1
while counter <= n:
    sum = sum + counter
    counter += 1
 
print("1 到 %d 之和为: %d" % (n,sum))


class MyNumbers:
  def __iter__(self):
    self.a = 1
    return self
 
  def __next__(self):
    x = self.a
    self.a += 1
    return x
 
myclass = MyNumbers()
myiter = iter(myclass)
 
print(next(myiter))
print(next(myiter))
print(next(myiter))
print(next(myiter))
print(next(myiter))