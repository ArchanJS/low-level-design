package lld;
public class TicTacToe{
    private final int board[][];
    private final int n;
    private final int rowSum[];
    private final int colSum[];
    private int diag;
    private int revDiag;
    private int winner;

    public TicTacToe(final int n){
        this.n=n;
        board=new int[n][n];
        rowSum=new int[n];
        colSum=new int[n];
        winner=-1;
        diag=0;
        revDiag=0;
    }

    public int move(int player, int row, int col) throws IllegalArgumentException{
        if(row < 0 || col < 0 || row >= n || col >= n) throw new IllegalArgumentException("Out of bound");
        else if(board[row][col]!=-1) throw new IllegalArgumentException("Cell already filled");
        else if(player != 0 && player != 1) throw new IllegalArgumentException("Invalid player");
        else if(getWinner() != -1) return getWinner();
        else if(isOver() && getWinner() == -1) throw new IllegalArgumentException("Draw");
        int addVal = player == 0?-1:1;
        if(row == col) diag+=addVal;
        if(row == n-col-1) revDiag+=addVal;
        rowSum[row] += addVal;
        colSum[col] += addVal;
        if(Math.abs(rowSum[row]) == n || Math.abs(colSum[col]) == n || Math.abs(diag) == n || Math.abs(revDiag) == n) {
            winner=player;
        }
        return getWinner();
    }
    public boolean isOver(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++) {
                if(board[i][j]==0) return false;
            }
        }
        return true;
    }
    public int getWinner(){
        return winner;
    }
}