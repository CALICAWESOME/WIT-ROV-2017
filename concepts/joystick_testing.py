import pygame
import os
from time import sleep

AXIS_DECIMAL_PLACES = 3


def setup_joysticks():
    pygame.joystick.init()
    j = [pygame.joystick.Joystick(_) for _ in range(pygame.joystick.get_count())]
    for _ in j:
        _.init()
    return j


def main():
    pygame.init()
    j = setup_joysticks()[0]    # assuming a joystick is plugged in
    print(j.get_id())
    while j:
        pygame.event.pump()
        a = [str(round(j.get_axis(_), AXIS_DECIMAL_PLACES)) for _ in range(j.get_numaxes())]
        print('\t\t'.join(a)    # print raw axis values in a rather disgusting manner
        sleep(0.05)


if __name__ == '__main__':
    main()
