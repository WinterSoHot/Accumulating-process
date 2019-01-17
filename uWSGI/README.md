# THE USE OF uWSGI

1. Installation of uWSGI

    sudo apt install uwsgi

2. support of python3

## test uWSGI

    [run test.py by using uWSGI](./test.py)

## use uWSGI to connect Django

1. cd project dir

        uswgi --http :9090 --module mysite.wsgi

2. access `http://127.0.0.1:9090/admin` in brower

## use nginx to connect uWSGI

1. modify `nginx.conf`

        location / {
           include uwsgi_params;
           uwsgi_pass 127.0.0.1:8090;
        }

    采用8090端口和uWSGI通信

2. touch the file of uWSGI that used to config and named `uswgi.ini`

        [uwsgi]
        print = uwsgi start
        uid = 1000
        gid = 1000
        plugins = python3
        socket = 127.0.0.1:8090
        chdir = /home/dx/桌面/Example/django/mysite
        module = mysite.wsgi
        no-orphans = true
        processes = 4
        thread = 2

3. run uWSGI Server

        uwsgi uwsgi.ini

4. access `http://127.0.0.1/admin` in brower