# paradigms of programming
#	procedural, oop, functional

# oop enforces data restrictions
#	ex: an age field can automatically restrict ages, make it a positive integer, etc through the "object" construct ("abstract data type")
#	The point is that you can't mess up from a user standpoint
#	Encapsulation through methods (only see useful stuff)
#	abstract data types + inheritance == oop

# there is no overloading in python

# Kludge | Kluge -- Inelegant solution to a problem, often resulting from an inelegant solution to a previous problem

class Person:
	def __init__(self, age, name):
		if not (type(age) is int and age >= 0):
			raise ValueError('Invalid age')
			
		if not(type(name) is str and len(name) > 0):
			raise ValueError('"'+name+'" is an invalid name')
			
		self.__name = name
		self.__age = age
		
	def getName(self):
		return self.__name
		
	def getAge(self):
		return self.__age
		

p = Person(5, 'billy smith')
print(p.getName(), p.getAge())
#print(p.__name, p.__age)

#q = Person(-85, '')
#v = Person(1, '')

# handling exceptions
try:
	p2 = Person(5, '')
except ValueError as e:
	print('error found, message = ', e)
except:
	print('wont get here!')
finally:
	print('an error may have occured, but do this anyway')
	
# pass is literaly an empty statement (space filler?)

# optional arguments:
def customAdd(a, *others):
	sum = a
	for i in others:
		sum += i
	return sum

print('custom add ex. ', customAdd(1,2,3,4,5))

# inheritance:
# 
	
	
	
	