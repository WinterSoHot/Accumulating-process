class Setting():
    #外星人设置类
    def __init__(self):
        """初始化游戏的设置"""
        # 屏幕设置
        self.screen_width = 800
        self.screen_height = 400
        self.bg_color = (230, 230, 230)
        self.ship_speed_factor = 1.5

        #子弹设置
        self.bullet_speed_factor = 0.6
        self.bullet_width = 15
        self.bullet_height = 15
        self.bullet_color = 60,60,60
        self.bullet_allowed = 3