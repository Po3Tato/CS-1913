# Alan Koval
# Project #1

import urllib.request

class Random:
    def __init__(self, seed):
        self.__seed = seed

    def next(self, range):
        self.__seed = (7**5 * self.__seed) % (2**31 - 1)
        return self.__seed % range

    def choose(self, characters):
        return characters[next(len(characters))]

class Words:
    def __init__(self, seed):
        self.__random = Random(seed)
        self.__first = ''
        self.__follows = {}
        for letter in 'abcdefghijklmnopqrstuvwxyz':
            self.__follows[letter] = ''
            self.__follows[letter.upper()] = ''

    def add(self, word):
        self.__first += word[0]
        for i in range(1, len(word)):
            self.__follows[word[i - 1]] += word[i]

    def make(self, size):
        result = ''
        while len(result) < size:
            if len(result) == 0 or len(self.__follows[result[-1]]) == 0:
                result += self.__first[self.__random.next(len(self.__first))]
            else:
                followDict = self.__follows[result[-1]]
                result += followDict[self.__random.next(len(followDict))]
        return result

w = Words(9)
# read a list of 10000 english words from a url
data = urllib.request.urlopen('https://raw.githubusercontent.com/first20hours/google-10000-english/master/google-10000-english.txt')
for line in data:
    w.add(line.decode('utf-8').strip())

w2 = Words(9)
for s in 'Apple Banana Cab Dapper Delta Epsilon Freak Gigantic Hello Julian Koala Lua Money Nothing Other People Quiet Rest Strengths Triathalon'.lower().split(' '):
    w2.add(s)

for i in range(8):
    for j in range(4):
        print(w.make(5+j))
        print(w2.make(5+j))

'''
output:
iempo
dakoa
blsuch
bakoan
diblter
rieriel
pupisust
trepstha
pinth
lullu
lacter
mopepp
nntesev
thstatr
tiurdapu
kotheapl
rtess
sthst
scintr
hantre
mavaray
capereo
datrdold
onapelta
ronst
deril
dieech
gtappe
litanar
ngabali
coreleno
dakonest
tedst
freon
sshiou
heyrig
laiside
neykopl
wregrdge
fricalua
dgera
juian
comedv
hestri
anelebe
dererer
meloreng
bangtalo
stste
onane
dedely
frilea
puriecr
juluigt
tennytyp
quanetre
aitil
thstr
orroli
juietr
hazetis
frerelo
pizintib
onapltha
'''
