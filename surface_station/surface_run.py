import pygame
from flask import Flask, render_template
surf = Flask(__name__)


class SurfaceStation:

    _axis_numdigits = 5
    clock = None
    joysticks = []

    def __init__(self):
        pygame.init()
        self.clock = pygame.time.Clock()
        self.joysticks = self._setup_joysticks()

    @property
    def axis_numdigits(self):
        return self._axis_numdigits

    @staticmethod
    def _setup_joysticks():
        """returns list of Joystick objects, one for each joystick plugged in"""
        pygame.joystick.init()
        j = [pygame.joystick.Joystick(_) for _ in range(pygame.joystick.get_count())]
        for _ in j:
            _.init()
        return j

    def get_joystick_data(self):
        """returns dictionary of all axis hat and button states of Joystick j"""
        _ = []
        for j in self.joysticks:
            d = {}
            for a in range(j.get_numaxes()):
                d['axis_{}'.format(a)] = round(j.get_axis(a), self._axis_numdigits)
            for h in range(j.get_numhats()):
                d['hat_{}'.format(h)] = j.get_hat(h)
            for b in range(j.get_numbuttons()):
                d['button_{}'.format(b)] = j.get_button(b)
            _.append(d)
        return _

    def tick(self):
        pygame.event.pump()
        print(self.get_joystick_data())
        self.clock.tick(200)


@surf.route('/')
def dataview():
    s = SurfaceStation()
    s.tick()
    return render_template('index.html', data=s.get_joystick_data()[0])


if __name__ == '__main__':
    surf.run(port=8000, debug=True)
