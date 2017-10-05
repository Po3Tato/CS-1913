// ordinary compiler: source file (.txt) --> machine assembly --> microprocessor
// Java: source file (.txt) --> Java compiler (javac) turns it into "bytecode" --> JVM virtual machine (interprets bytecode)
//   - supposedly more secure, since all programs run the same
//	 - doesn't actual run as fast as native code (assembly)
//	 	- hence the JIT ("Just in Time Compiler"), which decides to compile some of your code into assembly without you knowing
// 	 - free garbage collection

// other languages (Groovy, clojure, Jython) compile to Java bytecode (and uses the JVM for convenience)

// low level: bits / circuits
// still pretty low: assembly languages
// pretty medium: C
// solidly medium: C++
// Java is somewhere in here
// high level: python, JS, php, etc

// int, short, long, double, char, float, byte
// int k;
// now k == 0
class Java{
	public static void main(String[] args){
		int k = 12;
		// should be 0
		System.out.println(k);
	}
}

// pointer arithmetic in C, C++
// 	 we can add ints to pointers
//   	in C, if a[i] = [_,_,_,_...][i], then we can also start a pointer at 0 and increment it by 1 and access the memory at that location
// 		(it's fast, but dangerous, because you can over the memory location (buffer overrun error))
// no pointer arithmetic in Java, python, like all other languages (supposedly safer)
// hence, to sound good, Java people said that there were "References, not pointers". Basically, they are pointers without pointer arithmetic
