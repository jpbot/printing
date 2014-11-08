layup
=====
This is a collection of utilities created to build pdf documents for print
from source pdf files.

These will probably all use itext, an old version is included in this
directory because I believe the API changed between 2 and 5

Some of the code was lost and recovered from .class files, most of the n-up
tiling programs stem from the same code and checking the comments
in other files will probably help

2sidebyside
-----------
StackUp was probably a first go round for most of these utilities.

Pages are aranged in a single sided fashion in two columns side by side.
See the test file for an example.

2up
---

3up
---

4up
---
FourLateral is for ordered cards, it lays out cards in a 4up format but
stacks each sub page down through the stack first instead of simply
laying out 1-4 on the first page and 5-8 on the second etc.

See the test documents for how it works. -4U.pdf files are the output.

    java -cp itext-2.0.4.jar:<path to classfile> FourLatteral <input.pdf>

4up_2sides
----------
FourUp is similar to FourLateral (4up) above, except it works with 2-sided
documents, keeping duplex pages together

6up
---

saddlestitch
------------
Perhaps the premium here, saddlestitch performs saddle stich build booklet
proceedure on the source pdf. There's no automatic signature creation.
This is intended to be used with copiers or production printers that can
perform saddle stitch finishing. Xerox 4110 EPS systems for example. In the
past the driver function wasn't very good and this produced much better
results, even allowing VIPP to be used if you're so licensed.

   java -cp itext-2.0.4.jar:.<path to classfile> saddlestitch <input.pdf>

itext-2.0.4
-----------
itext is used by these utilities, rather than include it in the
repo multiple times I'm leaving it here.
