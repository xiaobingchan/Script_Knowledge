
0，创建Django项目
django-admin startproject mysite
cd mysite
python3 manage.py startapp mysite2

1，运行Django项目（指定8080端口）
python3 manage.py runserver 8080

2，创建后台管理员：
python3 manage.py migrate
python3 manage.py createsuperuser

3，增加一个请求返回
#  往项目C:\Users\Administrator\Desktop\mysite\mysite的url.py增加请求定义：
   from mysite2 import views
   urlpatterns = [
       ......................................
   	url(r'^luyanjie/', views.show_time),
   	......................................
   ]
#  往应用C:\Users\Administrator\Desktop\mysite\mysite2的views.py增加定义：
   from django.shortcuts import HttpResponse
   def show_time(req):
       return HttpResponse("hello")
#  访问：http://127.0.0.1:8080/luyanjie/

4，传值到html：
def show_time(req):
	t=time.ctime()
	return render(req,"index.html",locals())

html页面：{{t}}

错误：

1，Django admin 产生'WSGIRequest' object has no attribute 'user'的错误：

C:\Users\Administrator\Desktop\mysite\mysite的settings.py的中间件改为"MIDDLEWARE_CLASSES"

2，Django 找不到模版报错" django.template.exceptions.TemplateDoesNotExist: index.html"

TEMPLATES = [
    {
        ...............
        'DIRS': [os.path.join(BASE_DIR, 'templates')],
		............
]

3，