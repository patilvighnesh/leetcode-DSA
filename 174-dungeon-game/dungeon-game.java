class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int n = dungeon.length;
        int m = dungeon[0].length;
        Integer[][] dp =new Integer[n][m];
        return memo(0,0,dungeon,dp,n,m);
    }
    public int memo(int r,int c,int[][] dungeon,Integer[][] dp,int n,int m){
        if(r==n-1 && c==m-1){
            if(dungeon[r][c]<=0){
                return Math.abs(dungeon[r][c])+1;
            }
            return 1;
        }
        if(r>=n || c>=m){
            return (int)1e8;
        }
        if(dp[r][c]!=null) return dp[r][c];
        int down = memo(r+1,c,dungeon,dp,n,m);
        int right = memo(r,c+1,dungeon,dp,n,m);
        int result = Math.min(down,right) - dungeon[r][c];
        return dp[r][c] = (result<=0)?1:result;
    }
}