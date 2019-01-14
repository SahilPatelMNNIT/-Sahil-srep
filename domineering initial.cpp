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
int minimax(int board[13][13],int player,int depth,int alpha,int beta)
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
                    score=calmoves(board,-1)+minimax(board,-1,depth+1,alpha,best);
                    best=max(best,score);
                    alpha=max(alpha,best);
                    board[i][j]=0;
                    board[i][j+1]=0;
                    if(alpha>=beta)
                        break;
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
                    score=calmoves(board,1)+minimax(board,1,depth+1,alpha,beta);
                    best=min(score,best);
                    beta=min(beta,best);
                    board[i][j]=0;
                    board[i+1][j]=0;
                    if(beta<=alpha)
                        break;
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
                int moveval=calmoves(board,-1)+minimax(board,-1,0,-1000,1000);
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
    int bestval=1000;
    for(int i=0;i<12;i++)
    {
        for(int j=0;j<13;j++)
        {
            if(board[i][j]==0&&board[i+1][j]==0)
            {
                board[i][j]=-1;
                board[i+1][j]=-1;
                int moveval=calmoves(board,1)+minimax(board,1,0,-1000,1000);
                board[i][j]=0;
                board[i+1][j]=0;
                if(bestval>moveval)
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
void display(int board[13][13])
{
    for(int i=0;i<13;i++)
    {
        for(int j=0;j<13;j++)
        {
            cout<<board[i][j]<<" ";
        }
        cout<<endl;
    }
}
int main()
{
    int player=1;
    move p;
    int board[13][13]={0};
    while(ismovesleft(board,1)==0&&ismovesleft(board,-1)==0)
    {
        if(player==1)
        {
            cout<<"PLAYER 1 MOVE"<<endl;
            p=bestmove(board,1);
            board[p.a][p.b]=1;
            board[p.a][p.b+1]=1;
            display(board);
            player=-1;
        }
        if(player==-1)
        {
            cout<<"PLAYER 2 MOVE"<<endl;
            p=bestmove(board,-1);
            board[p.a][p.b]=-1;
            board[p.a+1][p.b]=-1;
            display(board);
            player=1;
        }
    }
    return 0;
}
