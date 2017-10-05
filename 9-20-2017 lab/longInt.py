# Alan Koval, 9/20/2017, koval048@umn.edu
# Lab #2, assignment: https://ay17.moodle.umn.edu/pluginfile.php/1322817/mod_resource/content/2/lab2.html

import re

class Zillion:
	def __init__(self, numAsString):
			
		# numAsString must only contain numbers, spaces and commas
		numAsString = re.sub('[ \,]', '', numAsString)
		
		if re.search('[^0-9]', numAsString):
			raise RuntimeError('Input must only contain numbers, spaces and commas')
		if len(numAsString) == 0:	
			raise RuntimeError('No digits in the string')
		
		# splat operator + list comprehension
		self.__num = [int(i) for i in [*numAsString]] 
	
	def increment(self):
		numLength = len(self.__num)
		changeIndex = numLength - 1
		while self.__num[changeIndex] == 9 and changeIndex >= 0:
			changeIndex -= 1
		
		if changeIndex == -1:
			# the entire number is 9s
			self.__num = [1] + [0] * numLength
		else:
			self.__num[changeIndex + 1 :] = [0] * (numLength - changeIndex - 1)
			self.__num[changeIndex] += 1
			
	def isZero(self):
		# if it contains a non-zero element, then it is not equal to zero
		return re.search('[1-9]', self.toString()) == None
		
	def toString(self):
		return ''.join([str(i) for i in self.__num])
		
		
# ----- tests --------
try:
  z = Zillion('')
except RuntimeError:
  print('Empty string')

# It must print 'Empty string' without apostrophes. 2 points.

try:
  z = Zillion(' , ')
except RuntimeError:
  print('No digits in the string')

# It must print 'No digits in the string' without apostrophes. 2 points.

try:
  z = Zillion('1+0')
except RuntimeError:
  print('Non-digit in the string')

# It must print 'Non-digit in the string' without apostrophes. 2 points.

try:
  z = Zillion('0')
except RuntimeError:
  print('This must not be printed')

#  It must print nothing. 2 points.

print(z.isZero())    #  It must print True. 2 points.

try:
  z = Zillion('000000000')
except RuntimeError:
  print('This must not be printed')

#  It must print nothing. 2 points.

print(z.isZero())    #  It must print True. 2 points.

try:
  z = Zillion('000 000 000')
except RuntimeError:
  print('This must not be printed')
 
#  It must print nothing. 2 points.

print(z.isZero())    #  It must print True. 2 points.

try:
  z = Zillion('997')
except RuntimeError:
  print('This must not be printed')

#  It must print nothing.  2 points.

print(z.isZero())    #  It must print False. 2 points.

print(z.toString())  #  It must print 997. 2 points.

z.increment()

print(z.toString())  #  It must print 998. 2 points.

z.increment()

print(z.toString())  #  It must print 999. 2 points.

z.increment()

print(z.toString())  #  It must print 1000. 2 points.

try:
  z = Zillion('0 9,9 9')
except:
  print('This must not be printed')

#  It must print nothing.  3 points.

z.increment()
print(z.toString())  #  It must print 1000. 2 points.

# program output:
'''
Empty string
No digits in the string
Non-digit in the string
True
True
True
False
997
998
999
1000
1000
'''
