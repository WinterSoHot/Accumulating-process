import json
import pygal
from pygal.style import RotateStyle
from country_code import get_country_code
filename = 'population_data.json'
with open(filename) as f:
    pop_data = json.load(f)

cc_population = {}

for pop_dict in pop_data:
    if pop_dict['Year'] == '2010':
        country_name = pop_dict['Country Name']
        population = int(float(pop_dict['Value']))
        code = get_country_code(country_name)
        if code:
            print(code+":"+str(population))
            cc_population[code] = population
        else:
            print("ERROR-"+country_name)

cc_pop_1,cc_pop_2,cc_pop_3 = {},{},{}
for item,value in cc_population.items():
    if value < 10000000:
        cc_pop_1[item] = value
    elif value < 100000000:
        cc_pop_2[item] =value
    else:
        cc_pop_3[item] =value
     
wm_style = RotateStyle('#336699')

wm = pygal.maps.world.World(style=wm_style)
wm.title = 'World Popultion'
wm.add('0-10m',cc_pop_1)
wm.add('10m-1bn',cc_pop_2)
wm.add('>1bn',cc_pop_3)



wm.render_to_file('world_population.svg')
