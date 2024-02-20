package com.company.enums;


/*
 * Created by Adrian Ng, 23/4/2020 , 16:42pm
 */
 
public enum ElementsData {

    X("X"), //Symbol X
    O("O"), //Symbol Y
    NOTHING(" "); //NOTHING, means no elements in that column

    public final String getReadAble; //Get the symbol of the elements, used in graphing the table

    ElementsData(String s) {
        getReadAble = s;
    }

    //get the other side of the element
    public static ElementsData getFlipSide(ElementsData elementsData) {
        if (elementsData == ElementsData.X) { //if elements is X, the return O
            return ElementsData.O;
        } else if (elementsData == ElementsData.O) { //if elements if O, then return X
            return ElementsData.X;
        } else throw new UnsupportedOperationException("Other ElementsData is not supported."); //Throw UnsupportedOperationException if elements is not X or O
    }

    //Check if first and second is equals to each other, if one of them are NOTHING, then return false, else return first is equals to second or not
    public static boolean match(ElementsData first, ElementsData second) {
        if (first == ElementsData.NOTHING || second == ElementsData.NOTHING) {
            return false;
        }
        return first == second;
    }
}
