package hu.tamasrev

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test
import org.testng.annotations.BeforeClass
import org.testng.Assert._
import org.testng.annotations.ExpectedExceptions

class CommonAncestorFinderTest {

  val rootA : MutableNode = new Root()
  val leftA : MutableNode = new BuildingNode()
  val rightA : MutableNode = new BuildingNode()
  val leftLeftA : MutableNode = new Leaf()
  val leftRightA : MutableNode = new BuildingNode()
  val leftRightLeftA : MutableNode = new Leaf() 
  
  val rootB : MutableNode = new Root()
  val leftB : MutableNode = new BuildingNode()
  val rightB : MutableNode = new BuildingNode()
  val leftLeftB : MutableNode = new Leaf()
  
  val checker = CommonAncestorFinder
    
  @BeforeMethod
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
    } catch {
      //THEN
      case iae : IllegalArgumentException => assertEquals(iae.getMessage(), "Different trees")
    }
  }
  
  //TODO loop check
}