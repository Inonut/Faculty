def finala(T, n, r, p):
    i = p / 100
    u = 1 + i
    return T * (pow(u, n) - 1) / i

def actuala(T, n, r, p):
    i = p / 100
    u = 1 + i
    v = 1 / u
    return T * (1 - pow(v, n)) / i * pow(v, r)


print('valoarea finala {:}'.format(finala(200, 5, 2, 20)))
print('valoarea actuala {:}'.format(actuala(200, 5, 2, 20)))