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

}