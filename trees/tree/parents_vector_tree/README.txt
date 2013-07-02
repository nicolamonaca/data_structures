* AUTHOR: Nicola Lamonaca
* DATE: June, 2013

* DESCRIPTION: Implementation of a tree using a parents array (and a nodes array).
nodes[0] stores the pointer to the root of the tree, while parents[0] is null, since the root has no parent. Each node has an associated index, which for the root is 0. When a subtree is going to be inserted, the new tree's root is put into nodes[index + 1] and all the nodes of the new tree into nodes[i + 1], for i varying in [index + 1, nodes.length - 1].