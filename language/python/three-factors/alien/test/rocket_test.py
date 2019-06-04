import sys
import pygame
# 火箭移动练习
class Rocket():
    def __init__(self,screen):
        self.screen = screen
        self.image = pygame.image.load('images/ship.bmp')
        self.rect = self.image.get_rect()
        self.screen_rect = self.screen.get_rect()

        self.moving_top = False
        self.moving_bottom = False
        self.moving_left = False
        self.moving_right = False

        self.rect.centerx = self.screen_rect.centerx
        self.rect.centery = self.screen_rect.centery

        self.centerx = float(self.rect.centerx)
        self.centery = float(self.rect.centery)

    def biltme(self):
        self.screen.blit(self.image,self.rect)

    def update(self):
        
        if self.moving_top and self.rect.top > self.screen_rect.top:
            self.centery -= 1
        elif self.moving_bottom and self.rect.bottom < self.screen_rect.bottom:
            self.centery += 1
        elif self.moving_left and self.rect.left > self.screen_rect.left:
            self.centerx -= 1
        elif self.moving_right and self.rect.right < self.screen_rect.right:
             self.centerx += 1
        
        self.rect.centerx = self.centerx
        self.rect.centery = self.centery

def start():

    pygame.init()
    screen =  pygame.display.set_mode((600,600))
    pygame.display.set_caption("火箭")

    rocket = Rocket(screen)
    while True:
        
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                sys.exit()
            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_UP:
                    rocket.moving_top = True
                elif event.key == pygame.K_DOWN:
                    rocket.moving_bottom = True
                elif event.key == pygame.K_RIGHT:
                    rocket.moving_right = True        
                elif event.key == pygame.K_LEFT:
                    rocket.moving_left = True
            elif event.type == pygame.KEYUP:
                if event.key == pygame.K_UP:
                    rocket.moving_top = False
                elif event.key == pygame.K_DOWN:
                    rocket.moving_bottom = False
                elif event.key == pygame.K_RIGHT:
                    rocket.moving_right = False        
                elif event.key == pygame.K_LEFT:
                    rocket.moving_left = False
        
        rocket.update()
        screen.fill((123,123,123))
        rocket.biltme()
        pygame.display.update()


start()