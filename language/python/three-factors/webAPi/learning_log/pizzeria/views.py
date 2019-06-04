from django.shortcuts import render

# Create your views here.
# PIZZERIA 的主页
def index(request):
    return render(request,'pizzeria/index.html')