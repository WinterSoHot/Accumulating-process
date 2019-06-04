import pygame
import sys
from bullet import Bullet
from alien import Alien
from time import sleep


def check_keydown_events(event, ai_setting, screen, ship, bullets):
    if event.key == pygame.K_RIGHT:
        ship.moving_right = True
    elif event.key == pygame.K_LEFT:
        ship.moving_left = True
    elif event.key == pygame.K_SPACE:
        fire_bullet(ai_setting, screen, ship, bullets)
    elif event.key == pygame.K_q:
        sys.exit()


def check_keyup_events(event, ship):
    if event.key == pygame.K_RIGHT:
        ship.moving_right = False
    elif event.key == pygame.K_LEFT:
        ship.moving_left = False


def fire_bullet(ai_setting, screen, ship, bullets):
    if len(bullets) < ai_setting.bullet_allowed:
        new_bullet = Bullet(ai_setting, screen, ship)
        bullets.add(new_bullet)


def check_events(ai_setting,gameState,aliens, screen, ship, bullets,play_button,sb):
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            print("退出游戏")
            sys.exit()
        elif event.type == pygame.KEYDOWN:
            check_keydown_events(event, ai_setting, screen, ship, bullets)
        elif event.type == pygame.KEYUP:
            check_keyup_events(event, ship)
        elif event.type == pygame.MOUSEBUTTONDOWN:
            mouse_x,mouse_y = pygame.mouse.get_pos()
            check_play_button(ai_setting,gameState,aliens, screen, ship, bullets,play_button,mouse_x,mouse_y,sb)

def check_play_button(ai_setting,gameState,aliens, screen, ship, bullets,play_button, mouse_x, mouse_y,sb):
    if play_button.rect.collidepoint(mouse_x,mouse_y) and not gameState.game_active:
        gameState.reset_stats()
        gameState.game_active = True
        ai_setting.initialize_dynamic_settings()
        pygame.mouse.set_visible(False)

        # 重置记分牌图像
        sb.prep_score()
        sb.prep_high_score()
        sb.prep_level()
        sb.prep_ships()

        bullets.empty()
        aliens.empty()

    

        create_fleet(ai_setting,screen,ship,aliens)
        ship.center_ship()



def update_bullets(ai_setting, screen,gameState,sb, ship, aliens, bullets):
    # 执行每个子弹的update 方法
    bullets.update()
    # 删除消失的子弹
    for bullet in bullets.copy():
        if bullet.rect.bottom < 0:
            bullets.remove(bullet)
    check_bullet_alien_collisions(ai_setting, screen,gameState,sb, ship, aliens, bullets)


def check_bullet_alien_collisions(ai_setting, screen,gameState,sb, ship, aliens, bullets):
    collections = pygame.sprite.groupcollide(bullets, aliens, True, True)
    if collections:
        for aliens in collections.values():
            gameState.score += ai_setting.alien_points * len(aliens)
            sb.prep_score()
    if len(aliens) == 0:
        bullets.empty()
        check_high_score(gameState,sb)
        gameState.level +=1
        sb.prep_level()
        ai_setting.increase_speed()
        create_fleet(ai_setting,screen,ship,aliens)

def update_screen(ai_setting,gameState, screen, ship, aliens, bullets,play_button,sb):
         # 填充背景色
    screen.fill(ai_setting.bg_color)

    # 重绘子弹
    for bullet in bullets.sprites():
        bullet.draw_bullet()
    # 绘制飞船
    ship.blitme()
    aliens.draw(screen)
    sb.show_score()
    if not gameState.game_active:
        play_button.draw_button()
    pygame.display.update()

# 获取一行多少外星人


def get_number_alien(ai_setting, alien_width):
    available_space_x = ai_setting.screen_width - 2 * alien_width
    return int(available_space_x / (2 * alien_width))


def get_number_rows(ai_setting, ship_height, alien_height):
    available_space_y = ai_setting.screen_height - 3 * alien_height - ship_height
    number_rows = int(available_space_y / (2*alien_height))
    return number_rows

# 创建外星人


def create_alien(ai_setting, screen, aliens, alien_number, row):
    alien = Alien(ai_setting, screen)
    alien_width = alien.rect.width
    alien.x = alien_width + 2 * alien_width * alien_number
    alien.rect.x = alien.x
    alien.rect.y = alien.rect.height + 2*alien.rect.height * row
    aliens.add(alien)


def create_fleet(ai_setting, screen, ship, aliens):
    alien = Alien(ai_setting, screen)
    number_alien_x = get_number_alien(ai_setting, alien.rect.width)
    number_rows = get_number_rows(
        ai_setting, ship.rect.height, alien.rect.height)
    for row in range(number_rows):
        for alien_number in range(number_alien_x):
            create_alien(ai_setting, screen, aliens, alien_number, row)


# 检查是否撞到边缘
def check_fleet_edges(setting,aliens):
    for alien in aliens:
        if alien.check_edge():
            change_fleet_direction(setting,aliens)
            break
            

def change_fleet_direction(setting,aliens):
    for alien in aliens:
        alien.rect.y += setting.fleet_drop_speed
    setting.fleet_direction *= -1

def update_aliens(setting,gameState,sb,screen,ship,aliens,bullets):
    check_fleet_edges(setting,aliens)
    aliens.update()
    # 检测外星人和飞船之间的碰撞
    if pygame.sprite.spritecollideany(ship, aliens):
        print("Ship hit!!!")
        ship_hit(setting, gameState,sb, screen, ship, aliens, bullets)
    check_alien_bottom(setting,gameState,sb,screen,ship,aliens,bullets)

def check_alien_bottom(setting,gameState,sb,screen,ship,aliens,bullets):
    screen_rect = screen.get_rect()
    for alien in aliens.sprites():
        if alien.rect.bottom >= screen_rect.bottom:
            ship_hit(setting, gameState, sb,screen, ship, aliens, bullets)
            break
    

def ship_hit(setting, gameState, sb,screen, ship, aliens, bullets):
    if gameState.ships_left>0:
        gameState.ships_left -=1
        sb.prep_ships()
        aliens.empty()
        bullets.empty()
        ship.center_ship()
        sleep(1)
    else:
        gameState.game_active = False
        pygame.mouse.set_visible(True)

def check_high_score(gameState,sb):
    if gameState.score > gameState.high_score:
        gameState.high_score = gameState.score
        sb.prep_high_score()