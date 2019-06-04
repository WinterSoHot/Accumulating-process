import sys
import pygame
from pygame.sprite import Sprite

class Alien(Sprite): # 外星人

    def __init__(self,setting,screen):
        super(Alien,self).__init__()
        self.screen = screen
        self.setting = setting
        self.image = pygame.image.load("images/alien.bmp")
        self.rect = self.image.get_rect()
        self.rect.x = self.rect.width
        self.rect.y = self.rect.height
        self.x = float(self.rect.x)

        self.speed = setting.alien_speed_factor

    def update(self):
        self.x += self.setting.fleet_direction * self.speed
        self.rect.x = self.x

    def check_edge(self):
        if self.rect.right >= self.screen.get_rect().right:
            return True
        elif self.rect.left <= 0:
            return True
        else:
            return False
            

    def blitme(self):
        self.screen.blit(self.image,self.rect)