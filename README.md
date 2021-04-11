# Burrows-Wheeler Compression


This project implements the Burrows–Wheeler data compression algorithm. 
This revolutionary algorithm outcompresses gzip and PKZIP, is relatively easy to implement, and is not protected by any patents. It forms the basis of the Unix compression utility bzip2.


### Compression steps:

#### Burrows–Wheeler transform. 
Given a typical English text file, transform it into a text file in which sequences of the same character occur near each other many times.
#### Move-to-front encoding. 
Given a text file in which sequences of the same character occur near each other many times, convert it into a text file in which certain characters appear much more frequently than others.
#### Huffman compression. 
Given a text file in which certain characters appear much more frequently than others, compress it by encoding frequently occurring characters with short codewords and infrequently occurring characters with long codewords.
Step 3 is the only one that compresses the message: it is particularly effective because Steps 1 and 2 produce a text file in which certain characters appear much more frequently than others. To expand a message, apply the inverse operations in reverse order: first apply the Huffman expansion, then the move-to-front decoding, and finally the inverse Burrows–Wheeler transform. Your task is to implement the Burrows–Wheeler and move-to-front components.
