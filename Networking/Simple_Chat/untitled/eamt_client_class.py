class cClient:
    """Defines a client"""
    def __init__(self, name, address):
        self.name = name
        self.address = address

    def get_name(self):
        return self.name

    def get_address(self):
        return self.address

    def __str__(self):
        return "%s, %s" % (self.name, self.address)
