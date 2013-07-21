package hu.tamasrev

trait Node {
  def getParent() : Node;
  def getLeft() : Node;
  def getRight() : Node;
}

object NoNode extends Node {
  def getParent() : Node = this
  def getLeft() : Node = this
  def getRight() : Node = this;
}

class Root extends Node {
  def getParent() : Node = NoNode
  def getLeft() : Node = NoNode
  def getRight() : Node = NoNode
}

