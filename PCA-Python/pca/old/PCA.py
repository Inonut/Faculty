
person = [{
    'name': 'Gogu',
    'age': 5
}, {
    'name': 'Gogur',
    'age': 5
}]

print(list(map(lambda a, b: a['name'] + b['name'], person, person)))

print("Good bye!")
