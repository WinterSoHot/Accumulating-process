from random import choice
# 随机漫步


class Random_Walk():
    """docstring for Random_Walk"""

    def __init__(self, num_point=5000):
        super(Random_Walk, self).__init__()
        self.num_point = num_point
        self.x_values = [0]
        self.y_values = [0]


    def get_step(self): 
        direction = choice([1, -1])
        distance = choice([0, 1, 2, 3, 4, 5, 6, 7, 8])
        return direction * distance

    def fill_walk(self):

        while len(self.x_values) < self.num_point:
            # # l两个方向
            # x_direction = choice([1,-1])
            # # 随机步长
            # x_distance = choice([0,1,2,3,4,5])
            # # 行走距离
            # x_step = x_direction * x_distance

            # y_direction = choice([1,-1])
            # y_distance = choice([0,1,2,3,4,5])
            # y_step = y_direction * y_distance
            x_step = self.get_step()
            y_step = self.get_step()

            # 拒绝原地漫步
            if x_step == 0 and y_step == 0:
                continue

            next_x = self.x_values[-1] + x_step
            next_y = self.y_values[-1] + y_step

            self.x_values.append(next_x)
            self.y_values.append(next_y)

    
