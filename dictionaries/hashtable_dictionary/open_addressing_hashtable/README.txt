* AUTHOR: Nicola Lamonaca
* DATE: June, 2013

* DESCRIPTION: Implementation of a dictionary using an indexed representation and the open addressing tecnique to manage collisions. When a cell in the array corresponding to the hash of a key is already taken, the array is inspected to find the first empty cell available. This is done using a scan function.