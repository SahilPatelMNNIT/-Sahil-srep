#include<bits/stdc++.h>
using namespace std;
char player='x';
char opponent='o';
struct pos{
int a;
int b;
};
pos bestmove;
void display(char arr[3][3])
{
    printf("\t%c\t|\t%c\t|\t%c",arr[0][0],arr[0][1],arr[0][2]);
    printf("\n");
    printf("------------------------------------------------------\n");
    printf("\t%c\t|\t%c\t|\t%c",arr[1][0],arr[1][1],arr[1][2]);
    printf("\n");
    printf("-------------------------------------------------------\n");
    printf("\t%c\t|\t%c\t|\t%c",arr[2][0],arr[2][1],arr[2][2]);
}
int evaluate(char b[3][3])
{
    for (int row = 0; row<3; row++)
    {
        if (b[row][0]==b[row][1] &&
            b[row][1]==b[row][2])
        {
            if (b[row][0]==player)
                return 1;
            else if (b[row][0]==opponent)
                return -1;
        }
    }
    for (int col = 0; col<3; col++)
    {
        if (b[0][col]==b[1][col] &&
            b[1][col]==b[2][col])
        {
            if (b[0][col]==player)
                return 1;

            else if (b[0][col]==opponent)
                return -1;
        }
    }
    if (b[0][0]==b[1][1] && b[1][1]==b[2][2])
    {
        if (b[0][0]==player)
            return 1;
        else if (b[0][0]==opponent)
            return -1;
    }

    if (b[0][2]==b[1][1] && b[1][1]==b[2][0])
    {
        if (b[0][2]==player)
            return 1;
        else if (b[0][2]==opponent)
            return -1;
    }
    return 2;

}
int isMovesLeft(char arr[3][3])
{
            int check=1;
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)
                {
                    if((arr[i][j]!='x')&&(arr[i][j]!='o'))
                        check=0;
                }
            }
            if(check==1)
                return 0;
            else
                return 2;
}
int minmax(char arr[3][3],bool isaiplayer,int depth)
{
    int score;
    if(evaluate(arr)==1)
        return score=10-depth;
    if(evaluate(arr)==-1)
        return score=-10+depth;
    if(isMovesLeft(arr)==0)
        return score=0;
    if(isaiplayer)
    {
        int best=-100;
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(arr[i][j]==' ')
                {
                    arr[i][j]=player;
                    best=max(best,minmax(arr,!isaiplayer,depth+1));
                    arr[i][j]=' ';
                }
            }
        }
        return best;
    }
    else
    {
        int best=100;
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(arr[i][j]==' ')
                    {
                        arr[i][j]=opponent;
                        best=min(minmax(arr,!isaiplayer,depth+1),best);
                        arr[i][j]=' ';
                    }
            }
        }
        return best;
    }
}
pos calbestmove(char arr[3][3])
{
int bestval=-100;
for (int i = 0; i<3; i++)
    {
        for (int j = 0; j<3; j++)
        {
            if (arr[i][j]==' ')
            {

                arr[i][j] = player;

                int moveval = minmax(arr, false, 0);

                arr[i][j] = ' ';

                if (moveval > bestval)
                {
                    bestmove.a = i;
                    bestmove.b = j;
                    bestval = moveval;
                }
            }
        }
    }


    return bestmove;
}
int main()
{

    char board[3][3] =
    {
        { ' ', ' ', ' ' },
        { ' ', ' ', ' ' },
        { ' ', ' ', ' ' },
    };
    pos aiplay;
    cout<<"SINGLEPLAYER TIC TAC TOE GAME"<<endl;
    int i,j;
    while(1)
    {
        display(board);
        cout<<endl<<endl;
        cout<<"enter your move"<<endl;
        cin>>i>>j;
        board[i][j]='o';
        display(board);
        cout<<endl<<endl;
        aiplay=calbestmove(board);
        i=aiplay.a;
        j=aiplay.b;
        board[i][j]='x';
        if(evaluate(board)==1)
        {
            display(board);
            cout<<"YOU LOSE"<<endl;
            return 0;
        }

        if(isMovesLeft(board)==0)
        {
            display(board);
            cout<<"DRAW"<<endl;
            return 0;
        }

    }
}
