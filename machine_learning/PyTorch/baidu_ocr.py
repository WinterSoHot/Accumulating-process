from aip import AipOcr

""" 你的 APPID AK SK """
APP_ID = '16895222'
API_KEY = 'V7eXH8G66hcirEAQzcvfOPFN'
SECRET_KEY = 'Eiq5qUjn6QvkWAFRad4wMkVkGZNxXlsh'

client = AipOcr(APP_ID, API_KEY, SECRET_KEY)


def get_image(filePath):
    with open(filePath, 'rb') as f:
        return f.read()


""" 如果有可选参数 """
options = {"language_type": "CHN_ENG"}


# img = get_image('/home/dx/下载/发票.jpg')
img = get_image('/home/dx/图片1.png')

msg = client.receipt(img)

for text in msg.get('words_result'):
    print(text, '\n')
