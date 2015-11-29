# RSA Parallel Prime Number Generator
#.RSA_PrimeGen k d

# Alicja Michniewicz

import sys, _thread
from Crypto.Util.number import getPrime

k = str(sys.argv[1])
d = str(sys.argv[2])

def main():

    for i in range(1,k):
        _thread.start_new_thread(generate(i))

def generate(threadName):
    temp = getPrime(d, randfunc=None) #randfunc = dev/random/
    print('%d ' % temp)


main()
