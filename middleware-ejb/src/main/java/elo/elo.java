package elo;


public class elo   {
    
/**
 * Le K value représente le maximum de points qu'une entité peut gagner/perdre en un match.
 */

int K_VALUE = 64;
/**
 * La différence de classement Elo à partir de laquelle il n'est plus intéressant de jouer contre.
 */
int  RANGE = 400;

// e1 Le classement Elo du première jouer (Nbre de Points)
// e2 Le classement Elo du deuxième jouer (Nbre de Points)
// Score (1 = gagné, 0 = perdu, 0.5 = match nul)
//elo_calculator(1,0,1602,1753)
// 

long[] elo_calculator(int score1,int score2,int e1,int e2)
{
	int  elo1 = e1;
	int  elo2 = e2;
	long e;
    long[] r = new long [2];
	if(score1 != score2)	// le match n'est pas une égalité, il existe un vainqueur
	{
		if(score1 > score2)	// le vainqueur est le joueur 1
		{
                    
                    	e = (K_VALUE - Math.round(1/(1+Math.pow(10,((elo2-elo1)/RANGE)))* K_VALUE));
			r[1] = elo1 + e;
			r[2] = elo2 - e;

		}
		else				// le vainqueur est le joueur 2
		{
                    	e = (K_VALUE - Math.round(1/(1+Math.pow(10,((elo1-elo2)/RANGE)))* K_VALUE));
			r[1] = elo1 - e;
			r[2] = elo2 + e;
		}
	}
	else					// le match est une égalité, il n'existe pas de vainqueur
	{
		if(elo1 == elo2)	// les deux joueurs ont le même classement elo
		{
			r[1] = elo1;
			r[2] = elo2;
		}
		else			// les deux joueurs n'ont pas le même classement elo
		{
			if(elo1 > elo2)	// le joueur 1 a un classement elo plus élevé
			{
				e=(K_VALUE-Math.round(1/(1+Math.pow(10,((elo1-elo2)/RANGE)))*K_VALUE))-(K_VALUE-Math.round(1/(1+Math.pow(10,((elo2-elo1)/RANGE)))*K_VALUE));
				r[1] = elo1 - e;
				r[2] = elo2 + e;
			}
			else			// le joueur 2 a un classement elo plus élevé
			{
				e=(K_VALUE-Math.round(1/(1+Math.pow(10,((elo2-elo1)/RANGE)))*K_VALUE))-(K_VALUE-Math.round(1/(1+Math.pow(10,((elo1-elo2)/RANGE)))*K_VALUE));
				r[1] = elo1 + e;
				r[2] = elo2 - e;
			}
		}
	}
      if ((r[1] - elo1) > 0)
        {
            r[1]= r[1];
        }else			
	{
           if ((r[2] - elo2) > 0)
            {
               r[1]= r[2];
            }              
        }
return r;
      
}

   

}


