import requests
import pygal
from pygal.style import LightColorizedStyle as LCS, LightenStyle as LS
# 获取github上面 python 项目
url = 'https://api.github.com/search/repositories?q=language:python&sort=stars'
r = requests.get(url)

print("Status Code:",r.status_code)

resp_dict = r.json()

print(resp_dict.keys())

print("Total repositories:",resp_dict['total_count'])

repo_dicts = resp_dict['items']
print('Repositories returned',len(repo_dicts))

repo_dict = repo_dicts[0]
print("\nKeys",len(repo_dict))

for key in sorted(repo_dict.keys()):
    print(key)

names,plot_dicts = [],[]
for item in repo_dicts:
    print("\nSelected information about first repository:")
    print('Name:', item['name'])
    print('Owner:', item['owner']['login'])
    print('Stars:', item['stargazers_count'])
    print('Repository:', item['html_url'])
    print('Created:', item['created_at'])
    print('Updated:', item['updated_at'])
    print('Description:', item['description'])
    names.append(item['name'])
    plot_dict = {
          'value': item['stargazers_count'],
          #'label': item['description'],
          'xlink':item['html_url']
        }
    plot_dicts.append(plot_dict)
    
my_style = LS('#333366',base_style=LCS)
my_config = pygal.Config()
my_config.x_label_rotation = 45
my_config.show_legend = False
my_config.title_font_size = 24
my_config.label_font_size = 14
my_config.major_label_font_size = 18
my_config.truncate_label = 15
my_config.show_y_guides = False
my_config.width = 1000
chart = pygal.Bar(my_config,style=my_style)
chart.title = 'Most-Starred Python Projects on GitHub'
chart.x_labels = names
chart.add('',plot_dicts)
chart.render_to_file('python_repos.svg')
