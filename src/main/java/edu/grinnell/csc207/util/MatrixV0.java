package edu.grinnell.csc207.util;

/**
 * An implementation of two-dimensional matrices.
 *
 * @author Slok Rajbhandari
 * @author Samuel A. Rebelsky
 *
 * @param <T>
 *   The type of values stored in the matrix.
 */
public class MatrixV0<T> implements Matrix<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The width of the matrix.
   */
  private int width;

  /**
   * The height of the matrix.
   */
  private int height;

  /**
   * 2D array to store matrix data.
   */
  private T[][] data;

  /**
   * Default value for matrix elements when not set.
   */
  private T defaultValue;


  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new matrix of the specified width and height with the
   * given value as the default.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   * @param def
   *   The default value, used to fill all the cells.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  public MatrixV0(int width, int height, T def) {
    if (width < 0 || height < 0) {
      throw new NegativeArraySizeException("Width and height must be non-negative.");
    } // if
    this.width = width; // Set the width
    this.height = height; // Set the height
    this.defaultValue = def; // Set the default value
    data = (T[][]) new Object[height][width]; // Create 2D array
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        data[i][j] = def; // Fill each cell with default value
      } // for
    } // for
  } // MatrixV0(int, int, T)

  /**
   * Create a new matrix of the specified width and height with
   * null as the default value.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  public MatrixV0(int width, int height) {
    this(width, height, null); // Call the other constructor with null default value
  } // MatrixV0

  // +--------------+------------------------------------------------
  // | Core methods |
  // +--------------+

  /**
   * Get the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   *
   * @return the value at the specified location.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public T get(int row, int col) {
    if (row < 0 || row >= height || col < 0 || col >= width) {
      throw new IndexOutOfBoundsException("Row or column is out of bounds.");
    } // if
    return data[row][col]; // Return the element at specified position
  } // get(int, int)

  /**
   * Set the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   * @param val
   *   The value to set.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public void set(int row, int col, T val) {
    if (row < 0 || row >= height || col < 0 || col >= width) {
      throw new IndexOutOfBoundsException("Row or column is out of bounds.");
    } // if
    data[row][col] = val; // Set the element at specified position
  } // set(int, int, T)

  /**
   * Determine the number of rows in the matrix.
   *
   * @return the number of rows.
   */
  public int height() {
    return height; // Return the number of rows
  } // height()

  /**
   * Determine the number of columns in the matrix.
   *
   * @return the number of columns.
   */
  public int width() {
    return width; // Return the number of columns
  } // width()

  /**
   * Insert a row filled with the default value.
   *
   * @param row
   *   The number of the row to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   */
  public void insertRow(int row) {
    if (row < 0 || row > height) {
      throw new IndexOutOfBoundsException("Invalid row index.");
    } // if
    T[][] newData = (T[][]) new Object[height + 1][width]; // Create new array with additional row
    for (int i = 0; i < row; i++) {
      newData[i] = data[i]; // Copy rows up to the insertion point
    } // for
    newData[row] = (T[]) new Object[width]; // Insert new row with default values
    for (int j = 0; j < width; j++) {
      newData[row][j] = defaultValue; // Fill new row with default values
    } // for
    for (int i = row + 1; i <= height; i++) {
      newData[i] = data[i - 1]; // Copy remaining rows after insertion point
    } // for
    data = newData; // Update reference
    height++; // Increase height
  } // insertRow(int)

  /**
   * Insert a row filled with the specified values.
   *
   * @param row
   *   The number of the row to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the width of the matrix.
   */
  public void insertRow(int row, T[] vals) throws ArraySizeException {
    if (row < 0 || row > height) {
      throw new IndexOutOfBoundsException("Invalid row index.");
    } // if
    if (vals.length != width) {
      throw new ArraySizeException("Size of values array does not match matrix width.");
    } // if
    T[][] newData = (T[][]) new Object[height + 1][width]; // Create new array with additional row
    for (int i = 0; i < row; i++) {
      newData[i] = data[i]; // Copy rows up to insertion point
    } // for
    newData[row] = vals; // Insert new row with provided values
    for (int i = row + 1; i <= height; i++) {
      newData[i] = data[i - 1]; // Copy remaining rows after insertion point
    } // for
    data = newData; // Update reference
    height++; // Increase height
  } // insertRow(int, T[])

  /**
   * Insert a column filled with the default value.
   *
   * @param col
   *   The number of the column to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   */
  public void insertCol(int col) {
    if (col < 0 || col > width) {
      throw new IndexOutOfBoundsException("Invalid column index.");
    } // if
    T[][] newData = (T[][]) new Object[height][width + 1];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < col; j++) {
        newData[i][j] = data[i][j]; // Copy columns up to insertion point
      } // for
      newData[i][col] = defaultValue; // Insert new column with default values
      for (int j = col + 1; j <= width; j++) {
        newData[i][j] = data[i][j - 1]; // Copy remaining columns after insertion point
      } // for
    } // for
    data = newData; // Update reference
    width++; // Increase width
  } // insertCol(int)
  /**
   * Insert a column filled with the specified values.
   *
   * @param col
   *   The number of the column to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the height of the matrix.
   */
  public void insertCol(int col, T[] vals) throws ArraySizeException {
    if (col < 0 || col > width) {
      throw new IndexOutOfBoundsException("Invalid column index.");
    } // if
    if (vals.length != height) {
      throw new ArraySizeException("Size of values array does not match matrix height.");
    } // if
    T[][] newData = (T[][]) new Object[height][width + 1];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < col; j++) {
        newData[i][j] = data[i][j]; // Copy columns up to insertion point
      } // for
      newData[i][col] = vals[i]; // Insert new column with provided values
      for (int j = col + 1; j <= width; j++) {
        newData[i][j] = data[i][j - 1]; // Copy remaining columns after insertion point
      } // for
    } // for
    data = newData; // Update reference
    width++; // Increase width
  } // insertCol(int, T[])

  /**
   * Delete a row.
   *
   * @param row
   *   The number of the row to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than or equal to the height.
   */
  public void deleteRow(int row) {
    if (row < 0 || row >= height) {
      throw new IndexOutOfBoundsException("Invalid row index.");
    } // if
    T[][] newData = (T[][]) new Object[height - 1][width]; // Create new array with one less row
    for (int i = 0; i < row; i++) {
      newData[i] = data[i]; // Copy rows up to deletion point
    } // for
    for (int i = row; i < height - 1; i++) {
      newData[i] = data[i + 1]; // Copy remaining rows after deletion point
    } // for
    data = newData; // Update reference
    height--; // Decrease height
  } // deleteRow(int)

  /**
   * Delete a column.
   *
   * @param col
   *   The number of the column to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than or equal to the width.
   */
  public void deleteCol(int col) {
    if (col < 0 || col >= width) {
      throw new IndexOutOfBoundsException("Invalid column index.");
    } // if
    T[][] newData = (T[][]) new Object[height][width - 1]; // Create new array with one less column
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < col; j++) {
        newData[i][j] = data[i][j]; // Copy columns up to deletion point
      } // for
      for (int j = col; j < width - 1; j++) {
        newData[i][j] = data[i][j + 1]; // Copy remaining columns after deletion point
      } // for
    } // for
    data = newData; // Update reference
    width--; // Decrease width
  } // deleteCol(int)

  /**
   * Fill a rectangular region of the matrix.
   *
   * @param startRow
   *   The top edge / row to start with (inclusive).
   * @param startCol
   *   The left edge / column to start with (inclusive).
   * @param endRow
   *   The bottom edge / row to stop with (exclusive).
   * @param endCol
   *   The right edge / column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillRegion(int startRow, int startCol, int endRow, int endCol, T val) {
    for (int i = startRow; i < endRow; i++) {
      for (int j = startCol; j < endCol; j++) {
        data[i][j] = val; // Fill each element in the specified region
      } // for
    } // for
  } // fillRegion(int, int, int, int, T)

  /**
   * Fill a line (horizontal, vertical, diagonal).
   *
   * @param startRow
   *   The row to start with (inclusive).
   * @param startCol
   *   The column to start with (inclusive).
   * @param deltaRow
   *   How much to change the row in each step.
   * @param deltaCol
   *   How much to change the column in each step.
   * @param endRow
   *   The row to stop with (exclusive).
   * @param endCol
   *   The column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillLine(int startRow, int startCol, int deltaRow,
      int deltaCol, int endRow, int endCol, T val) {
    int row = startRow;
    int col = startCol;
    while (row < endRow && col < endCol) {
      data[row][col] = val; // Fill each element in the line
      row += deltaRow; // Move to the next row
      col += deltaCol; // Move to the next column
    } // while
  } // fillLine(int, int, int, int, int, int, T)

  /**
   * A make a copy of the matrix. May share references (e.g., if individual
   * elements are mutable, mutating them in one matrix may affect the other
   * matrix) or may not.
   *
   * @return a copy of the matrix.
   */
  public Matrix clone() {
    MatrixV0<T> cloneMatrix = new MatrixV0<>(width, height, defaultValue);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        cloneMatrix.set(i, j, data[i][j]); // Copy each element to the cloned matrix
      } // for
    } // for
    return cloneMatrix; // Return the cloned matrix
  } // clone()

  /**
   * Determine if this object is equal to another object.
   *
   * @param other
   *   The object to compare.
   *
   * @return true if the other object is a matrix with the same width,
   * height, and equal elements; false otherwise.
   */
  public boolean equals(Object other) {
    if (this == other) {
      return true; // If they are the same object, return true
    } // if
    if (!(other instanceof MatrixV0)) {
      return false; // If the other object is not a MatrixV0, return false
    } // if
    MatrixV0<?> otherMatrix = (MatrixV0<?>) other; // Cast to MatrixV0
    if (width != otherMatrix.width || height != otherMatrix.height) {
      return false; // If dimensions are different, return false
    } // if
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (!data[i][j].equals(otherMatrix.get(i, j))) {
          return false; // If any element is different, return false
        } // if
      } // for
    } // for
    return true; // If all elements match, return true
  } // equals(Object)

  /**
   * Compute a hash code for this matrix. Included because any object
   * that implements `equals` is expected to implement `hashCode` and
   * ensure that the hash codes for two equal objects are the same.
   *
   * @return the hash code.
   */
  public int hashCode() {
    int multiplier = 7;
    int code = this.width() + multiplier * this.height();
    for (int row = 0; row < this.height(); row++) {
      for (int col = 0; col < this.width(); col++) {
        T val = this.get(row, col);
        if (val != null) {
          // It's okay if the following computation overflows, since
          // it will overflow uniformly.
          code = code * multiplier + val.hashCode();
        } // if
      } // for col
    } // for row
    return code;
  } // hashCode()
} // class MatrixV0
