import sys
import pygame
from pygame.sprite import Sprite,Group
import random

class Square(Sprite):
    def __init__(self,screen):
        super().__init__()
        self.screen =screen
        self.rect = pygame.Rect(0,0,40,40)
        self.color = (234,123,123)
        self.screen_rect = self.screen.get_rect()
        self.rect.top = self.screen_rect.top
        self.moving_right = False
        self.moving_left = False
        self.speed = 0.3
        self.rect.centerx = random.uniform(0,self.screen_rect.width)
        self.y = float(self.rect.y)
    def update(self):
        self.y += self.speed
        self.rect.y = self.y
    
    def draw(self):
        pygame.draw.rect(self.screen,self.color,self.rect)

class Ship(Sprite):

    def __init__(self,screen):
        super().__init__()
        self.screen =screen
        self.rect = pygame.Rect(0,0,50,10)
        self.color = (123,123,123)
        self.screen_rect = self.screen.get_rect()
        self.rect.centerx = self.screen_rect.centerx
        self.rect.bottom = self.screen_rect.bottom - 40

        self.moving_right = False
        self.moving_left = False
        self.speed = 1
        
        self.centerx = float(self.rect.centerx)
    def update(self):
        if self.moving_right:
            self.centerx += self.speed
        elif self.moving_left:
            self.centerx -= self.speed
        self.rect.centerx = self.centerx
        if self.rect.left < 0:
            self.rect.left = 0
        elif self.rect.right > self.screen_rect.right:
            self.rect.right = self.screen_rect.right
            
    
    def draw(self):
        pygame.draw.rect(self.screen,self.color,self.rect)


def update_ship(ship):
    ship.update()
    
def update_screen(screen,ship,square):
    screen.fill((255,255,255))
    ship.draw()
    square.draw()
    pygame.display.update()

def update_square(square,ship):
    square.update()
    if pygame.sprite.collide_rect(square,ship) or square.rect.top > square.screen_rect.bottom:
        square.rect.centerx = random.uniform(0,square.screen_rect.width)
        square.rect.bottom = square.screen_rect.top
        square.y = square.rect.y
        


def check_event(screen,ship):
     for event in pygame.event.get():
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_q:
                    sys.exit()
                elif event.key == pygame.K_RIGHT:
                    ship.moving_right = True
                elif event.key == pygame.K_LEFT:
                    ship.moving_left = True
            elif event.type == pygame.QUIT:
                sys.exit()
            elif  event.type == pygame.KEYUP:
                if event.key == pygame.K_RIGHT:
                    ship.moving_right = False
                elif event.key == pygame.K_LEFT:
                    ship.moving_left = False
            

def start():
    pygame.init()
    screen = pygame.display.set_mode((800, 600))
    pygame.display.set_caption("雨滴")
    ship = Ship(screen)
    square = Square(screen)
    while True:
        check_event(screen,ship)
        update_square(square,ship)
        update_ship(ship)
        update_screen(screen,ship,square)

start()