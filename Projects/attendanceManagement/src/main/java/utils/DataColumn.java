package main.java.utils;

import java.util.Arrays;

public class DataColumn<T> {
	
    private final T[] data ;
    public DataColumn(T[] data) {
        this.data = Arrays.copyOf(data, data.length);
    }
    public T getData(int index) {
        return data[index];
    }
    public int getSize() {
        return data.length ;
    }
}
