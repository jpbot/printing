layup
=====
This is a collection of utilities created to build pdf documents for print from source pdf files.

These will probably all use itext, an old version is included in this directory because
I believe the API changed between 2 and 5

4up
---
FourLateral is for ordered cards, it lays out cards in a 4up format but
stacks each sub page down through the stack first instead of simply
laying out 1-4 on the first page and 5-8 on the second etc.

See the test documents for how it works. -4U.pdf files are the output.

    java -cp itext-2.0.4.jar:<path to classfile> FourLatteral <input.pdf>

itext-2.0.4
-----------
itext is used by these utilities, rather than include it in the repo multiple times
I'm leaving it here.
