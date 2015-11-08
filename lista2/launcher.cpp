//Alicja Michniewicz
//Program szyfrujacy wybrane pliki kluczem pobranym z keystore'a
#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <iostream>

#include <openssl/aes.h>

#include "Encryption.h"

using namespace std;

int main(int argc, char *argv[])
{

	if (argc != 5)
	{
		cerr << "./launcher enc_scheme enc_mode keystore_path key_id" << endl;
		exit(EXIT_FAILURE);
	}

	if (strcmp(argv[1], "AES") == 1)
	{
		cerr << "U¿yj szyfrowania AES" << endl;
		exit(EXIT_FAILURE);
	}
	if (strcmp(argv[2], "CFB") == 0)
	{
		string keystorePath = atoi(argv[3]);
		string keyId = atoi(argv[4]);
		string pass;
		cout << "Podaj has³o do klucza " + keyId + ": " << endl;
		cin >> pass; //TODO: nie wyswietlaj hasla w terminalu
		char outputKey[32];
		obtainKey((char *)keystorePath.c_str() , (char *)keyId.c_str(), (char *)pass.c_str(), outputKey);

		FILE *toBeEncrypted = fopen("fileToEncrypt", "rb");
		FILE *Encrypted = fopen("encryptedFile", "wb");

		unsigned char ivec[17]; //generuje losowo wektor inicjalizujacy
		memset(ivec, 0, 16);
		RAND_bytes(ivec, 16);
		ivec[16] = '\0';

		encryptFileCFB((unsigned char *)outputKey, (unsigned char *)ivec, toBeEncrypted, Encrypted, AES_ENCRYPT);
		fclose(toBeEncrypted);
		fclose(Encrypted);
	}
	else
	{
		cerr << "U¿yj trybu CFB" << endl;
		exit(EXIT_FAILURE);
	}

	system("PAUSE");
	return 0;
}
