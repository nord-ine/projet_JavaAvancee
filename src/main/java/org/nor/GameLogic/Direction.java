package org.nor.GameLogic;

/**
 * enum representing the four directions in which a line can be made
 */
public enum Direction {


    horizontal
    {

        @Override
        protected int getIncrementX(){
            return 0;
        }
        @Override
        protected int getIncrementY(){
            return 1;
        }

    },
    vertical{

        @Override
        protected int getIncrementX(){
            return 1;
        }
        @Override
        protected int getIncrementY(){
            return 0;
        }

    },
    diagonal1{

        @Override
        protected int getIncrementX(){
            return 1;
        }
        @Override
        protected int getIncrementY(){
            return 1;
        }

    },diagonal2{

        @Override
        protected int getIncrementX(){
            return -1;
        }
        @Override
        protected int getIncrementY(){
            return 1;
        }

    },none{

        @Override
        protected int getIncrementX(){
            return 0;
        }
        @Override
        protected int getIncrementY(){
            return 0;
        }
        
    };

    /**
     * @return by how much x  increments when moving in a direction
     */
    protected abstract int getIncrementX();

    /**
     * @return by how much y increments when moving in a direction
     */
    protected abstract int getIncrementY();
}
