package rubiksCube;

import java.util.*;

public class Cube
{
  //creates object "cube"//
  private ArrayList<ArrayList<ArrayList<String>>> cube = new ArrayList<>();

  //Initial constructor for the "cube" object//
  public Cube()
  {
    String[] colors = new String[]{ "R", "W", "G", "B", "O", "Y"};

    for(int face = 0; face  < 6; face ++)
    {
      cube.add(new ArrayList<ArrayList<String>>(3));
      for(int row = 0; row < 3; row ++)
      {
        cube.get(face).add(new ArrayList<String>(3));
        for(int col = 0; col < 3; col ++)
        {
          cube.get(face).get(row).add(colors[face]);
        }
      }
    }
  }

  public Cube(ArrayList<ArrayList<ArrayList<String>>> original)
  {
    ArrayList<ArrayList<ArrayList<String>>> newCube = new ArrayList<>();
    for(int face = 0; face  < 6; face ++)
    {
      newCube.add(new ArrayList<ArrayList<String>>(3));
      for(int row = 0; row < 3; row ++)
      {
        newCube.get(face).add(new ArrayList<String>(3));
        for(int col = 0; col < 3; col ++)
        {
          newCube.get(face).get(row).add(original.get(face).get(row).get(col));
        }
      }
    }
    this.cube = newCube;
  }

  public ArrayList<ArrayList<ArrayList<String>>> getCube()
  {
    return cube;
  }
  //Prints out the cube with the faces labeled//
  public void printCube()
  {
    String[] faceName = {"Front","Top","Back","Bottom", "Left","Right"};
    for(int i = 0; i < 6; i ++)
    {
      System.out.println("\n" + faceName[i] + "\n" + "________");
      for(int row = 0; row < 3; row ++)
      {
        for(int col = 0; col < 3; col ++)
        {
          System.out.print(cube.get(i).get(row).get(col) + "  ");
        }
        System.out.println();
      }
    }
  }

  //Rotates the given row "row" right by one (also rotates faces properly)//
  public void rowRight(int row)
  {
    if(row == 0)
    {
      _rotateRowRight(row);
      rotateCounterClockwise(1);
    }
    if(row == 1)
    {
      _rotateRowRight(row);
    }
    if(row == 2)
    {
      _rotateRowRight(row);
      rotateCounterClockwise(3);
    }
  }

  //Used to shift row right (doesn't rotate faces)//
  public void _rotateRowRight(int row)
  {
    ArrayList<String> before = new ArrayList<String>();
    ArrayList<String> current = new ArrayList<String>();
    //Front Face = 0 Right Face = 5 Back Face = 2 Left Face = 4
    int face = 0;
    for(int col = 0; col < 3; col ++)
    {
      before.add(cube.get(4).get(row).get(col));
    }
    for(int col = 0; col < 3; col ++)
    {
      current.add(cube.get(face).get(row).get(col));
      cube.get(face).get(row).set(col, before.get(col));
    }

    Collections.copy(before, current);
    current.clear();
    face = 5;

    for(int col = 0; col < 3; col ++)
    {
      current.add(cube.get(face).get(row).get(col));
      cube.get(face).get(row).set(col, before.get(col));
    }

    Collections.copy(before, current);
    current.clear();
    face = 2;

    for(int col = 0; col < 3; col ++)
    {
      current.add(cube.get(face).get(row).get(col));
      cube.get(face).get(row).set(col, before.get(col));
    }

    Collections.copy(before, current);
    current.clear();
    face = 4;

    for(int col = 0; col < 3; col ++)
    {
      current.add(cube.get(face).get(row).get(col));
      cube.get(face).get(row).set(col, before.get(col));
    }
  }

  //Rotates the given row "row" left one (also rotates faces properly)//
  public void rowLeft(int row)
  {
    if(row == 0)
    {
      _rotateRowLeft(row);
      rotateClockwise(1);
    }
    if(row == 1)
    {
      _rotateRowLeft(row);
    }
    if(row == 2)
    {
      _rotateRowLeft(row);
      rotateClockwise(3);
    }
  }

  //Used to rotate given row one to the left (doesn't rotate the faces)//
  public void _rotateRowLeft(int row)
  {
    ArrayList<String> before = new ArrayList<String>();
    ArrayList<String> current = new ArrayList<String>();
    //Front Face = 0 Right Face = 5 Back Face = 2 Left Face = 4
    int face = 0;
    for(int col = 0; col < 3; col ++)
    {
      before.add(cube.get(5).get(row).get(col));
    }
    for(int col = 0; col < 3; col ++)
    {
      current.add(cube.get(face).get(row).get(col));
      cube.get(face).get(row).set(col, before.get(col));
    }

    Collections.copy(before, current);
    current.clear();
    face = 4;

    for(int col = 0; col < 3; col ++)
    {
      current.add(cube.get(face).get(row).get(col));
      cube.get(face).get(row).set(col, before.get(col));
    }

    Collections.copy(before, current);
    current.clear();
    face = 2;

    for(int col = 0; col < 3; col ++)
    {
      current.add(cube.get(face).get(row).get(col));
      cube.get(face).get(row).set(col, before.get(col));
    }

    Collections.copy(before, current);
    current.clear();
    face = 5;

    for(int col = 0; col < 3; col ++)
    {
      current.add(cube.get(face).get(row).get(col));
      cube.get(face).get(row).set(col, before.get(col));
    }
  }

  //Rotates given column "col" down once (also rotates faces properly)//
  public void columnDown (int col)
  {
    if(col == 0)
    {
      _rotateColumnDown(col);
      rotateCounterClockwise(4);
    }
    if (col == 1)
    {
      _rotateColumnDown(col);
    }
    if (col == 2)
    {
      _rotateColumnDown(col);
      rotateCounterClockwise(5);
    }
  }

  //Used to rotate given column down one (doesn't rotate the faces properly)//
  public void _rotateColumnDown(int col)
	{
		ArrayList<String> before = new ArrayList<String>();
		ArrayList<String> current = new ArrayList<String>();

		for(int face = 3; face >= 0; face --)
		{
			if(face == 3)
			{
				for(int row = 0; row < 3; row ++)
				{
					before.add(cube.get(0).get(row).get(col));
				}
				for(int row = 0; row < 3; row ++)
				{
					current.add(cube.get(face).get(row).get(col));
					cube.get(face).get(row).set(col, before.get(row));
				}
				Collections.copy(before, current);
				current.clear();
			}
			else
			{
				for(int row = 0; row < 3; row ++)
				{
					current.add(cube.get(face).get(row).get(col));
					cube.get(face).get(row).set(col, before.get(row));
				}
				Collections.copy(before, current);
				current.clear();
			}
		}
	}

  //Roates given column "col" up once (also rotates faces properly)//
  public void columnUp (int col)
  {
    if(col == 0)
    {
      _rotateColumnUp(col);
      rotateClockwise(4);
    }
    if(col == 1)
    {
      _rotateColumnUp(col);
    }
    if(col == 2)
    {
      _rotateColumnUp(col);
      rotateClockwise(5);
    }
  }

  //Used to rotate given column up one (doesn't rotate the faces properly)//
  public void _rotateColumnUp(int col)
  {
    ArrayList<String> before = new ArrayList<String>();
    ArrayList<String> current = new ArrayList<String>();
    for(int face = 0; face < 4; face ++)
    {
      if(face == 0)
      {
        for(int row = 0; row < 3; row ++)
        {
          before.add(cube.get(3).get(row).get(col));
        }
        for(int row = 0; row < 3; row ++)
        {
          current.add(cube.get(face).get(row).get(col));
          cube.get(face).get(row).set(col, before.get(row));
        }
        Collections.copy(before, current);
        current.clear();
      }
      else
      {
        for(int row = 0; row < 3; row ++)
        {
          current.add(cube.get(face).get(row).get(col));
          cube.get(face).get(row).set(col, before.get(row));
        }
        Collections.copy(before, current);
        current.clear();
      }
    }
  }

  //Scrambles cube "scrams" amount of times//
  public void scramble (int scrams)
  {
    int level;
    int move;
    Random rand = new Random();
    for(int i = 0; i < scrams; i ++)
    {
      move = rand.nextInt(4);
      switch(move)
      {
      case 0 :
        level = rand.nextInt(3);
        columnUp(level);
        break;

      case 1 :
        level = rand.nextInt(3);
        columnDown(level);
        break;

      case 2 :
        level = rand.nextInt(3);
        rowRight(level);
        break;

      case 3 :
        level = rand.nextInt(3);
        rowLeft(level);
        break;
      }
    }
  }

  //Adds abstraciton to interpreting moves on the cube//
  public void interpretMove (String move)
	{
		if(move.equals("D0"))
		{
			columnDown(0);
		}
		if(move.equals("D1"))
		{
			columnDown(1);
		}
		if(move.equals("D2"))
		{
			columnDown(2);
		}
		if(move.equals("U0"))
		{
			columnUp(0);
		}
		if(move.equals("U1"))
		{
			columnUp(1);
		}
		if(move.equals("U2"))
		{
			columnUp(2);
		}
		if(move.equals("L0"))
		{
			rowLeft(0);
		}
		if(move.equals("L1"))
		{
			rowLeft(1);
		}
		if(move.equals("L2"))
		{
			rowLeft(2);
		}
		if(move.equals("R0"))
		{
			rowRight(0);
		}
		if(move.equals("R1"))
		{
			rowRight(1);
		}
		if(move.equals("R2"))
		{
			rowRight(2);
		}
	}

  //----GETTERS AND SETTERS---//

  //Gets entire face "face" and returns it//
  public ArrayList<ArrayList<String>> _getFace(int face)
  {
    return cube.get(face);
  }

  //Gets specific cell at face "face", row "row", and column "col"//
  public String _getCell(int face, int row, int col)
  {
    return cube.get(face).get(row).get(col);
  }

  //Sets given int face on cube to the given 2D arraylist setFace//
  public void _setFace(int face, ArrayList<ArrayList<String>> setFace)
  {
    for(int row = 0; row < 3; row ++)
    {
      for(int col = 0; col < 3; col ++)
      {
        cube.get(face).get(row).set(col, setFace.get(row).get(col));
      }
    }
  }

  //counter clockwise//
  public void rotateCounterClockwise(int face)
  {
    ArrayList<ArrayList<String>> rotatedFace = new ArrayList<>();
    for(int row = 0; row < 3; row ++)
    {
      _setRow(rotatedFace, row,  _getCol(face, 2 - row));
    }
    _setFace(face, rotatedFace);
  }

  //Sets given row on given face to given row//
  public static void _setRow (ArrayList<ArrayList<String>> face, int r, ArrayList<String> row)
  {
    face.add(row);
  }

  //Rotate given face clock-wise//
  public void rotateClockwise(int face)
  {
    ArrayList<ArrayList<String>> rotatedFace = new ArrayList<>();
    for(int col = 0; col < 3; col ++)
    {
      _setColumn(rotatedFace, col, _getRow(face, 2 - col));
    }
    _setFace(face, rotatedFace);
  }

  //Gets entire row "row" and returns it//
  public ArrayList<String> _getRow(int face, int row)
  {
    return cube.get(face).get(row);
  }

  //Gets entire column and returns it//
  public ArrayList<String> _getCol(int face, int col)
  {
    ArrayList<String> column = new ArrayList<>();
    for(int row = 0; row < 3; row ++)
    {
      column.add(cube.get(face).get(row).get(col));
    }
    return column;
  }

  //Sets given column on given face to given col//
  public static void _setColumn (ArrayList<ArrayList<String>> face, int col, ArrayList<String> column)
  {
    for(int row = 0; row  < 3; row ++)
    {
      face.add(new ArrayList<String>(3));
      face.get(row).add(column.get(row));
    }
  }

  public String getHash()
  {
    int hash = 0;
    int index = 1;
    for (int face = 0; face < 6; face ++)
    {
      for(int row = 0; row < 3; row ++)
      {
        for(int col = 0; col < 3; col ++)
        {
          int ascii = (int) cube.get(face).get(row).get(col).charAt(0);
          hash += index * ascii;
          index ++;
        }
      }
    }
    return String.valueOf(hash);
  }
}
