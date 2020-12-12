package bg.petarh.interview.sirma.employees.utils;

import bg.petarh.interview.sirma.employees.employees.Employee;

public class Pair<E extends Employee, T extends Long>{
    private E key;
    private T value;

    Pair(){
        this.key = null;
        this.value = (T)Long.valueOf(0L);
    }

    public Employee getKey(){
        return this.key;
    }

    public T getValue(){
        return this.value;
    }

    void updatePair(E employee, T days){
        this.key = employee;
        this.value = days;
    }

}
