package hu.tamasrev

import scala.annotation.tailrec

object CommonAncestorFinder {

  def getDepth(n : Node) : Int = {
    @tailrec
    def getDepth(n : Node, depth : Int) : Int = {
      if (n.getParent() == NoNode) depth
      else getDepth(n.getParent, depth + 1)
    }
    getDepth(n, 0)
  }
  
  def isSameLevel(n1 : Node, n2 : Node) : Boolean = {
    val d1 = getDepth(n1)
    val d2 = getDepth(n2)
    d1 == d2
  }
  
  @tailrec
  def getNodesOnSameLevel(n1 : Node, n2 : Node) : (Node, Node) = {
    val d1 = getDepth(n1)
    val d2 = getDepth(n2)
    if (d1 == d2) (n1, n2)
    else if (d1 < d2) getNodesOnSameLevel(n1, n2.getParent)
    else getNodesOnSameLevel(n1.getParent, n2)
  }
  
  def getCommonAncestor(n1: Node, n2: Node) : Node = {
    val lr = getNodesOnSameLevel(n1, n2)
    getCommonAncestorFromSameLevel(lr._1, lr._2)
  }
  
  @tailrec
  private def getCommonAncestorFromSameLevel(n1: Node, n2: Node) : Node = {
    if (n1 == n2) n1
    else if (n1.getParent == NoNode) throw new IllegalArgumentException("Different trees")
    else getCommonAncestorFromSameLevel(n1.getParent, n2.getParent)
  }
}