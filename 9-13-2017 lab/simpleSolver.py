# Alan Koval, 9/13/2017
# assignment: https://ay17.moodle.umn.edu/pluginfile.php/1267788/mod_resource/content/1/lab1.pdf

def solve(var, expr):
	if isInside(var, expr[0]):
		return solving(var, expr)
		
	# otherwise switch the order so that the variable is on the left
	return solving(var, (expr[2], '=', expr[0]))
	
# var is garunteed to be on the left side of expr
def solving(var, expr):
	# expr = (a, '=', b), where a contains var
	
	# if our variable is isolated
	if expr[0] == var:
		return expr
		
	# otherwise, compute a step toward isolation of the variable.
	# this means undoing the expression on the left side
	leftOp = expr[0][1]
	
	# normally I would not hardcode these function names in, but the implementation said 
	# that they "must" be defined in my program.
	expr = {
		'+': solvingAdd,
		'-': solvingSubtract,
		'*': solvingMultiply,
		'/': solvingDivide
	}[leftOp](var, expr)
	
	return solving(var, expr)
	
# we will generate our solving(Add|Subtract...) functions here. Sorry for the 
# inelegance, I would normally have done this as a set of anonymous function, 
# but it said that solvingAdd, solvingSubtract... functions "must" be defined in the file, not 
# just as entries of a dictionary or something

# also, this would normally be a list (extendable, again), but I would get a zero on the assignment, so 
# I'm going to hack around it with some nested tuples instead
operations = (
	# each entry is an anonymous function that returns the result of the inverse of (other | unknown, operation, other | unknown, res), depending on whether 
	# the other is on the left side of the operation or the right (given by 'flip' -- flip == true => other is on the right)
	
	# addition    left: a = c - b, right: b = c - a 
	('+', lambda other, flip, res: (res, '-', other)),
	# subtraction    left: a = c + b, right: b = a - c
	('-', lambda other, flip, res: (other, '-', res) if flip else (res, '+', other)),
	# multiplication    left: a = c / b, right: b = c / a
	('*', lambda other, flip, res: (res, '/', other)),
	# division    left: a = c * b, right: b = a / c
	('/', lambda other, flip, res: (other, '/', res) if flip else (res, '*', other))
)

solvingAdd = solvingSubtract = solvingDivide = solvingMultiply = None

for op in operations:
	# you have no idea how tempted I am to use exec(...) here. Restraint is hard. Also, why 
	# do these functions have to be explicitly defined in the file? This is annoying.
	
	# 'cause python has late-binding of variables
	def makeFunc(opFunc):
		def tempFunc(var, expr): 
			# expr = ((a, operation, b), '=', c)
			# nested = (a, operation, b)
			nested = expr[0]
			(varExp, other) = (nested[0], nested[2]) if isInside(var, nested[0]) else (nested[2], nested[0])
			res = expr[2]
			
			return (varExp, '=', opFunc(other, other == nested[0], res))
		
		return tempFunc
	
	# I absolutely hate to do this, but a dictionary will not associate the variable names to the object.
	# Again, I will restrain myself from using exec, though it probably would be secure in this instance
	# 	(what, don't believe me? Here you go:
	# 		exec({'+':'solvingAdd', '-':'solvingSubtract'...}[op[0]]+'=producedFunc')
	# 	one line. so much better. But probably really slow, but hey, you're the one that made me 
	#	explicity define these functions)
	# All the more reason to forgo specific solvingAdd, solvingSubtract... functions and go with anonymous functions 
	# in a dictionary or something
	producedFunc = makeFunc(op[1])
	if op[0] == '+':
		solvingAdd = producedFunc
	elif op[0] == '-':
		solvingSubtract = producedFunc
	elif op[0] == '*':
		solvingMultiply = producedFunc
	elif op[0] == '/':
		solvingDivide = producedFunc
	
def isInside(var, expr):
	# it seems as if symbols were arbitrarily not considered "inside" expressions. This was not in the 
	# 	implementation notes
	if var in ('+','-','*','/') or type(expr) != tuple and expr != var:
		return False
	
	if var == expr:
		return True
	
	# map across any tuple elements of expr recursively
	return (var in expr) or (True in mapTuple(lambda x: type(x) is tuple and isInside(var, x), expr))

# I am ornery because I can't use the list map function (zero on the assignment, remember?)
def mapTuple(func, tup):
	newTup = ()
	for t in tup:
		newTup += (func(t),)
	return newTup
	

	
	