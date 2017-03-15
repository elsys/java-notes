all: 01java.pdf 02java.pdf 03java.pdf 04java.pdf 05java.pdf 06java.pdf java-threads.pdf

clean:
	rm -rf *~ *.aux *.log *.nav *.out *.snm *.bak *.toc *.dvi *.backup *.bbl *.blg

	
%.pdf: %.tex
	./tex-it pdflatex $<
