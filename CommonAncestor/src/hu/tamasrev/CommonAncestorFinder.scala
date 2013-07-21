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
}