
public class TicTacToe {
    private static class Cell {

        private enum states {X, O,empty}
        private states state;

        Cell() {
            state = states.empty;
        }

        public boolean setState(states inState) {
            if(state == states.empty) {
                state = inState;
                return true;
            }
            return false;
        }

        public String toString() {
            switch (state) {
                case X:
                    return states.X.toString();
                case O:
                    return states.O.toString();
                default:
                    return " ";
            }
        }
    }

    public static class GameException extends Exception {
        /*
        If game throws GameException it means that player's turn is
        NOT over for some reason (exception message is always required!).
         */
        public GameException(String message) {
            super(message);
        }
    }

    private int player = -1;

    private Cell[][] grid;
    private final int _SIZE = 3;

    private boolean gameOn;

    public TicTacToe() {
        gameOn = true;

        grid = new Cell[_SIZE][_SIZE];
        for(int i = 0; i < _SIZE; i++)
            for(int j = 0; j < _SIZE; j++)
                grid[i][j] = new Cell();
    }

    public void changePlayer() {
    	player *= -1;
    }
    public int getCurrentPlayer() {
        return (player < 0) ? 1 : 2;
    }

    public int getLastPlayer() {
        return (player * -1 < 0) ? 1 : 2;
    }

    public boolean isOn() {
        return gameOn;
    }

    public boolean takePosition(int inPos) throws GameException{
        if(gameOn) { // positions from 1 to _SIZE^2 !
            if (inPos > 0 && inPos <= _SIZE * _SIZE) {
                if (grid[(inPos - 1) / _SIZE][(inPos - 1) % _SIZE]
                    .setState((player < 0) ? Cell.states.X : Cell.states.O)) {
                    player *= -1;
                    return true;
                } else throw new GameException("This position is already taken.");
            } else throw new GameException("Position is out of bounds.");
        } else throw new GameException("Game is over.");
    }

    public String rules() {
    	StringBuilder out = new StringBuilder();
    	out.append("   RULES\n");
    	for(int i = 0; i<_SIZE*_SIZE;i++) {
    			if( (i+1) % _SIZE == 0) {
    				out.append(" " + (i + 1)+ "\n ---------\n");
    			}
    			else {
    			 out.append(" " + (i + 1) + " |");
    			}
    	}
    	return out.toString();
    }
    
    public String currentState() {
        StringBuilder out = new StringBuilder();
        for(int i = 0; i < _SIZE; i++) {
            out.append('\n');
            for (int j = 0; j < _SIZE; j++)
            {
            	if(j != _SIZE - 1) {
            		 out.append(" " + grid[i][j] + " |");
            	}
            	else {
            		out.append(" " + grid[i][j]);
            	}
               
            }
            out.append('\n');
            if(i != _SIZE -1) {
            	
            	out.append(" ---------");
            	
            }
        }

        return out.toString();
    }

    public boolean checkGrid() throws GameException{
    	int who_is_won = -1;
    	int i, j;
        if (gameOn) {
            for (j = 0; j < _SIZE; j++) {
                for (i = 1; i < _SIZE; i++) {
                    if (grid[0][j].state == Cell.states.empty ||
                            grid[i][j].state != grid[0][j].state) {
                        break;
                    }
                    if (i == _SIZE - 1) {
                        gameOn = false;
                        who_is_won = grid[0][j].state.ordinal() + 1;
                    }
                }		
               if(!gameOn){
                   break;
               }
            }
        }
        if (gameOn) {
            for (i = 0; i < _SIZE; i++) {
                for (j = 1; j < _SIZE; j++) {
                    if (grid[i][0].state == Cell.states.empty ||
                            grid[i][j].state != grid[i][0].state) {
                        break;
                    }
                    if (j == _SIZE - 1) {
                        gameOn = false;
                        who_is_won = grid[i][0].state.ordinal() + 1;
                    }
                }
                if(!gameOn){
                    break;
                }
            }
        }
        if (gameOn) {
            for (i = 1; i < _SIZE; i++) {
                if (grid[0][0].state == Cell.states.empty ||
                        grid[i][i].state != grid[0][0].state) {
                    break;
                }
                if (i == _SIZE - 1) {
                    gameOn = false;
                    who_is_won = grid[0][0].state.ordinal() + 1;
                }
            }
        }

        if (gameOn) {
            for (i = _SIZE - 2; i >= 0; i--) {
                if (grid[0][_SIZE - 1].state == Cell.states.empty ||
                        grid[_SIZE-1 - i][i].state != grid[0][_SIZE - 1].state) {
                    break;
                }
                if (i == 0) {
                    gameOn = false;
                    who_is_won = grid[0][_SIZE - 1].state.ordinal() + 1;
                }
            }

        }

            
         
         

            if(!gameOn) {
                throw new GameException("Player" + who_is_won + " is a winner.");
            }

            if(gameOn) {
                boolean draw = true;
                for ( i = 0; i < _SIZE && draw; i++) {
                    for ( j = 0; j < _SIZE && draw; j++) {
                        if (grid[i][j].state == Cell.states.empty) draw = false;
                    }
                }
                if (draw) {
                    gameOn = false;
                    throw new GameException("Draw.");
                }
            }
    

        return gameOn;
    }
}
