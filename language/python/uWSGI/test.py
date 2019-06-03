def application(env,start_response):
    start_response('200 OK',[('ContentType','text/html')])
    return [b'Hello World']

'''
uwsgi --http :9090 --wsgi-file test.py
'''
