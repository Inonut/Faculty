from functools import reduce


def dobanda_compusa(S0, p, t):
    i = p / 100
    return S0 * (pow(1 + i, t) - 1)


def capitalul_acumulat(S0, p, t):
    i = p / 100
    return S0 * pow(1 + i, t)


def suma_dobanda_compusa(St, p, t):
    i = p / 100
    return St / pow(1 + i, t)


def procentul_anual(S0, t, D):
    St = S0 + D
    return (pow(St / S0, 1 / t) - 1) * 100


def dobanda_compusa_rationala(S0, p, y, m):
    i = p / 100
    return S0 * pow(1 + i, y) * (1 + i * m / 12)


def dobanda_compusa_comerciala(S0, p, y, m):
    i = p / 100
    return S0 * pow(1 + i, y + m / 12)


def dobanda_compusa_rationala_procent_variabil(S0, *pv):
    D = S0 * (reduce(lambda p, x: p * dobanda_compusa_rationala(1, x[0], x[1], x[2]), pv, 1) - 1)
    return D, S0 + D


def dobanda_compusa_comerciala_procent_variabil(S0, *pv):
    D = S0 * (reduce(lambda p, x: p * dobanda_compusa_comerciala(1, x[0], x[1], x[2]), pv, 1) - 1)
    return D, S0 + D


print("""1. dobanda compusa 
    D = {0:.4f} u.m.
""".format(dobanda_compusa(10000, 16, 3)))

print("""2. capitalul acumulat 
    St = {0:.4f} u.m.
""".format(capitalul_acumulat(2500, 30, 5)))

print("""3. suma depusa de o persoană la banca, ı̂n regim de dobanda compusa 
    St = {0:.4f} u.m.
""".format(suma_dobanda_compusa(3456, 20, 3)))

print("""4. procentul anual 
    p = {0:.4f}%
""".format(procentul_anual(1000, 4, 800)))

print("""5. dobanda compusa rationala 
    St = {0:.4f} u.m.
""".format(dobanda_compusa_rationala(6000, 21, 1, 6)))

print("""6. dobanda compusa comerciala 
    St = {0:.4f} u.m.
""".format(dobanda_compusa_comerciala(6000, 21, 1, 6)))

print("""7. dobanda compusa rationala procent variabil 
    D = {0:.4f} u.m.
    St = {1:.4f} u.m.
""".format(*dobanda_compusa_rationala_procent_variabil(2000, (21, 1, 6), (25, 2, 4))))

print("""8. dobanda compusa comerciala procent variabil 
    D = {0:.4f} u.m.
    St = {1:.4f} u.m.
""".format(*dobanda_compusa_comerciala_procent_variabil(2000, (21, 1, 6), (25, 2, 4))))
