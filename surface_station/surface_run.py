import pygame
from flask import Flask, render_template
surf = Flask(__name__)

AXIS_NUMDIGITS = 5
CLOCK = None
JOYSTICKS = []


def setup_joysticks():
    """returns list of Joystick objects, one for each joystick plugged in"""
    pygame.joystick.init()
    j = [pygame.joystick.Joystick(_) for _ in range(pygame.joystick.get_count())]
    for _ in j:
        _.init()
    return j


def get_joystick_data(j):
    """returns dictionary of all axis hat and button states of Joystick j"""
    d = {}
    for _ in range(j.get_numaxes()):
        d['axis_{}'.format(_)] = round(j.get_axis(_), 5)
    for _ in range(j.get_numhats()):
        d['hat_{}'.format(_)] = j.get_hat(_)
    for _ in range(j.get_numbuttons()):
        d['button_{}'.format(_)] = j.get_button(_)
    return d


def setup():
    global CLOCK
    global JOYSTICKS
    pygame.init()
    CLOCK = pygame.time.Clock()
    JOYSTICKS = setup_joysticks()


def loop():
    pygame.event.pump()
    print(get_joystick_data(JOYSTICKS[0]))
    CLOCK.tick(200)


@surf.route('/')
def dataview():
    loop()
    return render_template('index.html', data=get_joystick_data(JOYSTICKS[0]))


if __name__ == '__main__':
    setup()
    surf.run(port=8000, debug=True)
