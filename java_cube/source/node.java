package rubiksCube;

import java.util.ArrayList;
import java.util.List;

public class node
{
  private node parentNode;
  private ArrayList<node> childList;
  private Cube data;
  private float sim;
  private String move;

  public node(node parent, ArrayList<node> children, Cube data, String move)
  {
    parentNode = parent;
    data = data;
    childList = children;
    sim = calcSim(data);
    childList = new ArrayList<node>();
    move = move;
  }

  public node(Cube c)
  {
    data = c;
    sim = calcSim(c);
    childList = new ArrayList<node>();
  }

  public boolean hasParent()
  {
    if(this.getParent() != null)
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  public Cube getData()
  {
    return data;
  }

  public void setData(Cube c)
  {
    data = c;
  }

  public void printData()
  {
    data.printCube();
  }

  public void makeMove(String m)
  {
    data.interpretMove(m);
  }

  public float getSim()
  {
    return sim;
  }

  public node getParent()
  {
    return parentNode;
  }

  public ArrayList<node> getChildren()
  {
    return childList;
  }

  public node getChild(int num)
  {
    return childList.get(num);
  }

  public void setParent(node parent)
  {
    parentNode = parent;
  }

  public String getMove()
  {
    return move;
  }

  public void setMove(String moves)
  {
    move = moves;
  }

  public void addChild(node child)
  {
    childList.add(child);
    child.parentNode = this;
  }

  static float calcSim(Cube data)
  {
    Cube perfectCube = new Cube();
    int total = 0;
    for(int face = 0; face < 6; face ++)
    {
      for(int row = 0; row < 3; row ++)
      {
        for(int col = 0; col < 3; col ++)
        {
          String center = data._getCell(face, 1, 1);
          if(data._getCell(face, row, col).equals(center))
          {
            total ++;
          }
        }
      }
    }
    return total / 54;
  }

  public ArrayList<String> getMovesToNode()
  {
    // creates an arraylist to store the move set to get to current cube //
    ArrayList<String> moveSet = new ArrayList<String>();
    moveSet.add(move);

    node currentNode = this;

    // tracks wether or not the current node found is the "head node" //
    boolean headNode = false;
    while(headNode == false)
    {
      node parentNode = currentNode.getParent();
      if(parentNode != null)
      {
        moveSet.add(parentNode.getMove());
        currentNode = parentNode;
      }
      else
      {
        headNode = true;
      }
    }
    return moveSet;
  }
}
