# 15 pkt.
# Zaimplementuj "ksiazkowy" schemat szyfrowania RSA (Gen(), Enc(), Dec())
# dla wersji RSA, w ktorej modul jest postaci: n = p_1 ... p_k (d jest dlugoscia w bitach liczb p_i).

# Program uruchomiony z parametrami     prog gen k d     generuje klucz prywatny i publiczny (i przechowuje je w pliku)

# Zaimplementuj dwie wersje szyfrowania i deszyfrowania:
    # 1. "Tradycyjna"
    # 2. Wykorzystujaca CRT (chinskie twierdzenie o resztach)

# Wersja CRT ma dzialac rownolegle na k watkach. Porownaj efektywnosc wersji "tradycyjnej" z wersja CRT
# dla roznych wartosci k i d (sprawdz te same wartosci k, d, co w zadaniu 2)

# (3 pkt.)
# Wykorzystaj swoja implementacje RSA (dla roznych parametrow) z implementacja AES z poprzedniej listy
# (wybierz takie długości kluczy, aby to porównanie miało sens – vide
# NIST SP 800-57, str. 60: http://csrc.nist.gov/publications/drafts/800-57/ sp800-57p1r4_draft.pdf).
# W tym celu potraktuj RSA jako szyfr blokowy wykorzystując np. CBC.
# Spróbuj zaszyfrować/deszyfrować 1, 10, 100, 1000, 10000 wiadomości o długości 128 bitów, 1MB, 10MB.

# Alicja Michniewicz
import sys, math
from Crypto.Util.number import getPrime, GCD
#.RSA gen k d


DEFAULT_BLOCK_SIZE = 128
BYTE_SIZE = 256

def main():

    if str(sys.argv[1]) == 'gen':
        # generate public and private key
        d = str(sys.argv[3])
        k = str(sys.argv[2])
        totient = 1

        for i in range(1,k):
            temp = getPrime(d, randfunc=None) #randfunc = dev/random/
            N = N * temp
            totient = totient * (temp-1)

        f = math.exp(-1) % totient
        pubkey = (N, math.e)
        privkey = (N, f)

        # create public/private keypair with 1024 bit keys
        print('Making key files...')
        f = open('privkey.txt', 'w')
        f.write(privkey.decode('utf-8')) # bytes to string conversion
        f.close()
        g = open('pubkey.txt', 'w')
        g.write(pubkey.decode('utf-8')) # bytes to string conversion
        g.close()

        #enc = math.pow(p, math.e) % N
        #dec = math.pow(q, d) % N
    print('Enter encryption mode: traditional or CRT')
    s = input('--> ')
    if s == 'traditional':
        print('You chose the traditional mode')
        RSA_traditional()

    elif s == 'CRT':
        print('You chose the Chinese Remainder Theorem mode')
        CRT()

def CRT():
    return


def RSA_traditional():
    filename = 'encrypted_file.txt' # the file to write to / read from
    mode = 'encrypt' # set mode to 'decrypt' or 'encrypt'

    if mode == 'encrypt':
        message = "Wykorzystaj swoja implementacje RSA (dla roznych parametrow) z implementacja AES z poprzedniej listy"
        publicKeyFilename = 'pubkey.txt'
        print('Reading from %s and decrypting...' % (filename))
        encryptedText = encryptAndWriteToFile(filename, publicKeyFilename, message)

        print('Encrypted text: ', encryptedText)

    elif mode == 'decrypt':
        privateKeyFilename = 'privkey.txt'
        print('Reading from %s and decrypting...' % (filename))
        decryptedText = readFromFileAndDecrypt(filename, privateKeyFilename)

        print('Decrypted text: ', decryptedText)









def getBlocksFromText(message, blockSize = DEFAULT_BLOCK_SIZE):
    # Converts a string message to a list of block integers. Each integer represents #blockSize# string characters.

    messageBytes = message.encode('ascii')
    blockInts = []

    for blockStart in range(0, len(messageBytes), blockSize):
        # Calculate the block integer for this block of text
        blockInt = 0

        for i in range(blockStart, min(blockStart+blockSize, len(messageBytes))):
            blockInt += messageBytes[i] * (BYTE_SIZE ** (i % blockSize))
        blockInts.append(blockInt)
    return blockInts

def getTextFromBlocks(blockInts, messageLength, blockSize = DEFAULT_BLOCK_SIZE):
    # Converts a list of block integers to the original message string.
    # The original message length is needed to propoerly convert the last block integer.
    message = []

    for blockInt in blockInts:
        blockMessage = []
        str = ''

        for i in range(blockSize, -1, -1):
            if len(message) + i < messageLength:
                # Decode the message string for #blockSize# characters from this block integer.
                asciiNumber = blockInt // (BYTE_SIZE ** i)
                blockInt = blockInt % (BYTE_SIZE ** i)
                blockMessage.insert(0, chr(asciiNumber))
            message.extend(blockMessage)
        return "".join(message)

def encryptMessage(message, key, blockSize = DEFAULT_BLOCK_SIZE):
    # Converts the message string into a list of block integers,
    # then encrypts each block integer. Pass the PUBLIC key to encrypt.
    encryptedBlocks = []
    n, e = key

    for block in getBlocksFromText(message, blockSize):
        # ciphertext = plaintext ^ e mod n
        encryptedBlocks.append(pow(block, e, n))
    return encryptedBlocks

def decryptMessage(encryptedBlocks, messageLength, key, blockSize = DEFAULT_BLOCK_SIZE):
    # Decrypts a list of encrypted blocks into the original message stirng.
    # The original message length is required to properly decrypt the last block.
    # Be sure to pass the PRIVATE key to decrypt.
    decryptedBlocks = []
    n, d = key

    for block in encryptedBlocks:
        # plaintext = ciphertext ^ d mod n
        decryptedBlocks.append(pow(block, d, n))
    return getTextFromBlocks(decryptedBlocks, messageLength, blockSize)

def readKeyFile(keyFilename):
    # Given the filename of a file that contains a public or private key,
    # return the key as a (n, e) or (n, d) tuple value.
    fo = open(keyFilename)
    content = fo.read()
    fo.close()
    keySize, n, EorD = content.split(',')
    return (int(keySize), int(n), int(EorD))

def encryptAndWriteToFile(messageFilename, keyFilename, message, blockSize = DEFAULT_BLOCK_SIZE):
    # Using a key from a key file, encrypt the message and save it to a file.
    # Return the encrypted message string.
    keySize, n, e = readKeyFile(keyFilename)

    # Check that key size is greater than block size.
    if keySize < blockSize * 8: # * 8 to convert bytes to bits.
        sys.exit('ERROR: Block size is %s bits and key size is %s bits. Key size needs to be greater or equal to block size.' % (blockSize * 8, keySize))

    # Encrypt the message
    encryptedBlocks = encryptMessage(message, (n, e), blockSize)

    # Convert the large int values to one string value
    for i in range(len(encryptedBlocks)):
        encryptedBlocks[i] = str(encryptedBlocks[i])
    encryptedContent = ','.join(encryptedBlocks)

    # Write out the encrypted string to the output file.
    encryptedContent = '%s_%s_%s' % (len(message), blockSize, encryptedContent)
    fo = open(messageFilename, 'w')
    fo.write(encryptedContent)
    fo.close()

    # Also return the encrypted string.
    return encryptedContent

def readFromFileAndDecrypt(messageFilename, keyFilename):
    # Using a key from a key file, read an encrypted message from a file,
    # then decrypt it. Return the decrypted message string.
    keySize, n, d = readKeyFile(keyFilename)

    # Read in the message length and the encrypted message from the file.
    fo = open(messageFilename)
    content = fo.read()
    messageLength, blockSize, encryptedMessage = content.split('_')
    messageLength = int(messageLength)
    blockSize = int(blockSize)

    # Check that key size is greater than block size.
    if keySize < blockSize * 8: # * 8 to convert bytes to bits
        sys.exit('ERROR: Block size is %s bits and key size is %s bits. Key size needs to be greater or equal to block size.' % (blockSize * 8, keySize))

    # Convert the encrypted message into large int values.
    encryptedBlocks = []

    for block in encryptedMessage.split(','):
        encryptedBlocks.append(int(block))

    # Decrypt the large int values.
    return decryptMessage(encryptedBlocks, messageLength, (n, d), blockSize)

main()






































