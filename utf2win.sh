#!/bin/bash

TEMPFILE=`tempfile`
if test -z "$TEMPFILE" ; then
	TEMPFILE=/tmp/tmp-$USER
fi

for file in $@; do
	cp $file $file.bak
	sed -e "s/\(UTF-8\)\|\(utf-8\)/windows-1251/" "$file" > $TEMPFILE 
	iconv -f utf8 -t cp1251 $TEMPFILE > "$file"
	rm -f $TEMPFILE
done

