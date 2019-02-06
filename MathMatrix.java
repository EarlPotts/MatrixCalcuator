package earlp.com.matrixcalcuator;//  MathMatrix.java - CS314 Assignment 2


/**
 * A class that models systems of linear equations (Math Matrices)
 * as used in linear algebra.
 *
 * @version Skeleton file for students
 */
public class MathMatrix
{
    // instance variables
    /*CS314 STUDENTS: ADD YOUR CODE HERE*/
    private int[][] values;
    private int numRows;
    private int numCols;


    public MathMatrix(int[][] mat) {
        numRows = mat.length;
        numCols = mat[0].length;
        
        values = new int[mat.length][mat[0].length];
        
        for (int r = 0; r < mat.length; r++) {
			for (int c = 0; c < mat[0].length; c++) {
				values[r][c] = mat[r][c];
			}
		}
    }


    //constructor that creates matrix with given rows columns and values
    public MathMatrix(int rows, int cols, int initialVal) {
        /*CS314 STUDENTS: ADD YOUR CODE HERE*/
        numRows = rows;
        numCols = cols;
    	values = new int[numRows][numCols];

    	for (int r = 0; r < numRows; r++) {
			for (int c = 0; c < numCols; c++) {
				values[r][c] = initialVal;
			}
		}
    }

    //returns a string of the Matrix
//    public String toString(){
//        String result = "";
//        for (int r = 0; r < numRows; r++) {
//            result += "[";
//            for (int c = 0; c < numCols; c++) {
//                result += " " + values[r][c];
//            }
//            result+= " ]\n";
//        }
//        return result;
//    }

    //changes specific element in the matrix
    public void changeElement(int row, int col, int newValue) {
        /*CS314 STUDENTS: ADD YOUR CODE HERE*/
    	values[row][col] = newValue;
    }


    //return number of Rows
    public int numRows() {
        /*CS314 STUDENTS: ADD YOUR CODE HERE*/
        return values.length; // alter as necessary
    }


    //return num of columns
    public int numCols() {
        /*CS314 STUDENTS: ADD YOUR CODE HERE*/
        return values[0].length; // alter as necessary
    }


    //returns value from matrix
    public int getVal(int row, int col) {
        /*CS314 STUDENTS: ADD YOUR CODE HERE*/
        return values[row][col]; // alter as necessary
    }


   //adds two matrices
    public MathMatrix add(MathMatrix rightHandSide) {
        /*CS314 STUDENTS: ADD YOUR CODE HERE*/
    	MathMatrix result = new MathMatrix(rightHandSide.numRows(), rightHandSide.numCols(), 0);
    	for (int r = 0; r < result.numRows(); r++) {
			for (int c = 0; c < result.numCols(); c++) {
				result.changeElement(r, c, rightHandSide.getVal(r, c) + values[r][c]);
			}
		}
        return result; // alter as necessary
    }


   //subtracts a matrix from the current matrix
    public MathMatrix subtract(MathMatrix rightHandSide) {
        /*CS314 STUDENTS: ADD YOUR CODE HERE*/
    	MathMatrix result = new MathMatrix(rightHandSide.numRows(), rightHandSide.numCols(), 0);
    	for (int r = 0; r < result.numRows(); r++) {
			for (int c = 0; c < result.numCols(); c++) {
				result.changeElement(r, c, rightHandSide.getVal(r, c) - values[r][c]);
			}
		}
        return result; // alter as necessary
    }


    public MathMatrix multiply(MathMatrix rightHandSide) {
        /*CS314 STUDENTS: ADD YOUR CODE HERE*/
        MathMatrix result = new MathMatrix(values.length, rightHandSide.numCols(), 0);

        for (int r = 0; r < result.numRows(); r++) {
            for (int c = 0; c < result.numCols(); c++) {
                int number = 0;
                for(int i = 0; i < values[0].length; i++) {
                    number += values[r][i] * rightHandSide.getVal(i, c);
                }
                result.changeElement(r, c, number);

            }
        }
        return result; // alter as necessary
    }


    //multiply all elements in the matrix by a scale factor
    public void scale(int factor) {
        /*CS314 STUDENTS: ADD YOUR CODE HERE*/
        for (int r = 0; r < values.length; r++) {
            for (int c = 0; c < values[0].length; c++) {
                values[r][c] *= factor;
            }
        }
    }


    //returns the transpose of the matrix
    public MathMatrix getTranspose() {
        /*CS314 STUDENTS: ADD YOUR CODE HERE*/
        MathMatrix result = new MathMatrix(values[0].length, values.length, 0);

        for (int r = 0; r < values[0].length; r++) {
            for (int c = 0; c < values.length; c++) {
                result.changeElement(r, c, values[c][r]);
            }
        }
        return result; // alter as necessary
    }


    //returns true if two matrices are equal
    public boolean equals(Object rightHandSide) {
        /* CS314 Students. The following is standard equals
         * method code. We will learn about it in a few weeks.
         */
        if( rightHandSide != null && this.getClass() == rightHandSide.getClass()){
            // rightHandSide is a non null MathMatrix
            MathMatrix otherMatrix = (MathMatrix)rightHandSide;

            // cs314 students: determine if otherMatrix is equal
            // to this MathMatrix and set result to true if they are.

            /*CS314 STUDENTS: ADD YOUR CODE HERE*/
            for (int r = 0; r < values.length; r++) {
                for (int c = 0; c < values[0].length; c++) {
                    if(values[r][c] != otherMatrix.getVal(r, c))
                        return false;
                }
            }
        }
        return true;
    }


    //turns matrix into String
    public String toString(){
        /*CS314 STUDENTS: ADD YOUR CODE HERE*/
        String result = "";
        for (int r = 0; r < values.length; r++) {
            result += "|";
            for (int c = 0; c < values[0].length; c++) {
                String length = longestNum() + "";
                String s = String.format("%" + length + "d", values[r][c]);
                result += " " + s;

            }
            result+= "|\n";
        }
        return result;// alter as necessary
    }

    //returns the longest length of a number in a matrix
    private int longestNum() {
        int longest = 0;
        for (int r = 0; r < values.length; r++) {
            for (int c = 0; c < values[0].length; c++) {
                String num = values[r][c] + "";
                if(num.length() > longest)
                    longest = num.length();
            }
        }
        return longest;
    }

    //returns whether or not the matrix is upper Triangular
    public boolean isUpperTriangular() {
        /*CS314 STUDENTS: ADD YOUR CODE HERE*/
        if(values.length != values[0].length)
            return false;
        for(int i = 0; i < values.length; i++) {
            //check all values below the pivot
            for(int j = i + 1; j < values.length; j++) {
                if(values[j][i] != 0)
                    return false;
            }
        }
        return true; // alter as necessary
    }

    //Swaps two rows of the array
    public void rowSwap(int rowOne, int rowTwo){
        int[] temp = new int[values[0].length];

        for (int c = 0; c < values[0].length; c++) {
            temp[c] = values[rowOne][c];
        }

        for (int c = 0; c < values[0].length; c++) {
            values[rowOne][c] = values[rowTwo][c];
        }

        for (int c = 0; c < values[0].length; c++) {
            values[rowTwo][c] = temp[c];
        }


    }


}