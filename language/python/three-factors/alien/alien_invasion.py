import pygame
from setting import Setting
from ship import Ship
from alien import Alien
import game_function as gf
from pygame.sprite import Group
from game_state import GameState
from button import Button
from scorebroad import ScoreBoard

def run_game():
    pygame.init()
    ai_setting = Setting()
    screen = pygame.display.set_mode((ai_setting.screen_width,ai_setting.screen_height))
    pygame.display.set_caption("外星人大战")
    ship = Ship(ai_setting,screen)
    # 创建存储子弹的编组
    bullets = Group()
    #创建一个外星人
    aliens = Group()
    gameState = GameState(ai_setting)
    sb = ScoreBoard(ai_setting,screen,gameState)
    play_button = Button(ai_setting,screen,'Play')

    gf.create_fleet(ai_setting,screen,ship,aliens)

    while True:
        
        gf.check_events(ai_setting,gameState,aliens, screen, ship, bullets,play_button,sb)
        if gameState.game_active:
            ship.update()
            gf.update_bullets(ai_setting,screen,gameState,sb,ship,aliens,bullets)
            gf.update_aliens(ai_setting,gameState,sb,screen,ship,aliens,bullets)
        gf.update_screen(ai_setting,gameState,screen,ship,aliens,bullets,play_button,sb)

# 启动游戏
run_game()

