package org.java8.inaction.chapter8.chaiofResposibility;

public abstract class ProcessObject<T> {

    protected ProcessObject<T> successor;

    public void setSuccessor(ProcessObject<T> successor){
        this.successor = successor;
    }

    public T handle(T input){
        T r = handleWork(input);
        if(successor != null){
            return successor.handle(r);
        }
        return r;
    }

    protected abstract T handleWork(T input);
}
