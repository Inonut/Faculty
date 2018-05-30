import zerorpc
import gevent

class State:
    NORMAL = 'Normal',
    ELECTION = 'Election',
    REORGANIZATION = 'Reorganization'
    DOWN = 'Down'


class Bully():
    def __init__(self, addr, config_file='server_config'):

        self.state = State.NORMAL
        self.coordinator = 0
        self.h = -1
        self.Up = []
        self.check_servers_greenlet = None
        self.addr = addr
        self.servers = []

        f = open(config_file, 'r')
        for line in f.readlines():
            self.servers.append(line.rstrip())


        print('My addr: ', self.addr)
        print('Server list: ', (str(self.servers)))

        self.n = len(self.servers)
        self.connections = []

        for i, server in enumerate(self.servers):
            if server == self.addr:
                self.i = i
                self.connections.append(self)
            else:
                c = zerorpc.Client(timeout=2)
                c.connect('tcp://' + server)
                self.connections.append(c)

    def are_you_there(self):
        return True

    def are_you_normal(self):
        return self.state == State.NORMAL

    def halt(self, j):
        self.state = State.ELECTION
        self.h = j

    def new_coordinator(self, j):
        print('call new_coordinator')
        if self.h == j and self.state == State.ELECTION:
            self.coordinator = j
            self.state = State.REORGANIZATION

    def ready(self, j):
        print('call ready')
        if self.coordinator == j and self.state == State.REORGANIZATION:
            self.state = State.NORMAL

    def election(self):
        print('Check the states of higher priority nodes:')

        for i, server in enumerate(self.servers[self.i + 1:]):
            try:
                self.connections[self.i + 1 + i].are_you_there()
                if self.check_servers_greenlet is None:
                    self.coordinator = self.i + 1 + i
                    self.state = State.NORMAL
                    self.check_servers_greenlet = self.pool.spawn(self.check())
                return
            except zerorpc.TimeoutExpired:
                print(server, ' Timeout!')

        print('halt all lower priority nodes including this node:')
        self.halt(self.i)
        self.Up = []
        for i, server in enumerate(self.servers[self.i::-1]):
            try:
                self.connections[i].halt(self.i)
            except zerorpc.TimeoutExpired:
                print(server, ' Timeout!')
                continue
            self.Up.append(self.connections[i])

        # reached 'election point',inform nodes of new coordinator
        print('inform nodes of new coordinator:')
        self.coordinator = self.i
        self.state = State.REORGANIZATION
        for j in self.Up:
            try:
                j.new_coordinator(self.i)
            except zerorpc.TimeoutExpired:
                print('Timeout! Election will be restarted.')
                self.election()
                return

        # Reorganization
        for j in self.Up:
            try:
                j.ready(self.i)
            except zerorpc.TimeoutExpired:
                print('Timeout!')
                self.election()
                return

        self.state = State.NORMAL
        print(self.servers[self.i], ' Starting ZeroRPC Server')
        self.check_servers_greenlet = self.pool.spawn(self.check())

    def recovery(self):
        self.h = -1
        self.election()

    def check(self):
        while True:
            gevent.sleep(2)
            if self.state == State.NORMAL and self.coordinator == self.i:
                for i, server in enumerate(self.servers):
                    if i != self.i:
                        try:
                            ans = self.connections[i].are_you_normal()
                            print(server, ' : are_you_normal = ', ans)
                        except zerorpc.TimeoutExpired:
                            print('%s Timeout!' % server)
                            continue

                        if not ans:
                            self.election()
                            return
            elif self.state == State.NORMAL and self.coordinator != self.i:
                print('check coordinator\'s state')
                try:
                    result = self.connections[self.coordinator].are_you_there()
                    print(self.servers[self.coordinator], ' : are_you_there = ', result)
                except zerorpc.TimeoutExpired:
                    print('coordinator down, rasie eleciton.')
                    self.timeout()

    def timeout(self):
        if self.state == State.NORMAL or self.state == State.REORGANIZATION:
            try:
                self.connections[self.coordinator].are_you_there()
            except zerorpc.TimeoutExpired:
                print(self.servers[self.coordinator], ' Timeout!')
                self.election()
        else:
            self.election()

    def start(self):
        self.pool = gevent.pool.Group()
        self.recovery_greenlet = self.pool.spawn(self.recovery)


def main():
    addr = '127.0.0.1:9001'
    bully = Bully(addr)
    s = zerorpc.Server(bully)
    s.bind('tcp://' + addr)
    bully.start()
    # Start server
    print(addr, ' Starting ZeroRPC Server')
    s.run()


if __name__ == '__main__':
    main()