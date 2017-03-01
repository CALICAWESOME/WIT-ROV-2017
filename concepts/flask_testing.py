from flask import Flask
flask_testing = Flask(__name__)


@flask_testing.route('/')
def hello_world():
    return 'Hello, World!'


if __name__ == "__main__":
    flask_testing.run()
