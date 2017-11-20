# import __module__ [as __name__], __othermodule__ [as __othername__]
# from __module__ import __indentifier__ [as __name__], ...
# from __module__ import *
from functools import reduce
class example:
    def __init__(self, arg1):
        self.__privateVar = arg1
        if arg1 == 5:
            raise ValueError('error string')
        raise StandardError('other String')
        # StadardError, ArithmeticError, OverflowError, ValueError, etc

try:
    e = example(4)
except ValueError:
    print('got it!')
except:
    print('other error!')

a = (1,2,3)
b = list(filter(lambda x: x % 2 == 0, a))
c = list(map(lambda x: x**2, a))
d = reduce(lambda init,n: init**2+n, a,2)
print(b)
print(c)
print(d)
#732
'''Java
class A{
    private int a;
    public String ex;
    public A(String args){

    }

    public static void main(String[]args){
        int[] arr = new int[10];
        arr[2] = 3;
        system.out.println(arr.length);
        A var = new A(example);
    }
}

'''
