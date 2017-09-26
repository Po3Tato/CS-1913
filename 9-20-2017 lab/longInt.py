# Alan Koval, 9/20/2017
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
