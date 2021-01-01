package org.nor.GameLogic;

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

    protected abstract int getIncrementX();
    protected abstract int getIncrementY();
}
