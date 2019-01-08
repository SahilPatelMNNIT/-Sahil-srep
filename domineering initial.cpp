#include<iostream>
using namespace std;
struct move{
int a;
int b;
};
move pos;
int calmoves(int board[13][13],int player)
{
    int score=0;
    if(player==1)
    {
        for(int i=0;i<13;i++)
        {
            for(int j=0;j<12;j++)
            {
                if(board[i][j]==0&&board[i][j+1]==0&&board[i+1][j]!=0)
                    score=score+2;
                else
                    if(board[i][j]==0&&board[i][j+1]==0&&board[i+1][j]==0)
                    score=score+1;
            }
        }
        return score;
    }
    if(player==-1)
    {
        for(int i=0;i<12;i++)
        {
            for(int j=0;j<13;j++)
            {
                if(board[i][j]==0&&board[i+1][j+1]==0&&board[i][j+1]!=0)
                    score=score-2;
                else
                    if(board[i][j]==0&&board[i+1][j]==0&&board[i][j+1]==0)
                    score=score-1;
            }
        }
        return score;
    }

}
int ismovesleft(int board[13][13],int player)
{
    int check=1;
    if(player==1)
    {
    for(int i=0;i<13;i++)
    {
        for(int j=0;j<12;j++)
        {
            if(board[i][j]==0&&board[i][j+1]==0)
            {
                check=0;
                break;
            }
        }
    }
    return check;
    }
    if(player==-1)
    {
    for(int i=0;i<12;i++)
    {
        for(int j=0;j<13;j++)
        {
            if(board[i][j]==0&&board[i+1][j]==0)
            {
                check=0;
                break;
            }
        }
    }
    return check;
    }
}
int minimax(int board[13][13],int player,int depth)
{
    int score;
    if(depth==2)
                return score=0;
    if(ismovesleft(board,1)==1&&player==1)
                return score=-1000;
    if(ismovesleft(board,-1)==1&&player==-1)
                return score=1000;
    if(player==1)
    {
        int best=-1000;
        for(int i=0;i<13;i++)
        {
            for(int j=0;j<12;j++)
            {
                if(board[i][j]==0&&board[i][j+1]==0)
                {
                    board[i][j]=1;
                    board[i][j+1]=1;
                    score=calmoves(board,-1)+minimax(board,-1,depth+1);
                    best=max(best,score);
                    board[i][j]=0;
                    board[i][j+1]=0;
                }
            }
        }
        return best;
    }
    if(player==-1)
    {
        int best=1000;
        for(int i=0;i<12;i++)
        {
            for(int j=0;j<13;j++)
            {
                if(board[i][j]==0&&board[i+1][j]==0)
                {
                    board[i][j]=-1;
                    board[i+1][j]=-1;
                    score=calmoves(board,1)+minimax(board,1,depth+1);
                    best=min(score,best);
                    board[i][j]=0;
                    board[i+1][j]=0;
                }
            }
        }
        return best;
    }
}
move bestmove(int board[13][13],int player)
{
    if(player==1)
    {
    int bestval=-1000;
    for(int i=0;i<13;i++)
    {
        for(int j=0;j<12;j++)
        {
            if(board[i][j]==0&&board[i][j+1]==0)
            {
                board[i][j]=1;
                board[i][j+1]=1;
                int moveval=calmoves(board,-1)+minimax(board,-1,0);
                board[i][j]=0;
                board[i][j+1]=0;
                if(bestval<moveval)
                {
                    bestval=moveval;
                    pos.a=i;
                    pos.b=j;
                }
            }
        }
    }
    return pos;
    }
    if(player==-1)
    {
    int bestval=-1000;
    for(int i=0;i<12;i++)
    {
        for(int j=0;j<13;j++)
        {
            if(board[i][j]==0&&board[i][j+1]==0)
            {
                board[i][j]=-1;
                board[i+1][j]=-1;
                int moveval=minimax(board,1,0);
                board[i][j]=0;
                board[i+1][j]=0;
                if(bestval<moveval)
                {
                    bestval=moveval;
                    pos.a=i;
                    pos.b=j;
                }
            }
        }
    }
    return pos;
    }
}
int main()
{
    int board[13][13]={0};
    int score=minimax(board,1,0);
    cout<<score;
    move p=bestmove(board,1);
    cout<<p.a;
    cout<<p.b;
    return 0;
}
