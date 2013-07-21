package hu.tamasrev

import org.testng.annotations.Test
import org.testng.annotations.BeforeClass
import org.testng.Assert._

class CommonAncestorFinderTest {

  val rootA : MutableNode = new Root()
  val leftA : MutableNode = new BuildingNode()
  val rightA : MutableNode = new BuildingNode()
  val leftLeftA : MutableNode = new Leaf()
  
  val rootB : MutableNode = new Root()
  val leftB : MutableNode = new BuildingNode()
  val rightB : MutableNode = new BuildingNode()
  val leftLeftB : MutableNode = new Leaf()
  
  val checker = CommonAncestorFinder
    
  @BeforeClass
  def setUp() : Unit = {
    rootA.setLeft(leftA)
    rootA.setRight(rightA)
    leftA.setLeft(leftLeftA)
    
    rootB.setLeft(leftB)
    rootB.setRight(rightB)
    leftB.setLeft(leftLeftB)
  }
  
  @Test
  def testDepth() : Unit = {
    //WHEN
    val leftLeftDepth = checker.getDepth(leftLeftA)
    val leftDepth = checker.getDepth(leftA)
    val rootDepth = checker.getDepth(rootA)
    
    //THEN
    assertEquals(rootDepth, 0)
    assertEquals(leftDepth, 1)
    assertEquals(leftLeftDepth, 2)
  }
  
  @Test
  def testSameDepth() : Unit = {
    //WHEN
    val isSameLR = checker.isSameLevel(leftA, rightA)
    val isSameLLR = checker.isSameLevel(leftLeftA, rightA)
    val isSameDifferentTrees = checker.isSameLevel(leftLeftA, leftLeftB)
    
    //THEN
    assertTrue(isSameLR)
    assertFalse(isSameLLR)
    assertTrue(isSameDifferentTrees)
  }
}