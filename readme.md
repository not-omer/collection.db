#collection.db  

##format
* **int32** - dummy value
* **int32** - number of collections (*n*)
* loop *n* times, reading the following:
    * **ostring** - name of collection
    * **int32** - number of beatmaps in the collection (*i*)
    * loop *i* times, reading the following:
        *  **ostring** - name of beatmap

##spec
* **int32**  
    *  4 bytes
    * little endian byte order
* **ostring**
    * prefixed with length of string encoded as an integer
    * read 7 bits at a time
    * to get string length:  
```
private int getStringLength() throws IOException {
		int count = 0;
		int shift = 0;
		boolean more = true;
		while (more) {
			byte b = (byte) read();
			count |= (b & 0x7F) << shift;
			shift += 7;
			if ((b & 0x80) == 0) {
				more = false;
			}
		}
		return count;
	}
```
