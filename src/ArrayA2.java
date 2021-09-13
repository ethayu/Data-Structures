public class ArrayA2 {
    public static void main(String[] args) {
        //R7.27
        final int ROWS = 9, COLUMNS = 9;
        int[][] values = new int[ROWS][COLUMNS];
        //a.
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[0].length; j++) {
                values[i][j] = 0;
            }
        }
        //b.
        boolean x = true;
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[0].length; j++) {
                x = !x;
                values[i][j] = x ? 0 : 1;
            }
        }
        //c.
        for (int i = 0; i < values[0].length; i++) {
            values[0][i] = 0;
            values[values.length - 1][i] = 0;
        }
        //d.
        int sum = 0;
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[0].length; j++) {
                sum += values[i][j];
            }
        }
        //e.
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[0].length; j++) {
                System.out.print(values[i][j] + " ");
            }
            System.out.println();
        }
        /**
         * R7.30
         *  a. TRUE
         *  b. FALSE
         *  c. FALSE
         *  d. FALSE
         *  e. FALSE
         *  f. TRUE, if can return modified array
         *  g. TRUE, if can return modified array
         *  h. TRUE, if can return modified array
         * R7.32
         *  a. TRUE
         *  b. TRUE
         *  c. FALSE
         *  d. TRUE
         *  e. FALSE
         *  f. FALSE
         * CODING BAT - DONE
         */
    }
}
