# https://www.ibm.com/support/knowledgecenter/es/SSTLXK_8.5.7/com.ibm.wbpm.admin.doc/topics/managing_twks_servers_F.html

import socket
from threading import Thread
from time import strftime


clients = {}  # key:socket value:names
clients_by_name = {}  # key:name value:socket
addresses = {}  # key:socket value:address
paired_clients = {}  # key:socket A value:socket B (pairwise)

#HOST = socket.gethostname() 
HOST = "192.168.0.4"  # 192.168.56.1
#HOST = "10.0.0.19"
PORT = 10023
BUFFER_SIZE = 1024
ADDR = (HOST, PORT)
SERVER = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
SERVER.bind(ADDR)


def get_clients():
    return clients.values()


def accept_incoming_connections():
    """Sets up handling for incoming clients."""
    while True:
        client, client_address = SERVER.accept()
        print("%s has connected.\n" % (str(client_address)))
        addresses[client] = client_address
        Thread(target=handle_client, args=(client,)).start()


def handle_client(client):  # Takes client socket as argument.
    """Handles a single client connection."""
    name = client.recv(BUFFER_SIZE).decode("utf8")
    welcome = 'Hi %s! If you ever want to quit, type {QqQ} to exit.' % name
    print("Hi %s" % name)
    client.send(bytes(welcome, "utf8"))

    msg = "%s has joined the chat!" % name
    broadcast(bytes(msg, "utf8"))

    clients[client] = name
    clients_by_name[name] = client

    while True:
        msg = client.recv(BUFFER_SIZE)
        if msg == bytes("QqQ", "utf8"):
            client.send(bytes("QqQ", "utf8"))
            client.close()
            del clients[client]
            print("%s has left the chat." % name)
            broadcast(bytes("%s has left the chat." % name, "utf8"))
            break
        elif msg == bytes("List", "utf8"):
            for user in list(clients.values()):
                client.send(bytes(user, "utf8"))
        elif msg == bytes("CcC", "utf8"):
            client.send(bytes("Enter name:", "utf8"))
        elif msg.decode("utf8") in list(clients_by_name.keys()):
            user_A = clients[client]
            user_B = msg.decode("utf8")
            client_B = clients_by_name[user_B]
            paired_clients[client] = clients_by_name[user_B]
            paired_clients[client_B] = client
            client.send(bytes("You are connected with %s" % user_B, "utf8"))
            client_B.send(bytes("You are connected with %s" % user_A, "utf8"))
        else:
            broadcast(msg, name + ": ")


def broadcast(msg, prefix=""):  # prefix is for name identification.
    """Broadcasts a message to all the clients."""
    for sock in clients:
        sock.send(bytes(prefix, "utf8")+msg)


if __name__ == "__main__":
    SERVER.listen(20)  # Listens for 20 connections at max.

    print("Server started at", strftime("%d/%m/%y - %H:%M:%S"), "in", SERVER.getsockname(), "socket.")
    print("\nWaiting for connection...")

    ACCEPT_THREAD = Thread(target=accept_incoming_connections)
    ACCEPT_THREAD.start()  # Starts the infinite loop.
    ACCEPT_THREAD.join()
    SERVER.close()

