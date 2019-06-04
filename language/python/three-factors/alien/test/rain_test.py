import sys
import pygame
from pygame.sprite import Sprite, Group
import random

class Rain(Sprite):

    def __init__(self, screen):
        super().__init__()
        self.screen = screen
        self.image = pygame.image.load("images/water.bmp")
        self.rect = self.image.get_rect()
        self.rect.x = random.random()*800 
        self.rect.y = 0
        self.screen_rect = self.screen.get_rect()
        self.y = float(self.rect.y)
        self.speed = random.randint(3,8)

    def update(self):
        self.y += self.speed
        self.rect.y = self.y

    def blite(self):
        self.screen.blit(self.image, self.rect)


def create_rain(screen, rains):
    count = 300 - len(rains)
    while count>0:
        rain = Rain(screen)
        rains.add(rain)
        count-=1
    


def check_rains(screen, rains):
    for rain in rains.copy():
        if rain.rect.y > screen.get_rect().height:
            rains.remove(rain)
    create_rain(screen,rains)


def update_rain(screen, rains):
    rains.update()
    check_rains(screen, rains)


def update_screen(screen, rains):
    screen.fill((255, 255, 255))
    rains.draw(screen)
    pygame.display.update()


def start_game():
    pygame.init()
    screen = pygame.display.set_mode((800, 600))
    pygame.display.set_caption("雨滴")
    rains = Group()
    create_rain(screen, rains)
    while True:
        for event in pygame.event.get():
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_q:
                    sys.exit()
            elif event.type == pygame.QUIT:
                sys.exit()

        update_rain(screen, rains)
        update_screen(screen, rains)


start_game()
