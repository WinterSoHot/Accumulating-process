from django.conf.urls import url
from . import views

app_name='pizzeria'

urlpatterns = [
    # 主页
    url(r'^$', views.index, name='index'),
]