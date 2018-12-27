from django.contrib import admin

from .models import Question, Choice


class ChoiceInline(admin.TabularInline):
    model = Choice
    extra = 3


class QuestionAdmin(admin.ModelAdmin):  # 自定义管理后台表单
    search_fields = ['question_text'] # 搜索 question_text 字段
    list_filter = ['pub_date']# 允许人们以 pub_date 字段来过滤列表
    list_display = ('question_text', 'pub_date', 'was_published_recently') # 显示的类型
    fieldsets = [
        (None,               {'fields': ['question_text']}),
        ('Date information', {'fields': ['pub_date']}),#显示表单
    ]
    inlines = [ChoiceInline]


admin.site.register(Question, QuestionAdmin)
