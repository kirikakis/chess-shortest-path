package com.github.kirikakis.chess;

// ChessCoordinate provides an abstraction for valid chess position on a square chess board of square BOARD_SIZE
// Can be setup using algebraic chess notation
public class ChessCoordinate
{

	/**
	 * Maps the integer horizontal co-ordinates to constant letter representations from algebra co-ordinates
	 */
	enum AlgebraicXCoordinate {
		A(1), B(2), C(3), D(4), E(5), F(6), G(7), H(8);

		private int xCoordinate;

		//Map each enum value to an integer representing the x-coordinate
		AlgebraicXCoordinate(final int coordinate)
		{

			this.xCoordinate = coordinate;
		}

		public int getValue()
		{
			return this.xCoordinate;
		}

		public static AlgebraicXCoordinate fromInt(int coordinate) 
		{
		    for (AlgebraicXCoordinate algebraCoordinate : AlgebraicXCoordinate.values()) 
		    {
		        if (coordinate == algebraCoordinate.getValue()) 
		        {
		        	return algebraCoordinate;
		        }
		    }

		    throw new IllegalArgumentException("Invalid position co-ordinate for algebraic chess notation.");
		}
	}

	public int x; //horizontal coordinate
	public int y; //vertical coordinate

	public static final int BOARD_SIZE = 8; //size of one side of the square chessboard


	/**
	 * Constructor for a x,y position coordinate
	 * @param xCoordinate
	 * @param yCoordinate
	 * @throws IllegalArgumentException if co-ordinate is out of range
	 */
	public ChessCoordinate(int xCoordinate, int yCoordinate) throws IllegalArgumentException
	{
		if (isValidCoordinate(xCoordinate,yCoordinate))
		{
			x = xCoordinate;
			y = yCoordinate;
		} else {
			throw new IllegalArgumentException("Invalid position co-ordinate. Board size is: " + BOARD_SIZE + " by " + BOARD_SIZE);
		}
	}

	/**
	 * Constructor for a position coordinate from algebraic chess notation
	 * @param algebraCoordinate (eg. A8 => x=1,y=8 , B2 => x=2,y=2 ...)
	 * @throws IllegalArgumentException if co-ordinate is out of range or AlgebraicXCoordinate is not present
	 */
	public ChessCoordinate(String algebraCoordinate) throws IllegalArgumentException
	{
		if (algebraCoordinate.length() == 2)
		{
			try {
				x = AlgebraicXCoordinate.valueOf(algebraCoordinate.substring(0,1).toUpperCase()).getValue(); //Use AlgebraicXCoordinate enum
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException("Invalid horizontal algebraic co-ordinate. Board size is: " + BOARD_SIZE + " by " + BOARD_SIZE);
			}

			try {
				y = Integer.parseInt(algebraCoordinate.substring(1));
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException("Invalid vertical position co-ordinate. Board size is: " + BOARD_SIZE + " by " + BOARD_SIZE);
			}

			if (!isValidCoordinate(x,y))
			{
				throw new IllegalArgumentException("Invalid algebraic position co-ordinate. Board size is: " + BOARD_SIZE + " by " + BOARD_SIZE);
			}

		} else {
			throw new IllegalArgumentException("Invalid algebraic length. Board size is: " + BOARD_SIZE + " by " + BOARD_SIZE);
		}

	}

	/**
	 * Is the coordinate a valid position on the defined board size
	 * @param xCoordinate
	 * @param yCoordinate
	 * @return true if co-ordinate in range
	 */
	public static boolean isValidCoordinate(int xCoordinate, int yCoordinate)
	{
        return xCoordinate >= 1 && xCoordinate <= BOARD_SIZE && yCoordinate >= 1 && yCoordinate <= BOARD_SIZE;
	}

	/**
	 * When does one ChessCoordinate equal another ChessCoordinate
	 * Required method to override for use with 'contains' in collections
	 * @param other ChessCoordinate Object
	 * @return boolean
	 */
	public boolean equals(Object other)
	{
	    if(other instanceof ChessCoordinate) {
            ChessCoordinate another = (ChessCoordinate) other;

            return another.x == this.x && another.y == this.y;
        }
        else {
	        return false;
        }
	}

	/**
	 * Allows for storage of class in Hash based collections
	 * Builds a unique int as the hashcode based on concatenating the string representation of each part of the coordinate
	 * @return int that is unique for each ChessCoordinate
	 */
	public int hashCode()
	{
		String xString = String.valueOf(this.x);
		String yString = String.valueOf(this.y);
		String stringCoordinate = xString + yString;
		return Integer.parseInt(stringCoordinate);
	}

	/**
	 * Return the algebraic notation of the object's chess co-ordinate
	 * @return string algebraic notation
	 */
	public String toString()
	{
		String xCoordinate = AlgebraicXCoordinate.fromInt(this.x).name();
		String yCoordinate = String.valueOf(this.y);
		return (xCoordinate + yCoordinate);
	}

}