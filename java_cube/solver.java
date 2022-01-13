import rubiksCube.*;
import java.util.*;
/*
- Setup recursive node solution similar to the "min/max" function
  - make move, calc sim (taken care of upon node creation), create node
  - analize tree, make main move, repeat
- Setup hash table to store previous cube states and their "solutions"
- Compare move length if solution exists (if new < old replace )
- export data sheet? or make data sheet edits while in runtime?
*/
public class solver
{
  public static void main (String args[])
  {
    
    int scrambles = 5;

    Cube initCube = new Cube();

    initCube.scramble(scrambles);

    initCube.printCube();

    node parentNode = new node(initCube);

    //initializes the solving of node "Parent Node", at depth 0, with scrambles alterations made//
    populate(parentNode, 0, scrambles + 1);

  }

  // returns the length of the node "stem" //
  static boolean _genBranch(node parentNode)
  {
    String[] moves = new String[]{ "D0", "D1", "D2", "U0", "U1", "U2", "L0", "L1", "L2", "R0", "R1", "R2"};
    for(String move : moves)
    {
      Cube newCube = new Cube(parentNode.getData().getCube());
      newCube.interpretMove(move);
      node childNode = new node(newCube);
      childNode.setMove(move);
      if(childNode.getSim() == 1.0)
      {
        System.out.println("Solved the cube!\n");
        return true;
      }
      parentNode.addChild(childNode);
    }
    return false;
  }

  static void populate(node parent, int depth, int depthLimit)
  {
    boolean solved = false;
    if(solved == false)
    {
      if(depth == 0)
      {
        if(_genBranch(parent) == false)
        {
          depth ++;
          populate(parent, depth, depthLimit);
        }
      }
      else
      {
        depth ++;
        if(depth < depthLimit)
        {
          for(node child : parent.getChildren())
          {
            if(_genBranch(child) == false)
            {
              populate(child, depth, depthLimit);
            }
          }
        }
      }
    }
    else
    {
      System.out.println("fuck off");
    }
  }

  static int traceNode(node node1)
  {
    int length = 0;
    while(node1.hasParent())
    {
      length ++;
      node1 = node1.getParent();
    }
    return length;
  }
}
