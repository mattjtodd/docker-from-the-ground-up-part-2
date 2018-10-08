from flask import Flask
app = Flask(__name__)

import pika

@app.route("/greeting")
def greeting():

    credentials = pika.PlainCredentials('rabbitmq', 'password')
    connection = pika.BlockingConnection(pika.ConnectionParameters('rabbit', 5672, '/', credentials))
    channel = connection.channel()
    channel.queue_declare(queue='hello')

    channel.basic_publish(exchange='', routing_key='hello', body='Hello World!')

    return "Hello World!"