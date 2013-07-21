package hu.tamasrev

import org.testng.annotations.Test;
import org.testng.Assert._

class LoopFinderTest {

  private val loopFinder = LoopFinder
  
  @Test
  def testNoLoopWithRoot() : Unit = {
    //GIVEN
    val root = new Root()
    val left = new BuildingNode()
    val leftLeaf = new Leaf()
    root.setLeft(left)
    left.setLeft(leftLeaf)
    
    //WHEN
    val isLoop = loopFinder.isInLoop(leftLeaf)
    
    //THEN
    assertFalse(isLoop)
  }
  
  @Test
  def testLoopWithRoot() : Unit = {
    //GIVEN
    val root = new BuildingNode()
    val left = new BuildingNode()
    val leftLeaf = new BuildingNode()
    root.setLeft(left)
    left.setLeft(leftLeaf)
    leftLeaf.setLeft(root)
    
    //WHEN
    val isLoop = loopFinder.isInLoop(leftLeaf)
    
    //THEN
    assertTrue(isLoop)
  }
}