class GameState():

    def __init__(self,setting):
        self.setting = setting
        self.reset_stats()
        self.game_active = False
        self.high_score = 0

    def reset_stats(self):
          """初始化在游戏运行期间可能变化的统计信息"""
          self.ships_left = self.setting.ship_limit
          self.score = 0
          self.level = 1