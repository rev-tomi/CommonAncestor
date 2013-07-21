package hu.tamasrev

object LoopFinder {

  abstract class NextStep {
    def getNext(head : Node, behind : Node) : (Node, Node, NextStep)
  }
  case object HeadFirst extends NextStep {
    def getNext(head : Node, behind : Node) = (head.getParent(), behind, HeadSecond)
  }
  case object HeadSecond extends NextStep {
    def getNext(head : Node, behind : Node) = (head.getParent(), behind, Behind)
  }
  case object Behind extends NextStep {
    def getNext(head : Node, behind : Node) = (head, behind.getParent, HeadFirst)
  }
  
  def isInLoop(n : Node) : Boolean = {
    def isInLoop(head : Node, behind : Node, step : NextStep) : Boolean = {
      if (head == behind) true
      else if (head == NoNode) false
      else {
        val params = step.getNext(head, behind)
        isInLoop(params._1, params._2, params._3)
      }
    }
    
    if (n == NoNode) false
    else isInLoop(n.getParent, n, HeadFirst)
  }
}