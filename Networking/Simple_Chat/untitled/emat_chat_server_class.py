import socket


class cServer:
    def __init__(self):
        """Builds and constructs a server with a host, port, buffer_size, and bind it"""
        self.host = socket.gethostname()  # 192.168.56.1
        self.port = 33000
        self.buffer_size = 1024
        self.address = (self.host, self.port)



my_server = cServer()

server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.bind(my_server.address)

client, client_address = server.accept()
# client_identification = self.server.recv(self.buffer_size)
print("%s has connected.\n" % (str(client_address)))
client.send(bytes("Welcome!   Type your NAME and press enter!", "utf8"))

server.close()
