"""定义learning_logs的URL模式"""
from django.conf.urls import url
from . import views

app_name='learning_logs'
urlpatterns = [
      # 主页
      url(r'^$', views.index, name='index'),
      # show all topics
      url(r'^topics/$', views.topics, name='topics'),
          
      # show detail info for specific topic
      url(r'^topics/(?P<topic_id>\d+)/$', views.topic, name='topic'),
      
      # add new topic page
      url(r'^new_topic/$', views.new_topic, name='new_topic'),
      
      # add new entry page
      url(r'new_entry/(?P<topic_id>\d+)/$', views.new_entry, name='new_entry'),
      
      # edit entry page
      url(r'edit_entry/(?P<entry_id>\d+)/$', views.edit_entry, name='edit_entry')
]