# Encryption Standards

DES is a symmetric-key algorithm developed by IBM in the 1970's to meet the request of the National Bureau of Standards. It also employed what I believe to be one of the coolest concepts ever: Feistel ciphers.

This project is a java implementation of DES and Triple DES (3DES). Prior to developing the project in code, quite some time was spent researching the Feistel function, key schedule, and the overall structure used in DES. Without any prior knowledge, the algorithm seemed quite complicated as it seemed to employ confusion and diffusion in almost a completely random way. However, once each of the steps are examined in detail, DES is quite simple.

A functional programming approach was employed after identifying the key method calls that were most repeated throughout the encryption process. What I am most content with is the fact that writing decrypt() was practically like duplicating encrypt(). The only difference is iterating through the key schedule in reverse. Creating a function feistel() to simulate each round allowed for a jumping-off point to identify other necessary functions. Implementation of 3DES may have been the least challenging part. Since 3DES is simply DES only encrypting, then decrypting, then encrypting once more (with different keys each time preferably), the encrypt() and decrypt() function calls were most helpful in doing so. 

It's understandable as to why the data encryption algorithm was retired as the standard and replaced by AES in 2002. 3DES's boasted key length of 168 bits has a worst case of being just as insecure as DES, which has a key length of 56 bits, if the same key is used for all three encryption operations. However, MITM (meet-in-the-middle) attacks can greatly decrease the brute force time, since they reduce a linear cracking process into smaller steps. 

I have future plans to implement a function to toggle output viewing so one can visually walk through each of the steps and rounds. I also have plans to implement this same program in Python. Further down the line, I am looking to develop another java implementation, only this time of the Advanced Encryption Standard (AES).
