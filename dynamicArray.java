public class dynamicArray {
    private String array[];
    private int count;
    private int sizeofarray;

public dynamicArray(int banyakPemain){
    array = new String[banyakPemain];
    count = 0;
    sizeofarray = banyakPemain;
}



public void shrinkSize()  {   
//declares a temp[] array      
int temp[] = null;   

if (count > 0)   {   
    //creates an array of the size equal to the count i.e. number of elements the array have  
    temp = new int[count];   
    for (int i = 0; i < count; i++) {   
    //copies all the elements of the old array  
        temp[i] = array[i];   
        }   
    sizeofarray = count;   
    array = temp;   
}
}
}
