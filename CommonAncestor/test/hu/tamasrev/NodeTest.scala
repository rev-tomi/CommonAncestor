package hu.tamasrev

import org.testng.annotations.Test
import org.testng.Assert._

class NodeTest {
  
  @Test
  def testCreateNode() : Unit = {
    //GIVEN
    val root : Node = new Root()
    
    //WHEN
    val root_parent = root.getParent()
    val left = root.getLeft()
    val right = root.getRight()
    
    //THEN
    assertEquals(root_parent, NoNode)
    assertEquals(left, NoNode)
    assertEquals(right, NoNode)
  }
  
  @Test
  def testBuildTree() : Unit = {
    //GIVEN
    val root : MutableNode = new Root()
    val left : MutableNode = new BuildingNode()
    val right : MutableNode = new BuildingNode()
    val leftLeft : MutableNode = new Leaf()
    
    //WHEN
    root.setLeft(left)
    root.setRight(right)
    left.setLeft(leftLeft)
    
    //THEN
    assertSame(left, root.getLeft())
    assertSame(root, left.getParent())
    assertSame(leftLeft, left.getLeft())
    assertSame(left, leftLeft.getParent())
  }

}