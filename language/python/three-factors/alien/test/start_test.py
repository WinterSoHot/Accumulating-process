import sys
import pygame
from pygame.sprite import Sprite,Group
from random import randint

class Star(Sprite):

    def __init__(self,screen):
        super().__init__()
        self.screen = screen
        self.image = pygame.image.load('images\star.bmp')
        self.rect = self.image.get_rect()

        self.rect.x = self.rect.width
        self.rect.y = self.rect.height

        self.x = float(self.rect.x)

def creat_stars(screen,stars):
    if len(stars) > 2000:
        return None
    for num in range(200):
        star = Star(screen)
        space = float(star.rect.width) / 2
        x =  randint(0,800)
        y =  randint(0,600)
        star.rect.x = x
        star.rect.y = y
        stars.add(star)

def check_event():
    for event in pygame.event.get():
        if event.type == pygame.KEYDOWN:
            if event.key == pygame.K_q:
                sys.exit()
    pass

def update_screnn(screen,stars):
    screen.fill((255, 255, 255))
    stars.draw(screen)
    pygame.display.update()

    pass

def start_game():

    pygame.init()
    screen = pygame.display.set_mode((800,600))
    pygame.display.set_caption("满天星")

    stars = Group()

    while True:
        # 检测事件
        check_event()
        creat_stars(screen,stars)
        # 刷新屏幕
        update_screnn(screen,stars)

    pass

start_game()