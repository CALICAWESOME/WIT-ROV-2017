import pygame

AXIS_DECIMAL_PLACES = 3


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
        d['axis_{}'.format(_)] = j.get_axis(_)
    for _ in range(j.get_numhats()):
        d['hat_{}'.format(_)] = j.get_hat(_)
    for _ in range(j.get_numbuttons()):
        d['button_{}'.format(_)] = j.get_button(_)
    return d


def main():
    pygame.init()
    clock = pygame.time.Clock()
    j = setup_joysticks()[0]    # assuming a joystick is plugged in
    print(j.get_id())
    while j:
        pygame.event.pump()
        print(get_joystick_data(j))
        clock.tick(200)


if __name__ == '__main__':
    main()
