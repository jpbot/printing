PageCount
=========
Count the pages in a pdf file. Can count the pages in multiple pdf files
provided on the command line. Works nicely when your shell expands
wildcards (globbing).

Here's an example runing on bash:
   $ java -cp .:itext-2.0.4.jar PageCount *pdf
   test7.pdf 7
   test9.pdf 9

Uses itext, check the printing/layup directory for itext. Example used test
files from layup as well.
