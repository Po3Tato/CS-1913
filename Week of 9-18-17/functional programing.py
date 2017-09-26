# functional programming in python
# functions as data
# first class data objects:
#	pass it as argument
#	return it as value
#	assign to variable
#	make it part of another data structure
#	etc

# Guess what? Functions can be first class data objects too! (no way!)
from functools import reduce

a = [1,2,3,4,5]
print(a[1:])

print(a[0:-2])

# 9/25/17 notes (if any)
# filter(predicate, sequence), if predicate then stays
# reduce(func, list, initial) -> func(func(initial, list[0]), list[1])... (left reduce or foldl)
def foldl(func, list, initial):
	val = initial
	for el in list:
		val = func(val, el)
	return val

	
print(foldl(lambda x,y: x/y, a, 1))

# average length of single names
names = ['prince', 'jim', 'sally smith', 'a b c', 'd', 'cho', 'james smith', 'billy the kid']
singleNames = list(filter(lambda x: not (' ' in x), names))
lengths = list(map(lambda x: len(x), singleNames))
average = reduce(lambda x,y: x+y, lengths, 0) / len(lengths)
print(average)