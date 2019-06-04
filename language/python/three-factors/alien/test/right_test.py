# 编写右面飞行的飞机
import sys
import pygame
from pygame.sprite import Sprite, Group
from setting import Setting


class Ship(): # 飞船

    def __init__(self, setting, screen):
        self.screen = screen

        self.speed = setting.ship_speed_factor

        self.image = pygame.image.load("images\ship.bmp")
        self.rect = self.image.get_rect()
        self.screen_rect = self.screen.get_rect()

        self.centery = float(self.screen_rect.centery)
        self.right = float(self.screen_rect.right)

        self.rect.centery = self.centery
        self.rect.right = self.right

        self.moving_up = False
        self.moving_down = False

    # 更新飞船位置
    def update(self):
        if self.moving_up and self.rect.top > self.screen_rect.top:
            self.centery -= self.speed
        if self.moving_down and self.rect.bottom < self.screen_rect.bottom:
            self.centery += self.speed
        
        self.rect.centery = self.centery


    def biltme(self):
        self.screen.blit(self.image,self.rect)



class Bullet(Sprite): # 子弹
    
    def __init__(self,setting,screen,ship):
        super().__init__()
        self.color = setting.bullet_color
        self.speed = setting.bullet_speed_factor
        self.screen = screen
        self.rect = pygame.Rect(0,0,setting.bullet_width,setting.bullet_height)

        self.rect.centery = ship.rect.centery
        self.rect.left = ship.rect.left

        self.x = float(self.rect.x)

    def update(self):
        self.x -= self.speed
        self.rect.x = self.x
    
    def draw(self):
        pygame.draw.rect(self.screen,self.color,self.rect)

def check_event(setting,screen,ship,bullts):
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            sys.exit()
        if event.type == pygame.KEYDOWN:
            if event.key == pygame.K_UP:
                ship.moving_up = True
            elif event.key == pygame.K_DOWN:
                ship.moving_down = True
            elif event.key == pygame.K_SPACE:
                if len(bullts) < setting.bullet_allowed:
                    new_bullet = Bullet(setting,screen,ship)
                    bullts.add(new_bullet)
                
        elif event.type == pygame.KEYUP:
            if event.key == pygame.K_UP:
                ship.moving_up = False
            elif event.key == pygame.K_DOWN:
                ship.moving_down = False
            
            


# 重绘显示 所有的显示操作都在这里
def update_screen(setting,screen,ship,bullets):
    screen.fill(setting.bg_color)
    ship.biltme()
    for bullet in bullets.sprites():
        bullet.draw()
    pygame.display.update()

def update_builts(bullets):
    bullets.update()

    for bullet in bullets.copy():
        if bullet.rect.left < 0:
            bullets.remove(bullet)
    print("当前子弹：",len(bullets))

def start():
    pygame.init()
    setting = Setting()
    screen = pygame.display.set_mode(
        (setting.screen_width, setting.screen_height))
    pygame.display.set_caption("外星人大战2")

    ship = Ship(setting,screen)
    bullets = Group()

    while True:
        check_event(setting,screen,ship,bullets)
        ship.update()
        update_builts(bullets)
        update_screen(setting,screen,ship,bullets)
         
start()
