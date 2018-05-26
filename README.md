Randomized queue. 

A randomized queue is similar to a stack or queue, except that the item removed is chosen uniformly at random from items 
in the data structure.

Iterator.  Each iterator must return the items in uniformly random order. The order of two or more iterators to the same 
randomized queue must be mutually independent; each iterator must maintain its own random order.

Performance requirements.  
Your randomized queue implementation must support each randomized queue operation (besides creating an iterator) in 
constant amortized time. That is, any sequence of m randomized queue operations (starting from an empty queue) must take 
at most cm steps in the worst case, for some constant c.

Additionally, your iterator implementation must support operations next() and hasNext() in constant worst-case time; and 
construction in linear time; you may (and will need to) use a linear amount of extra memory per iterator.