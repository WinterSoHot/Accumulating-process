from django.shortcuts import render
from django.http import HttpResponseRedirect,Http404
from django.urls import reverse

from .models import Topic,Entry
from .forms import TopicForm,EntryForm
from django.contrib.auth.decorators import login_required


# Create your views here.
def index(request):
    """学习笔记的主页"""
    return render(request, 'learning_logs/index.html')

# 让登录的用户才能访问 在setting.py配置未登录重点向页面   
@login_required  
def topics(request):
    
    topics = Topic.objects.filter(owner=request.user).order_by("date_added")
    context = {'topics':topics}
    return render(request,'learning_logs/topics.html',context)
    
@login_required    
def topic(request,topic_id):
    topic = Topic.objects.get(id=topic_id)
    if check_topic_owner(request,topic)==False:
        raise Http404
    entries = topic.entry_set.order_by("date_added")
    context = {"topic":topic,"entries":entries}
    return render(request,'learning_logs/topic.html',context)
   
@login_required   
def new_topic(request):
    if request.method != 'POST':
        form = TopicForm()
    else:
        form = TopicForm(request.POST)
        if form.is_valid():
            new_topic = form.save(commit=False)
            new_topic.owner = request.user
            new_topic.save()
            return HttpResponseRedirect(reverse('learning_logs:topics'))
    context = {'form': form}
    return render(request, 'learning_logs/new_topic.html', context)
    
@login_required    
def new_entry(request,topic_id):
    topic = Topic.objects.get(id=topic_id)
    
    if  check_topic_owner(request,topic)==False:
        raise Http404
    
    if request.method != 'POST':
        # 未提交数据，创建一个空表单
        form = EntryForm()
    else:
        # POST提交的数据，对数据进行处理
        form = EntryForm(data=request.POST)
        if form.is_valid():
            new_entry = form.save(commit=False)
            new_entry.topic = topic
            new_entry.save()
            return HttpResponseRedirect(reverse('learning_logs:topic',args=(topic_id)))
    context = {'topic':topic,'form':form}
    return render(request,'learning_logs/new_entry.html',context)

@login_required
def edit_entry(request,entry_id):
    entry = Entry.objects.get(id=entry_id)
    topic = entry.topic
    if check_topic_owner(request,topic)==False:
        raise Http404
        
    if request.method != 'POST':
        form = EntryForm(instance=entry)
    else:
        form = EntryForm(instance=entry,data=request.POST)
        if form.is_valid():
            form.save()
            return HttpResponseRedirect(reverse('learning_logs:topic',args=[topic.id]))
    context = {'entry':entry,'topic':topic,'form':form}
    return render(request,'learning_logs/edit_entry.html',context)
    
# 检查用户是否登录
def check_topic_owner(request,topic):
    if topic.owner != request.user:
        return False
    else:
        return True
    