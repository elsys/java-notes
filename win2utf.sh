#!/bin/bash

TEMPFILE=`tempfile`
if test -z "$TEMPFILE" ; then
	TEMPFILE=/tmp/tmp-$USER
fi

for file in $@; do
	cp $file $file.bak
	sed -e "s/\(windows-1251\)\|\(cp1251\)\|\(CP1251\)/utf-8/g" "$file" > $TEMPFILE 
	iconv -f cp1251 -t utf8 $TEMPFILE > "$file"
	rm -f $TEMPFILE
done

