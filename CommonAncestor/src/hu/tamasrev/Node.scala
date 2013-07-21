package hu.tamasrev

trait Node {
  def getParent() : Node;
  def getLeft() : Node;
  def getRight() : Node;
}

trait MutableNode extends Node {
  def setParent(node: MutableNode): Unit
  def setLeft(node: MutableNode): Unit
  def setRight(node: MutableNode) : Unit
}

object NoNode extends MutableNode {
  def getParent() : Node = this
  def setParent(node: MutableNode) : Unit = throwNotSupported()
  def getLeft() : Node = this
  def setLeft(node : MutableNode) : Unit = throwNotSupported()
  def getRight() : Node = this
  def setRight(node : MutableNode) : Unit = throwNotSupported()
  
  private def throwNotSupported() : Unit = throw new UnsupportedOperationException("Cannot add node to NoNode")
  
}

class BuildingNode(var parent : MutableNode, var left : MutableNode, var right : MutableNode) extends MutableNode {
  
  def this() = this(NoNode, NoNode, NoNode)
  
  def getParent() : Node = parent
  def setParent(node: MutableNode) : Unit = {
    parent = node
  }
  def getLeft() : Node = left
  def setLeft(node: MutableNode) : Unit = {
    left = node
    left.setParent(this)
  }
  def getRight() : Node = right
  def setRight(node: MutableNode) : Unit = {
    right = node
    right.setParent(this)
  }
  
}

class Root extends BuildingNode {
  override def getParent() : Node = NoNode
  override def setParent(node : MutableNode) : Unit = throw new UnsupportedOperationException("Cannot add parent to Root")
}

class Leaf extends BuildingNode {
  override def getLeft() : Node = NoNode
  override def setLeft(node : MutableNode) : Unit = throwUnsupported()
  override def getRight() : Node = NoNode
  override def setRight(node : MutableNode) : Unit = throwUnsupported()
  
  private def throwUnsupported() = throw new UnsupportedOperationException("Cannot add parent to Root")
  
}

