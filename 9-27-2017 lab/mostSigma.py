# Alan Koval, 9/27/2017 (koval048@umn.edu)
# assignment: https://ay17.moodle.umn.edu/pluginfile.php/1361675/mod_resource/content/4/lab3.html

def mostHelper(P, s):
	if len(s) == 1:
		return 1 if P(s[0]) else -1
	return mostHelper(P, [s[0]]) + mostHelper(P, s[1:])

def most(P,s):
	return False if len(s) == 0 else mostHelper(P, s) > 0

def sigma(F, a, b):
	if a > b:
		return 0
	if a == b:
		return F(a)
	return sigma(F, a, a) + sigma(F, a + 1, b)

# tests:

def odd(N):
  return N % 2 != 0

print(most(odd, []))         #  False    2 points
print(most(odd, [0]))        #  False    2 points
print(most(odd, [1]))        #  True     2 points
print(most(odd, [1, 2]))     #  False    2 points
print(most(odd, [1, 2, 3]))  #  True     2 points

#  SQR. Assume N is a number. Return N times N.

def sqr(N):
  return N * N

print(sigma(sqr, 0, 0))    #  0          2 points
print(sigma(sqr, 1, 0))    #  0          2 points
print(sigma(sqr, 0, 4))    #  30         2 points
print(sigma(sqr, 1, 1))    #  1          2 points
print(sigma(sqr, 2, 100))  #  338349     2 points

'''
program output:
False
False
True
False
True
0
0
30
1
338349
'''
