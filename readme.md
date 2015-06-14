#collection.db  

##format
* **int32** - dummy value
* **int32** - number of collections (*n*)
* loop *n* times, reading the following:
    * **string** - name of collection
    * **int32** - number of beatmaps in the collection (*i*)
    * loop *i* times, reading the following:
        *  **string** - md5 hash of beatmap's .osu file

##spec
* **int32**  
    *  4 bytes
    * little endian byte order
* **string**
    * prefixed with length of string encoded as an integer
    * read 7 bits at a time