#! /bin/bash

myUrl="blog.dx.com.cn"

# 只读变量
readonly myUrl
myUrl="change"
echo $myUrl

# 删除变量
unset myUrl
echo $myUrl
