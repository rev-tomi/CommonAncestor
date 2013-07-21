package hu.tamasrev

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass
import org.testng.Assert._

class CommonAncestorFinderTest {

  private val rootA : MutableNode = new Root()
  private val leftA : MutableNode = new BuildingNode()
  private val rightA : MutableNode = new BuildingNode()
  private val leftLeftA : MutableNode = new Leaf()
  private val leftRightA : MutableNode = new BuildingNode()
  private val leftRightLeftA : MutableNode = new Leaf() 
  
  private val rootB : MutableNode = new Root()
  private val leftB : MutableNode = new BuildingNode()
  private val rightB : MutableNode = new BuildingNode()
  private val leftLeftB : MutableNode = new Leaf()
  
  private val rootLoop = new BuildingNode()
  private val rightLoop = new BuildingNode()
  
  private val checker = CommonAncestorFinder
    
  @BeforeClass
  def setUp() : Unit = {
    rootA.setLeft(leftA)
    rootA.setRight(rightA)
    leftA.setLeft(leftLeftA)
    leftA.setRight(leftRightA)
    leftRightA.setLeft(leftRightLeftA)
    
    rootB.setLeft(leftB)
    rootB.setRight(rightB)
    leftB.setLeft(leftLeftB)
    
    rootLoop.setRight(rightLoop)
    rightLoop.setLeft(rootLoop)
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
  def testGetNodesOnSameLevel() : Unit = {
    //WHEN
    val lr = checker.getNodesOnSameLevel(leftLeftA, rightA)
    
    //THEN
    assertEquals(lr._1, leftA)
    assertEquals(lr._2, rightA)
  }
  
  @Test
  def testGetNodesOnSameLevelDifferentTrees() : Unit = {
    //WHEN
    val lr = checker.getNodesOnSameLevel(rightB, leftLeftA)
    
    //THEN
    assertEquals(lr._1, rightB)
    assertEquals(lr._2, leftA)
  }
  
  @Test
  def getNodesCommonAncestor() : Unit = {
    //WHEN
    val ancestor = checker.getCommonAncestor(leftLeftA, leftRightLeftA)
    
    //THEN
    assertEquals(ancestor, leftA)
  }

  @Test
  def getNodesCommonAncestorDifferentTrees() : Unit = {
    try {
      //WHEN
      val ancestor = checker.getCommonAncestor(leftLeftB, leftRightLeftA)
      fail("An IllegalArgumentException should've been thrown")
    } catch {
      //THEN
      case iae : IllegalArgumentException => assertEquals(iae.getMessage(), "Different trees")
    }
  }
  
  @Test
  def getNodesCommonAncestorRoot() : Unit = {
    try {
      //WHEN
      val ancestor = checker.getCommonAncestor(leftLeftB, rootLoop)
      fail("An IllegalArgumentException should've been thrown")
    } catch {
      //THEN
      case iae : IllegalArgumentException => assertEquals(iae.getMessage(), "Loop detected")
    }
  }
}